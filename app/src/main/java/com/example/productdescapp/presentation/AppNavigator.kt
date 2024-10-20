package com.example.productdescapp.presentation

// Presentation layer: Navigation between screens
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.productdescapp.presentation.auth.AuthScreen
import com.example.productdescapp.presentation.dashboard.DashboardScreenComponent
import com.example.productdescapp.presentation.splash.SplashScreenComponent

// Navigation graph for the app
@Composable
fun AppNavigator() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreenComponent(onSplashFinished = {
                navController.navigate("auth") {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }

        // access auth screen
        composable("auth") {
            AuthScreen(navController)
        }

        // access dashboard screen
        composable("dashboard") {
            Scaffold(topBar = {
                TopAppBar(
                    backgroundColor = Color.Cyan,
                    contentColor = MaterialTheme.colors.onPrimary,
                    elevation = 12.dp,
                    title = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Product",
                                fontWeight = FontWeight.Bold,// Set text to bold
                                fontSize = 20.sp, // Set font size here
                                textAlign = TextAlign.Center,// Center text alignment
                                modifier = Modifier.padding(top = 30.dp)

                            )
                        }
                    }
                )
            }, content = { paddingValues ->
                DashboardScreenComponent(paddingValues)
            })
        }
    }
}
