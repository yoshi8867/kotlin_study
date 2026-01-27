package com.yoshi0311.togetherledger.ui.transaction

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TransactionEntryViewModel : ViewModel() {

    var transactionUiState by mutableStateOf(TransactionUiState())
        private set

    fun updateUiState(transactionDetails: TransactionDetails) {
        transactionUiState =
            TransactionUiState(transactionDetails = transactionDetails, isEntryValid = validateInput(transactionDetails))
    }

    suspend fun saveTransaction() {
        if (validateInput()) {
//            itemsRepository.insertTransaction(transactionUiState.transactionDetails.toTransaction())
        }
    }

    private fun validateInput(uiState: TransactionDetails = transactionUiState.transactionDetails): Boolean {
        return with(uiState) {
            true // 일단 true, (검증 안 함)
//            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }
}

data class TransactionUiState(
    val transactionDetails: TransactionDetails = TransactionDetails(),
    val isEntryValid: Boolean = false,
)

data class TransactionDetails(
    val id: Int = 0,
    val name: String = "", // 나중에 지울 것
    val category: String = "",
    val content: String = "",
    val timestamp: String = "",
    val amount: String = "",
    val assetType: String = "",
    val isIncome: Boolean = false,
)