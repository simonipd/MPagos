package com.example.mpagos.ui.selectedBank.data.repository

import com.example.mpagos.ui.selectedBank.domain.model.Bank

interface BankRepository {
    suspend fun getBank(payment_method_id: String): List<Bank>
}