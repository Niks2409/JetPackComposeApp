package com.example.productdescapp.di

import com.example.productdescapp.data.remote.ApiService
import com.example.productdescapp.data.repository.ProductRepository
import com.example.productdescapp.data.repository.SplashRepository
import com.example.productdescapp.domain.repo.ProductRepositoryImpl
import com.example.productdescapp.domain.usecase.SplashDelayUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)  // This module lives for the entire lifecycle of the application
object AppModule {

    // Provides a singleton instance of ApiService
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.jsonbin.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    // Provides a singleton instance of ProductRepository
    @Provides
    @Singleton
    fun provideCategoryRepository(apiService: ApiService): ProductRepository {
        return ProductRepositoryImpl(apiService)
    }

    // Provides a singleton instance of SplashDelayUseCase
    @Provides
    @Singleton
    fun provideSplashDelayUseCase(): SplashDelayUseCase {
        return SplashDelayUseCase()
    }

    // Provides a singleton instance of SplashRepository
    @Provides
    @Singleton
    fun provideSplashRepository(
        splashDelayUseCase: SplashDelayUseCase
    ): SplashRepository {
        return SplashRepository(splashDelayUseCase)
    }
}
