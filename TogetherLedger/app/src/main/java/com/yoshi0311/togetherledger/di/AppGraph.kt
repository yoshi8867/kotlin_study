package com.yoshi0311.togetherledger.di
import android.content.Context
import androidx.room.Room
import com.yoshi0311.togetherledger.data.LedgerRepository
import com.yoshi0311.togetherledger.data.local.db.AppDatabase

object AppGraph {
    @Volatile private var db: AppDatabase? = null
    @Volatile private var repo: LedgerRepository? = null

    fun provideRepository(context: Context): LedgerRepository {
        return repo ?: synchronized(this) {
            val database = db ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app.db"
            ).build().also { db = it }

            LedgerRepository(database.ledgerDao()).also { repo = it }
        }
    }
}
