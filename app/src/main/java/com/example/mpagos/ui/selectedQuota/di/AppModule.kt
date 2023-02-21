package com.example.mpagos.ui.selectedQuota.di

import android.app.Application
import com.example.mpagos.R
import com.example.mpagos.ui.selectedQuota.data.remote.QuotaApi
import com.example.mpagos.ui.selectedQuota.data.repository.QuotaRepository
import com.example.mpagos.ui.selectedQuota.data.repository.QuotaRepositoryImp
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
        api: QuotaApi,
        @ApiKey apiKey: String
    ): QuotaRepository {
        return QuotaRepositoryImp(api, apiKey)
    }

    @Provides
    fun provideApiService(@ApiUrl apiUrl: String): QuotaApi {
        return ApiServiceFactory.build(
            QuotaApi::class.java,
            apiUrl
        )
    }
}
