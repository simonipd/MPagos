package com.example.mpagos.ui.methodPay.data.remote

import com.example.mpagos.ui.main.data.entity.PaymentMethodElementEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface PayApi {

    //?public_key=444a9ef5-8a6b-429f-abdf-587639155d88
    @GET("payment_methods")
    suspend fun getPaymentMethods(
        @Query("public_key") public_key: String
    ): List<PaymentMethodElementEntity>
}