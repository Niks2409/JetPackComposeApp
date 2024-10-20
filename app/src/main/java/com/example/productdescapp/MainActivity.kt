package com.example.productdescapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.productdescapp.presentation.AppNavigator
import com.example.productdescapp.ui.theme.ProductDescAppTheme
import dagger.hilt.android.AndroidEntryPoint

// Entry point for the app
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductDescAppTheme {
                AppNavigator()  // Starts the navigation
            }
        }
    }
}
