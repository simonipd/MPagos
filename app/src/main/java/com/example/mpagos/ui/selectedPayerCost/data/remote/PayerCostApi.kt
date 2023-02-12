package com.example.mpagos.ui.selectedPayerCost.data.remote

import com.example.mpagos.ui.selectedPayerCost.data.entity.FeeEntity
import retrofit2.http.GET
import retrofit2.http.Query


interface PayerCostApi {

    @GET("payment_methods/installments")
    suspend fun getFee(
        @Query("public_key") public_key: String,
        @Query("amount") amount: String,
        @Query("payment_method_id") payment_method_id: String
    ): List<FeeEntity>
}