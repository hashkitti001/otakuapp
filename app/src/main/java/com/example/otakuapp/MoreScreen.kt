package com.example.otakuapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.otakuapp.viewmodels.StorageViewModel

// Custom colors
val GreenAppUsage = Color(0xFF3DDC84)
val BlueOtherUsage = Color(0xFF03A9F4)
val GrayFreeSpace = Color(0xFFBDBDBD)
val BackgroundColor = Color.Black
val TextColor = Color.White
val LightGrayColor = Color.LightGray

@Preview()
@Composable
fun MoreScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "More",
                style = MaterialTheme.typography.titleLarge,
                color = TextColor,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // Profile Section
        item { SectionTitle(title = "Profile") }
        item { ButtonWithArrowEnd(name = "My Account", imgRes = R.drawable.profile) }
        item { ButtonWithArrowEnd(name = "Privacy and Security", imgRes = R.drawable.securityuser) }

        // Downloads Section
        item { SectionTitle(title = "Downloads") }
        item {
            ButtonWithSubtitles(
                name = "Auto Downloads",
                sub = "Download the next episode",
                imgRes = R.drawable.download_square
            )
        }
        item { ButtonWithSwitch(name = "Download with WiFi", imgRes = R.drawable.wifi) }
        item { ButtonWithArrowEnd(name = "Video Resolution", imgRes = R.drawable.icon_video) }

        // Storage Section
        item { SectionTitle(title = "Storage") }
        item { AppSizeVisualization() }
        item { DeleteCache() }
        item { About() }
    }
}


@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.bodyLarge,
        color = TextColor,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun ButtonWithArrowEnd(name: String, imgRes: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = imgRes),
                contentDescription = name,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(25.dp),
                tint = TextColor
            )
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                color = TextColor
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
            contentDescription = "Navigate to $name",
            tint = TextColor
        )
    }
}

@Composable
fun ButtonWithSubtitles(name: String, sub: String, imgRes: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = imgRes),
                contentDescription = name,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(25.dp),
                tint = TextColor
            )
            Column {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextColor
                )
                Text(
                    text = sub,
                    style = MaterialTheme.typography.bodySmall,
                    color = LightGrayColor
                )
            }
        }
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
            contentDescription = "Navigate to $name",
            tint = TextColor
        )
    }
}

@Composable
fun ButtonWithSwitch(name: String, imgRes: Int) {
    var isChecked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = imgRes),
                contentDescription = name,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(25.dp),
                tint = TextColor
            )
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                color = TextColor
            )
        }
        Switch(
            checked = isChecked,
            onCheckedChange = { isChecked = it }
        )
    }
}

@Composable
fun AppSizeVisualization(storageViewModel: StorageViewModel = viewModel()) {
    val appSize by storageViewModel.appSize.collectAsState()
    val otherAppUsage by storageViewModel.otherAppsSize.collectAsState()
    val totalStorage by storageViewModel.totalStorage.collectAsState()

    if (totalStorage > 0L) {
        StorageVis(appSize = appSize, otherAppUsage = otherAppUsage, totalStorage = totalStorage)
    } else {
        Text(text = "Analyzing storage...", color = TextColor)
    }
}

@Composable
fun StorageVis(appSize: Long, otherAppUsage: Long, totalStorage: Long) {
    val appUsage = appSize.toFloat() / totalStorage
    val othersUsage = otherAppUsage.toFloat() / totalStorage
    val freeSpace = 1.0f - othersUsage - appUsage

    val appSizeMB = appSize.toDouble() / (1024 * 1024) // Convert to MB
    val otherAppsSizeGB = otherAppUsage.toDouble() / (1024 * 1024 * 1024) // Convert to GB
    val totalStorageGB = totalStorage.toDouble() / (1024 * 1024 * 1024) // Convert to GB
    val freeSpaceGB = (totalStorageGB - otherAppsSizeGB - (appSizeMB / 1024))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        StorageBar(appUsage = appUsage, otherUsage = othersUsage, freeSpace = freeSpace)
        Spacer(modifier = Modifier.height(16.dp))


        Column(modifier = Modifier.fillMaxWidth()) {
            StorageRow(
                label = "Other Apps",
                value = "${String.format("%.2f", otherAppsSizeGB)} GB",
                color = BlueOtherUsage
            )
            StorageRow(
                label = "Otaku",
                value = "${String.format("%.2f", appSizeMB)} MB",
                color = GreenAppUsage
            )
            StorageRow(
                label = "Free Space",
                value = "${String.format("%.2f", freeSpaceGB)} GB",
                color = GrayFreeSpace
            )
        }
    }
}

@Composable
fun StorageRow(label: String, value: String, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Colored circle
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(RoundedCornerShape(50))
                .background(color)
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Label and value with spacing
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = TextColor
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = TextColor
            )
        }
    }
}


@Composable
fun StorageBar(appUsage: Float, otherUsage: Float, freeSpace: Float) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(16.dp)
            .background(Color.Gray.copy(alpha = 0.2f), shape = MaterialTheme.shapes.small)
            .padding(2.dp)
            .clip(RoundedCornerShape(15.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .weight(otherUsage)
                .fillMaxHeight()
                .background(BlueOtherUsage)
        )
        Spacer(
            modifier = Modifier
                .weight(appUsage)
                .fillMaxHeight()
                .background(GreenAppUsage)
        )

        Spacer(
            modifier = Modifier
                .weight(freeSpace)
                .fillMaxHeight()
                .background(GrayFreeSpace)
        )
    }
}

@Composable
fun DeleteCache() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.delete_icon),
            contentDescription = "Delete cache",
            modifier = Modifier.size(24.dp),
            tint = TextColor
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = "Delete cache",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                color = TextColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Free up space by clearing your cache. Your downloads won’t be removed.",
                style = MaterialTheme.typography.bodySmall,
                color = LightGrayColor
            )
        }
    }
}

@Composable
fun About() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        SectionTitle(title = "About")
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Version",
                style = MaterialTheme.typography.bodyMedium,
                color = TextColor
            )
            Text(
                text = "1.0.0",
                style = MaterialTheme.typography.bodyMedium,
                color = LightGrayColor
            )
        }
    }
}
