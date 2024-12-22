package com.example.otakuapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun DownloadScreen() {
    val interFont = FontFamily(Font(R.font.inter_variable))
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.group_4),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Adjust to fit your design
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)

        ) {
            Text(
                text = "Downloads",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 29.sp

            )
            //No items found
            Column(modifier = Modifier.padding(top = 100.dp, start = 50.dp)) {
                Box(
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                ) {
                    Image(
                        painterResource(id = R.drawable.no_download),
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = "No Downloads"
                    )
                }
                Text(
                    text = "Oops! No downloads yet",
                    textAlign = TextAlign.Center,
                    fontFamily = interFont
                )
            }
            // A view for actual downloads would be available in version 2

        }
    }
}
