package com.example.productdescapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productdescapp.data.repository.SplashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// Presentation Layer - ViewModel for the splash screen
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val splashRepository: SplashRepository
) : ViewModel() {

    fun startSplashTimer(onSplashFinished: () -> Unit) {
        viewModelScope.launch {
            splashRepository.getSplashDelay()
            onSplashFinished()
        }
    }
}