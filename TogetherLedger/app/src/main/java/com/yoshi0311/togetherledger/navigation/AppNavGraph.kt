package com.yoshi0311.togetherledger.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yoshi0311.togetherledger.ui.daily.DailyScreen
import com.yoshi0311.togetherledger.ui.montly.MontlyScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Daily.route,
        modifier = modifier
    ) {
        composable(Screen.Daily.route) {
            DailyScreen()
        }
        composable(Screen.Monthly.route) {
            MontlyScreen()
        }
        composable(Screen.Calendar.route) {
            // TODO
        }
        composable(Screen.Sms.route) {
            // TODO
        }
        composable(Screen.Settings.route) {
            // TODO
        }
    }
}