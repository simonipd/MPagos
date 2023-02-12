package com.example.mpagos.ui.selectedMethodPay.di

import android.app.Application
import com.example.mpagos.R
import com.example.mpagos.ui.selectedMethodPay.data.remote.PayApi
import com.example.mpagos.ui.selectedMethodPay.data.repository.PaymentRepository
import com.example.mpagos.ui.selectedMethodPay.data.repository.PaymentRepositoryImp
import com.example.mpagos.util.ApiServiceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    @ApiKey
    fun provideApiKey(app: Application): String = app.getString(R.string.api_key)


    @Provides
    @Singleton
    @ApiUrl
    fun provideApiUrl(): String = "https://api.mercadopago.com/v1/"

    @Provides
    fun provideRepository(
        api: PayApi,
        @ApiKey apiKey: String
    ): PaymentRepository {
        return PaymentRepositoryImp(api, apiKey)
    }

    @Provides
    fun provideApiService(@ApiUrl apiUrl: String): PayApi {
        return ApiServiceFactory.build(
            PayApi::class.java,
            apiUrl
        )
    }
}