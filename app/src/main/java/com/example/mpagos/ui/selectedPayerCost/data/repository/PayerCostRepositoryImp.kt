package com.example.mpagos.ui.selectedPayerCost.data.repository

import com.example.mpagos.ui.selectedPayerCost.data.remote.PayerCostApi
import com.example.mpagos.ui.selectedPayerCost.data.repository.mapper.bankEntityToDomain
import com.example.mpagos.ui.selectedPayerCost.domain.model.Fee
import javax.inject.Inject

class PayerCostRepositoryImp @Inject constructor(
    private val api: PayerCostApi,
    private val apiKey: String
) : PayerCostRepository {

    override suspend fun getFee(amount: String, payment_method_id: String): List<Fee> {
        return api.getFee(apiKey, amount, payment_method_id).map { it.bankEntityToDomain() }
    }
}
