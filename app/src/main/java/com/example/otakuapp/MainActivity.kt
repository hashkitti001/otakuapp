package com.example.otakuapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DownloadForOffline
import androidx.compose.material.icons.rounded.GridView
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
        delay(4000)
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
        composable("downloads") { DownloadScreen() }
        composable("settings") { SettingsScreen() }
    }
}

@Composable
fun AppBottomBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("home", Icons.Rounded.Home, "Home"),
        BottomNavItem("search", Icons.Rounded.GridView, "Discover"),
        BottomNavItem("downloads", Icons.Rounded.DownloadForOffline, "Downloads"),
        BottomNavItem("settings", Icons.Rounded.Settings, "Settings")
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


