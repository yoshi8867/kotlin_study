package com.yoshi0311.togetherledger.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yoshi0311.togetherledger.data.local.dao.LedgerDao
import com.yoshi0311.togetherledger.data.local.entitiy.LedgerEntity

@Database(
    entities = [LedgerEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ledgerDao(): LedgerDao
}
