package com.example.productdescapp.domain.model

// Data Layer - Sealed class for biometric authentication results
sealed class BiometricAuthResult {
    data object Success : BiometricAuthResult()
    data object Failure : BiometricAuthResult()
    data object Loading : BiometricAuthResult()
    data class Error(val message: String) : BiometricAuthResult()
}