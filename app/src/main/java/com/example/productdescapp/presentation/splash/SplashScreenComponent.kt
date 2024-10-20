package com.example.productdescapp.presentation.splash

// Presentation layer: Splash Screen UI with Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.productdescapp.R
import com.example.productdescapp.presentation.viewmodel.SplashViewModel


@Composable
fun SplashScreenComponent(
    viewModel: SplashViewModel = hiltViewModel(),
    onSplashFinished: () -> Unit
) {

    // Trigger splash timer in ViewModel
    LaunchedEffect(Unit) {
        viewModel.startSplashTimer(onSplashFinished)
    }

    // Display the splash screen content with an image
    Box(
        modifier = Modifier
            .fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.product), // Replace with your image resource
            contentDescription = "product image",
            contentScale = ContentScale.FillBounds, // Ensures the image scales to cover the entire Box
            modifier = Modifier.fillMaxSize() // Fills the entire size of the Box
        )
    }
}
