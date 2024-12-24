package com.example.otakuapp.viewmodels

import android.app.Application
import android.os.Build
import android.os.StatFs
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StorageViewModel(application: Application) : AndroidViewModel(application) {
    /*
    * Contains business logic for getting app size,
    * storage used by other apps, and total device storage.
    * */
    private val _appSize = MutableStateFlow<Long>(0L)
    val appSize: StateFlow<Long> = _appSize

    private val _otherAppsSize = MutableStateFlow<Long>(0L)
    val otherAppsSize: StateFlow<Long> = _otherAppsSize

    private val _totalStorage = MutableStateFlow<Long>(0L)
    val totalStorage: StateFlow<Long> = _totalStorage

    init {
        calculateStorageInfo()
    }

    private fun calculateStorageInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val appContext = getApplication<Application>()
                val packageInfo =
                    appContext.packageManager.getPackageInfo(appContext.packageName, 0)
                val appPath = packageInfo.applicationInfo?.sourceDir
                val appFile = appPath?.let { java.io.File(it) }
                if (appFile != null) {
                    _appSize.value = appFile.length()
                }

                val statFs = StatFs("/data")
                val totalBytes = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    statFs.totalBytes
                } else {
                    statFs.blockCountLong * statFs.blockSizeLong
                }
                _totalStorage.value = totalBytes

                // Calculate storage used by other apps
                val usedBytes = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    statFs.totalBytes - statFs.availableBytes
                } else {
                    statFs.blockCountLong * statFs.blockSizeLong - statFs.availableBlocksLong * statFs.blockSizeLong
                }
                _otherAppsSize.value = usedBytes - _appSize.value

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
