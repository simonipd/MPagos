package com.example.mpagos.ui.selectedQuota.data.repository

import com.example.mpagos.ui.selectedQuota.data.remote.QuotaApi
import com.example.mpagos.ui.selectedQuota.data.repository.mapper.bankEntityToDomain
import com.example.mpagos.ui.selectedQuota.domain.model.PayerCostResponse
import javax.inject.Inject

class QuotaRepositoryImp @Inject constructor(
    private val api: QuotaApi,
    private val apiKey: String
) : QuotaRepository {

    override suspend fun getFee(amount: String, payment_method_id: String): List<PayerCostResponse> {
        return api.getFee(apiKey, amount, payment_method_id).map { it.bankEntityToDomain() }
    }
}
