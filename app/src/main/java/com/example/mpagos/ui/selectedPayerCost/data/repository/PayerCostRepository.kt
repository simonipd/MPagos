package com.example.mpagos.ui.selectedPayerCost.data.repository

import com.example.mpagos.ui.selectedPayerCost.domain.model.PayerCostResponse

interface PayerCostRepository {
    suspend fun getFee(amount: String, payment_method_id: String): List<PayerCostResponse>
}
