package com.example.mpagos.ui.selectedQuota.domain.usecase

import com.example.mpagos.ui.selectedQuota.data.repository.QuotaRepository
import javax.inject.Inject

class GetQuotaUseCase @Inject constructor(
    private val repository: QuotaRepository
) {
    lateinit var paymentMethodId: String
    lateinit var amount: String

    fun setData(amount: String, paymentMethodId: String): GetQuotaUseCase {
        this.paymentMethodId = paymentMethodId
        this.amount = amount
        return this
    }

    suspend operator fun invoke() = repository.getFee(amount, paymentMethodId)
}
