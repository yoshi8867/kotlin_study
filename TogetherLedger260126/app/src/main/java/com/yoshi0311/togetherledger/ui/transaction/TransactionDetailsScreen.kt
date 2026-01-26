package com.yoshi0311.togetherledger.ui.transaction
import com.yoshi0311.togetherledger.R
import com.yoshi0311.togetherledger.ui.navigation.NavigationDestination

object TransactionDetailsDestination : NavigationDestination {
    override val route = "transaction_details"
    override val titleRes = R.string.transaction_detail_title
    const val transactionIdArg = "transactionId"
    val routeWithArgs = "$route/{$transactionIdArg}"
}

class TransactionDetailsScreen {
}