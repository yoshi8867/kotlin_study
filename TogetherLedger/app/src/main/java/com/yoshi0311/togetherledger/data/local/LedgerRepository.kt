package com.yoshi0311.togetherledger.data

import com.yoshi0311.togetherledger.data.local.dao.LedgerDao
import com.yoshi0311.togetherledger.data.local.entitiy.LedgerEntity
import kotlinx.coroutines.flow.Flow

class LedgerRepository(
    private val dao: LedgerDao
) {
    fun observeAll(): Flow<List<LedgerEntity>> = dao.observeAll()

    fun observeBetween(from: Long, to: Long): Flow<List<LedgerEntity>> =
        dao.observeBetween(from, to)

    suspend fun add(
        timestamp: Long,
        category: String,
        memo: String,
        amount: Long,
        type: Int
    ): Long {
        return dao.insert(
            LedgerEntity(
                timestamp = timestamp,
                category = category,
                memo = memo,
                amount = amount,
                type = type
            )
        )
    }

    suspend fun update(item: LedgerEntity) = dao.update(item)
    suspend fun delete(item: LedgerEntity) = dao.delete(item)
}
