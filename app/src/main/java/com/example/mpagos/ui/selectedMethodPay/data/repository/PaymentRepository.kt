package com.example.mpagos.ui.selectedMethodPay.data.repository

import com.example.mpagos.ui.main.domain.model.PaymentMethodElement

interface PaymentRepository {
    suspend fun getPaymentMethod(): List<PaymentMethodElement>
}