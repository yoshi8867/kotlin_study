package com.yoshi0311.togetherledger.navigation

sealed class Screen(
    val route: String,
    val title: String
) {
    object Daily : Screen("daily", "일별")
    object Monthly : Screen("monthly", "월별")
    object Calendar : Screen("calendar", "달력")
    object Sms : Screen("sms", "SMS")
    object Settings : Screen("settings", "설정")

    companion object {
        val tabs = listOf(Daily, Monthly, Calendar, Sms, Settings)
    }
}