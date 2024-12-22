package com.example.otakuapp

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import com.example.otakuapp.data.FeedItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

// Trending Feed Data
val trendingItems = listOf(
    FeedItem(name = "Oshi No Ko", imgRes = R.drawable.oshi_no_ko),
    FeedItem(name = "Attack On Titan", imgRes = R.drawable.aot),
    FeedItem(name = "Naruto", imgRes = R.drawable.naruto),
    FeedItem(name = "One Piece", imgRes = R.drawable.one_piece),
    FeedItem(name = "Jujutsu Kaisen", imgRes = R.drawable.jjk)
)

val recommendedItems = listOf(
    FeedItem(name = "Bleach", imgRes = R.drawable.bleach),
    FeedItem(name = "Dr. Stone", imgRes = R.drawable.dr_stone),
    FeedItem(name = "Love is War", imgRes = R.drawable.love_is_war),
    FeedItem(name = "Your Lie In April", imgRes = R.drawable.ylia),
    FeedItem(name = "Demon Slayer", imgRes = R.drawable.demon_slayer),
    FeedItem(name = "My Hero Academia", imgRes = R.drawable.mha)
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
            .padding(16.dp)
    ) {
        AccountInfoAndSearch()
        TrendingFeed()
        Recommended()
    }
}

@Composable
fun AccountInfoAndSearch() {
    val lightGrey = colorResource(id = R.color.light_grey)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
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

        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.inverseSurface)
                .size(40.dp)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "Search",
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TrendingFeed() {
    // Display 3 items per page
    val itemsPerPage = 3
    val pagerState = rememberPagerState()

    Column(modifier = Modifier.padding(top = 16.dp)) {
        Text(
            text = "Trending Now",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
        )

        HorizontalPager(count = (trendingItems.size + itemsPerPage - 1) / itemsPerPage, state = pagerState) { page ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                for (i in 0 until itemsPerPage) {
                    val index = page * itemsPerPage + i
                    if (index < trendingItems.size) {
                        ExpandableTrendingFeedItem(trendingItems[index])
                    }
                }
            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Recommended() {
    // Display 4 items per page
    val itemsPerPage = 4
    val pagerState = rememberPagerState()

    Column(modifier = Modifier.padding(top = 16.dp)) {
        Text(
            text = "Recommended",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
        )

        HorizontalPager(count = (recommendedItems.size + itemsPerPage - 1) / itemsPerPage, state = pagerState) { page ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                for (i in 0 until itemsPerPage) {
                    val index = page * itemsPerPage + i
                    if (index < recommendedItems.size) {
                        RecommendedItem(recommendedItems[index])
                    }
                }
            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        )
    }
}

@Composable
fun ExpandableTrendingFeedItem(item: FeedItem) {
    var isExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .width(if (isExpanded) 400.dp else 250.dp)
            .height(250.dp)
            .clip(RoundedCornerShape(1.dp))
            .clickable { isExpanded = !isExpanded }
            .padding(top = 10.dp)
            .animateContentSize()
    ) {
        Image(
            painter = painterResource(id = item.imgRes),
            contentDescription = item.name,
            modifier = Modifier.fillMaxSize()
        )

        if (isExpanded) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.6f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.name,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}

@Composable
fun RecommendedItem(item: FeedItem) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(1.dp))
            .clickable { }
            .padding(top = 10.dp)
    ) {
        Image(
            painter = painterResource(id = item.imgRes),
            contentDescription = item.name,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun LatestReleases(){
 //Latest Releases composable
}
