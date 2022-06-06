package com.example.mpagos.ui.selectedFee.data.repository

import com.example.mpagos.ui.selectedFee.data.remote.FeeApi
import com.example.mpagos.ui.selectedFee.data.repository.mapper.bankEntityToDomain
import com.example.mpagos.ui.selectedFee.domain.model.Fee
import javax.inject.Inject

class FeeRepositoryImp @Inject constructor(
    private val api: FeeApi,
    private val apiKey: String
) : FeeRepository {

    override suspend fun getFee(amount: String, payment_method_id: String): List<Fee> {
        return api.getFee(apiKey, amount, payment_method_id).map { it.bankEntityToDomain() }
    }
}
