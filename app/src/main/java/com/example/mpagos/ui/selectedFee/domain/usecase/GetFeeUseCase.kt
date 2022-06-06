package com.example.mpagos.ui.selectedFee.domain.usecase

import com.example.mpagos.ui.selectedFee.data.repository.FeeRepository
import javax.inject.Inject

class GetFeeUseCase @Inject constructor(
    private val repository: FeeRepository
) {
    lateinit var paymentMethodId: String
    lateinit var amount: String

    fun setData(amount: String, paymentMethodId: String): GetFeeUseCase {
        this.paymentMethodId = paymentMethodId
        this.amount = amount
        return this
    }

    suspend operator fun invoke() = repository.getFee(amount, paymentMethodId)
}
