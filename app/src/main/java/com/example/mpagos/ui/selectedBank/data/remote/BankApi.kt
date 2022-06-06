package com.example.mpagos.ui.selectedBank.data.remote

import com.example.mpagos.ui.selectedBank.data.entity.BankEntity
import retrofit2.http.GET
import retrofit2.http.Query


interface BankApi {

    @GET("payment_methods/card_issuers")
    suspend fun getBank(
        @Query("public_key") public_key: String,
        @Query("payment_method_id") payment_method_id: String
    ): List<BankEntity>
}