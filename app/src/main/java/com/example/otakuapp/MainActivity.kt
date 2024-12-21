package com.example.otakuapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DownloadForOffline
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.GridView
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.otakuapp.ui.theme.OtakuAppTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OtakuAppTheme {
                AppWithSplashScreen()
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.Q)
@Preview
@Composable
fun AppWithSplashScreen() {
    var showSplash by remember { mutableStateOf(true) }

    // Delay the splash screen for 3 seconds
    LaunchedEffect(Unit) {
        delay(6000)
        showSplash = false
    }

    if (showSplash) {
        SplashScreen()
    } else {
        MainApp()
    }
}



@Composable
fun MainApp() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { AppBottomBar(navController) }
    ) { innerPadding ->
        AppNavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") { HomeFeed() }
        composable("search") { ScreenContent("Search Screen") }
        composable("downloads") { ScreenContent("Favorites Screen") }
        composable("more") { ScreenContent("Profile Screen") }
    }
}

@Composable
fun AppBottomBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("home", Icons.Rounded.Home, "Home"),
        BottomNavItem("search", Icons.Rounded.GridView, "Discover"),
        BottomNavItem("downloads", Icons.Rounded.DownloadForOffline, "Downloads"),
        BottomNavItem("more", Icons.Rounded.Person, "More")
    )
    val currentBackStack = navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack.value?.destination?.route

    BottomAppBar(
        modifier = Modifier.background(MaterialTheme.colorScheme.inverseSurface)
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentDestination == item.route,
                onClick = { navController.navigate(item.route) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = { Text(item.label) }
            )
        }
    }
}

@Composable
fun ScreenContent(content: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = content,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

data class BottomNavItem(val route: String, val icon: ImageVector, val label: String)


