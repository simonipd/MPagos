package com.example.mpagos.ui.selectedPayerCost.di

import android.app.Application
import com.example.mpagos.R
import com.example.mpagos.ui.selectedPayerCost.data.remote.PayerCostApi
import com.example.mpagos.ui.selectedPayerCost.data.repository.PayerCostRepository
import com.example.mpagos.ui.selectedPayerCost.data.repository.PayerCostRepositoryImp
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
        api: PayerCostApi,
        @ApiKey apiKey: String
    ): PayerCostRepository {
        return PayerCostRepositoryImp(api, apiKey)
    }

    @Provides
    fun provideApiService(@ApiUrl apiUrl: String): PayerCostApi {
        return ApiServiceFactory.build(
            PayerCostApi::class.java,
            apiUrl
        )
    }
}
