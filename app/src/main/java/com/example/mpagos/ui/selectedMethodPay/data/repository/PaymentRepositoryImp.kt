package com.example.mpagos.ui.selectedMethodPay.data.repository

import com.example.mpagos.ui.selectedMethodPay.data.remote.PayApi
import com.example.mpagos.ui.main.data.reporitory.mapper.paymentMethodElementEntityToDomain
import com.example.mpagos.ui.main.domain.model.PaymentMethodElement
import javax.inject.Inject

class PaymentRepositoryImp @Inject constructor(
    private val api: PayApi,
    private val apiKey: String
) : PaymentRepository {

    override suspend fun getPaymentMethod(): List<PaymentMethodElement> {
        return api.getPaymentMethods(apiKey).map { it.paymentMethodElementEntityToDomain() }
    }
}