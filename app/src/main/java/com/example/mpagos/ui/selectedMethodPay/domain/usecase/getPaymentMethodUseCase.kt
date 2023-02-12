package com.example.mpagos.ui.main.domain.usecase

import com.example.mpagos.ui.selectedMethodPay.data.repository.PaymentRepository
import javax.inject.Inject

class GetPaymentMethodUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository
) {
    suspend operator fun invoke() = paymentRepository.getPaymentMethod()
}