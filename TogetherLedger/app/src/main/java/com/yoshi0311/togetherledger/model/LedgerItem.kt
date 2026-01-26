package com.yoshi0311.togetherledger.model

data class LedgerItem(
    val category: String,
    val content: String,
    val timestamp: String,
    val amount: Int,
    val assetType: String,
    val isIncome: Boolean,
    val id: Long? = null // DB용
)