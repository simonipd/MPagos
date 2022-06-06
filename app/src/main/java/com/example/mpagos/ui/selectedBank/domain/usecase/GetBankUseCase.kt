package com.example.mpagos.ui.selectedBank.domain.usecase

import com.example.mpagos.ui.selectedBank.data.repository.BankRepository
import javax.inject.Inject

class GetBankUseCase @Inject constructor(
    private val repository: BankRepository
){
    lateinit var paymentMethodId: String

    fun setData(paymentMethodId: String): GetBankUseCase{
        this.paymentMethodId = paymentMethodId
        return this
    }

    suspend operator fun invoke() = repository.getBank(paymentMethodId)
}