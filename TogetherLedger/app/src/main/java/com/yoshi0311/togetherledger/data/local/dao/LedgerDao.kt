package com.yoshi0311.togetherledger.data.local.dao

import androidx.room.*
import com.yoshi0311.togetherledger.data.local.entitiy.LedgerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LedgerDao {

    @Query("SELECT * FROM ledger ORDER BY timestamp DESC, id DESC")
    fun observeAll(): Flow<List<LedgerEntity>>

    @Query("SELECT * FROM ledger WHERE timestamp BETWEEN :from AND :to ORDER BY timestamp DESC, id DESC")
    fun observeBetween(from: Long, to: Long): Flow<List<LedgerEntity>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(item: LedgerEntity): Long

    @Update
    suspend fun update(item: LedgerEntity)

    @Delete
    suspend fun delete(item: LedgerEntity)
}
