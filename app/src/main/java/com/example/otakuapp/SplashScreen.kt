package com.example.otakuapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun SplashScreen() {
    // Load the custom font
    val interFont = FontFamily(Font(R.font.inter_variable))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                painter = painterResource(id = R.drawable.splash_logo),
                contentDescription = "Splash logo"
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "OTAKU",
                fontSize = 21.sp,
                color = Color.White,
                fontFamily = interFont,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}
