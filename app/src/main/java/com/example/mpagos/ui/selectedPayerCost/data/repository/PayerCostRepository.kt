package com.example.mpagos.ui.selectedPayerCost.data.repository

import com.example.mpagos.ui.selectedPayerCost.domain.model.Fee

interface PayerCostRepository {
    suspend fun getFee(amount: String, payment_method_id: String): List<Fee>
}
