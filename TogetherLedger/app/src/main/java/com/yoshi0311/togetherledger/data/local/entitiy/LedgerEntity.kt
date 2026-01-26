package com.yoshi0311.togetherledger.data.local.entitiy

@Entity(
    tableName = "ledger",
    indices = [
        Index(value = ["timestamp"]),
        Index(value = ["category"])
    ]
)
data class LedgerEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val timestamp: Long,
    val category: String,
    val memo: String = "",
    val amount: Long,
    /** 0=EXPENSE, 1=INCOME */
    val type: Int
)