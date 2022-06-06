package com.example.mpagos.ui.methodPay.data.repository

import com.example.mpagos.ui.main.domain.model.PaymentMethodElement
import io.reactivex.Observable

interface PaymentRepository {
    suspend fun getPaymentMethod(): List<PaymentMethodElement>
}