package com.example.mpagos.ui.selectedBank.data.repository

import com.example.mpagos.ui.selectedBank.data.remote.BankApi
import com.example.mpagos.ui.selectedBank.data.repository.mapper.bankEntityToDomain
import com.example.mpagos.ui.selectedBank.domain.model.Bank
import javax.inject.Inject

class BankRepositoryImp @Inject constructor(
    private val api: BankApi,
    private val apiKey: String
) : BankRepository {
    override suspend fun getBank(payment_method_id: String): List<Bank> {
        return api.getBank(apiKey, payment_method_id).map { it.bankEntityToDomain() }
    }
}