package com.example.mpagos.ui.selectedPayerCost.domain.usecase

import com.example.mpagos.ui.selectedPayerCost.data.repository.PayerCostRepository
import javax.inject.Inject

class GetPayerCostUseCase @Inject constructor(
    private val repository: PayerCostRepository
) {
    lateinit var paymentMethodId: String
    lateinit var amount: String

    fun setData(amount: String, paymentMethodId: String): GetPayerCostUseCase {
        this.paymentMethodId = paymentMethodId
        this.amount = amount
        return this
    }

    suspend operator fun invoke() = repository.getFee(amount, paymentMethodId)
}
