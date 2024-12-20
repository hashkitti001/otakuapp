package com.example.otakuapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ImageRow() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(7.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // First Column - 1/3 of the width
        Column(
            modifier = Modifier
                .weight(0.33f) // 1/3 of the width
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.black_clover),
                contentDescription = "Black Clover",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp) // Ensuring a minimum height for each image
            )
            Image(
                painter = painterResource(id = R.drawable.bleach),
                contentDescription = "Bleach",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.death_note),
                contentDescription = "Death Note",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.demon_slayer),
                contentDescription = "Demon Slayer",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp)
            )
        }

        // Second Column - 1/3 of the width
        Column(
            modifier = Modifier
                .weight(0.33f) // 1/3 of the width
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.fmab),
                contentDescription = "Full Metal Alchemist",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.hells_paradise),
                contentDescription = "Jigokuraku",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.hxh),
                contentDescription = "HxH",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.jjk),
                contentDescription = "Jujutsu Kaisen",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp)
            )
        }

        // Third Column - 1/3 of the width
        Column(
            modifier = Modifier
                .weight(0.33f) // 1/3 of the width
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.mha),
                contentDescription = "My Hero Academia",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.naruto),
                contentDescription = "Naruto",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.one_piece),
                contentDescription = "One Piece",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp)
            )
        }
    }
}

@Preview
@Composable
fun Onboarding() {
    Box(modifier = Modifier.fillMaxSize()) {
        ImageRow()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black.copy(alpha = 0.5f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start=20.dp, end=20.dp, bottom = 80.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Unleash your inner otaku!",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 44.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    modifier = Modifier
                        .width(400.dp)
                        .padding(top = 6.dp),
                    shape = RoundedCornerShape(6.dp),
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = "Get Started")
                }
            }
        }
    }
}
