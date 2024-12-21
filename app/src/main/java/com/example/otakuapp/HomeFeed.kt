package com.example.otakuapp

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.otakuapp.data.TrendingFeedItem

//Trending Feed Data
val trendingItems = listOf(
    TrendingFeedItem(
        name = "Oshi No Ko",
        imgRes = R.drawable.oshi_no_ko
    ),
    TrendingFeedItem(
        name = "Attack On Titan",
        imgRes = R.drawable.aot
    ),
)
@Preview
@Composable
fun HomeFeed() {
    val bgGradient = Brush.verticalGradient(
        colors = listOf(Color.Black, Color(0xFF800080), Color.Black)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgGradient)
            .padding(16.dp) // Padding for the whole container
    ) {
        AccountInfoAndSearch()
        TrendingFeed()
    }
}

@Composable
fun AccountInfoAndSearch() {
    val lightGrey = colorResource(id = R.color.light_grey)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp), // Adjust the vertical padding for spacing
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically // Vertically center items
    ) {
        // Account section
        Row(
            verticalAlignment = Alignment.CenterVertically // Vertically center the profile pic and name
        ) {
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 10.dp),
                painter = painterResource(id = R.drawable.user_dp),
                contentDescription = "User profile pic"
            )

            Text(
                text = "Ella Mai",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = lightGrey
            )
        }

        // Search section
        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.inverseSurface)
                .size(40.dp) // Adjust the size of the search box
                .padding(8.dp), // Add padding around the icon
            contentAlignment = Alignment.Center
        ) {// Smooth transition for width changes
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "Search",
                tint = Color.White,
                modifier = Modifier.size(30.dp)

            )
        }
    }
}
@Composable
fun TrendingFeed() {
    Column(modifier = Modifier.padding(top = 16.dp)) {
        Text(
            text = "Trending Now",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            items(trendingItems.size) { index ->
                ExpandableTrendingFeedItem(trendingItems[index])
            }
        }
    }
}

@Composable
fun ExpandableTrendingFeedItem(item: TrendingFeedItem) {
    // State to control expansion
    var isExpanded by remember { mutableStateOf(false) }

    val expandedWidth = 400.dp
    val collapsedWidth = 250.dp
    val heightRatio = 6f / 5f // Inverse ratio for maintaining 5:6

    Box(
        modifier = Modifier
            .width(if (isExpanded) expandedWidth else collapsedWidth)
            .height(collapsedWidth)
            .clip(RoundedCornerShape(1.dp))
            .clickable { isExpanded = !isExpanded }
            .padding(top = 10.dp)
            .animateContentSize()
    ) {
        Image(
            painter = painterResource(id = item.imgRes),
            contentDescription = item.name,

            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(0.5625F)
        )

        // Overlay for text
        if (isExpanded) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.6f))
                    .align(Alignment.Center)

            ) {
                Text(
                    text = item.name,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
