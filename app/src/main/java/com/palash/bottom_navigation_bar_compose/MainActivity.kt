package com.palash.bottom_navigation_bar_compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.palash.bottom_navigation_bar_compose.screens.Chat
import com.palash.bottom_navigation_bar_compose.screens.HomeScreen
import com.palash.bottom_navigation_bar_compose.screens.Settings

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val items = listOf(
                BottomNavigationItem(
                    title = "Home",
                    selectedIcon = Icons.Filled.Home,
                    unselectedIcon = Icons.Outlined.Home,
                    hasNews = false,
                ),
                BottomNavigationItem(
                    title = "Chat",
                    selectedIcon = Icons.Filled.Email,
                    unselectedIcon = Icons.Outlined.Email,
                    hasNews = false,
                    badgeCount = 45
                ),
                BottomNavigationItem(
                    title = "Setting",
                    selectedIcon = Icons.Filled.Settings,
                    unselectedIcon = Icons.Outlined.Settings,
                    hasNews = true,
                ),
                BottomNavigationItem(
                    title = "Notification",
                    selectedIcon = Icons.Filled.Notifications,
                    unselectedIcon = Icons.Outlined.Notifications,
                    hasNews = false,
                    badgeCount = 10
                ),
                BottomNavigationItem(
                    title = "Account",
                    selectedIcon = Icons.Filled.AccountCircle,
                    unselectedIcon = Icons.Outlined.AccountCircle,
                    hasNews = false,
                ),
            )
            var selectedItemIndex by rememberSaveable {
                mutableStateOf(0)
            }
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("My Scaffold Example") },
                            Modifier.background(color = Color.Blue),
                        )
                    },
                    bottomBar = {
                    NavigationBar {
                        items.forEachIndexed { index, item ->
                            NavigationBarItem(
                                selected = selectedItemIndex == index,
                                onClick = { selectedItemIndex = index },
                                label = { Text(text = item.title) },
                                alwaysShowLabel = false,
                                icon = {
                                    BadgedBox(badge = {
                                        if (item.badgeCount != null) {
                                            Badge {
                                                Text(text = item.badgeCount.toString())
                                            }
                                        } else if (item.hasNews){
                                            Badge()
                                        }
                                    }) {
                                        Icon(
                                            imageVector =
                                            if (index == selectedItemIndex) {
                                                item.selectedIcon
                                            } else item.unselectedIcon,
                                            contentDescription = item.title
                                        )
                                    }
                                })
                        }
                    }
                }) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        if (items[selectedItemIndex].title=="Home"){
                            //Log.d("Home Screen", "Home")
                            HomeScreen()
                        }else if (items[selectedItemIndex].title=="Chat"){
                            Chat()
                            //Log.d("Browse Screen", "Browse")
                        } else if (items[selectedItemIndex].title == "Setting"){
                            Settings()
                            //Log.d("Account Screen", "Account")
                        }
                        Text(text = items[selectedItemIndex].title)
                    }
                }

            }
        }
    }
}