package com.example.mpagos.ui.selectedQuota.data.repository

import com.example.mpagos.ui.selectedQuota.domain.model.PayerCostResponse

interface QuotaRepository {
    suspend fun getFee(amount: String, payment_method_id: String): List<PayerCostResponse>
}
