package com.yoshi0311.togetherledger.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yoshi0311.togetherledger.ui.daily.DailyDestination
import com.yoshi0311.togetherledger.ui.daily.DailyScreen
import com.yoshi0311.togetherledger.ui.transaction.TransactionEntryDestination
import com.yoshi0311.togetherledger.ui.transaction.TransactionEntryScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = DailyDestination.route,
        modifier = modifier,
    ) {
        composable(route = DailyDestination.route) {
            DailyScreen(
                navigateToTransactionEntry = { navController.navigate(TransactionEntryDestination.route) },
                navigateToTransactionUpdate = {
//                    navController.navigate("${ItemDetailsDestination.route}/${it}")
                    navController.navigate(DailyDestination.route)
                },
            )
        }
        composable(route = TransactionEntryDestination.route) {
            TransactionEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() },
            )
        }
    }
}