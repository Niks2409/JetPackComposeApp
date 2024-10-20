package com.example.productdescapp.data.repository

import com.example.productdescapp.domain.usecase.SplashDelayUseCase

// Data Layer - Repository for the splash screen
class SplashRepository(private val splashDelayUseCase: SplashDelayUseCase) {

    // This function wraps the use case to decouple the presentation logic
    suspend fun getSplashDelay() {
        splashDelayUseCase.executeSplashDelay()
    }
}