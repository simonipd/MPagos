package com.example.mpagos.ui.selectedFee.di

import android.app.Application
import com.example.mpagos.R
import com.example.mpagos.ui.selectedBank.data.remote.BankApi
import com.example.mpagos.ui.selectedFee.data.remote.FeeApi
import com.example.mpagos.ui.selectedFee.data.repository.FeeRepository
import com.example.mpagos.ui.selectedFee.data.repository.FeeRepositoryImp
import com.example.mpagos.ui.util.ApiServiceFactory
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
        api: FeeApi,
        @ApiKey apiKey: String
    ): FeeRepository {
        return FeeRepositoryImp(api, apiKey)
    }

    @Provides
    fun provideApiService(@ApiUrl apiUrl: String): FeeApi {
        return ApiServiceFactory.build(
            FeeApi::class.java,
            apiUrl
        )
    }
}
