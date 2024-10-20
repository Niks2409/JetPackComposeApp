package com.example.productdescapp.domain.usecase

import kotlinx.coroutines.delay

// Domain Layer - Use case for the splash screen
class SplashDelayUseCase {
    // This function handles the 2-second delay logic
    suspend fun executeSplashDelay() {
        delay(2000L)  // 2 seconds delay
    }
}