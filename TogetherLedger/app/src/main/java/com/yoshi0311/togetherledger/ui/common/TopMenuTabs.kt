package com.yoshi0311.togetherledger.ui.common

import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.yoshi0311.togetherledger.navigation.Screen

@Composable
fun TopMenuTabs(
    currentRoute: String?,
    onTabSelected: (Screen) -> Unit
) {
    val tabs = Screen.tabs
    val selectedIndex = tabs.indexOfFirst { it.route == currentRoute }
        .coerceAtLeast(0)

    TabRow(selectedTabIndex = selectedIndex) {
        tabs.forEachIndexed { index, screen ->
            Tab(
                selected = index == selectedIndex,
                onClick = { onTabSelected(screen) },
                text = { Text(screen.title) }
            )
        }
    }
}
