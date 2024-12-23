package com.example.otakuapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun MoreScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(5.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(20.dp),
            text = "More",
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        // Profile Section
        Column(modifier = Modifier.padding(start = 5.dp)) {
            Text(
                text = "Profile",
                style = MaterialTheme.typography.labelSmall,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            ButtonWithArrowEnd(name = "My Account", imgRes = R.drawable.profile)
            ButtonWithArrowEnd(name = "Privacy and Security", imgRes = R.drawable.securityuser)
        }
        // Downloads
        Column(modifier = Modifier.padding(start = 5.dp)) {
            Text(
                text = "Downloads",
                style = MaterialTheme.typography.labelSmall,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            ButtonWithSubtitles(
                name = "Auto-Downloads",
                sub = "Download the next episode",
                imgRes = R.drawable.download_square
            )
            ButtonWithSwitch(name = "Download with WiFi", imgRes = R.drawable.wifi)
            ButtonWithArrowEnd(name = "Video Resolution", imgRes = R.drawable.icon_video)
        }
    }
}

@Composable
fun ButtonWithArrowEnd(name: String, imgRes: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = imgRes),
                contentDescription = name,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(25.dp), // Set the desired size
                tint = Color.White
            )

            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
            contentDescription = "Navigate to $name",
            tint = Color.White
        )
    }
}

@Composable
fun ButtonWithSubtitles(name: String, sub: String, imgRes: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = imgRes),
                contentDescription = name,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(25.dp), // Set the desired size
                tint = Color.White
            )

            Column() {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
                Text(
                    text = sub,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.LightGray
                )
            }
        }
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
            contentDescription = "Navigate to $name",
            tint = Color.White
        )
    }
}

@Composable
fun ButtonWithSwitch(name: String, imgRes: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = imgRes),
                contentDescription = name,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(25.dp), // Set the desired size
                tint = Color.White
            )

            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )


        }
        var isChecked by remember { mutableStateOf(false) }
        Switch(checked = isChecked, onCheckedChange = { isChecked = it })
    }
}