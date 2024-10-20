package com.example.productdescapp.presentation.auth

import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity.RESULT_OK
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.productdescapp.MainActivity
import com.example.productdescapp.R
import com.example.productdescapp.presentation.auth.BioMetricPromptManager.BioMetricResult

@Composable
fun AuthScreen(navController: NavController) {
    val context = LocalContext.current as MainActivity // Get the activity context here
    val promptManager by lazy { BioMetricPromptManager(context) }

    val bioMetricResult by promptManager.promptResult.collectAsState(initial = null)

    val enrollLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                promptManager.showBioMetricPrompt(
                    "Biometric Authentication", "Authenticate using your fingerprint"
                )
            }
        }

    // Use LaunchedEffect with proper dependency
    LaunchedEffect(bioMetricResult) {
        if (bioMetricResult is BioMetricResult.AuthNotSet && Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                putExtra(
                    Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                    BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                )
            }
            enrollLauncher.launch(enrollIntent)
        } else if (bioMetricResult is BioMetricResult.AuthSuccess) {
            // Navigate to the dashboard screen
            navController.navigate("dashboard") {
                popUpTo("auth") { inclusive = true } // Optionally pop up to auth
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BiometricAuthButton(bioMetricResult, onClick = {
            promptManager.showBioMetricPrompt(
                "Biometric Authentication", "Authenticate using your fingerprint"
            )
        })
    }
}

@Composable
fun BiometricAuthButton(
    bioMetricResult: BioMetricResult?,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF6200EE)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.fingerprint), // Replace with your drawable resource
            contentDescription = "Biometric Icon",
            modifier = Modifier.size(24.dp),
            tint = Color.White
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(text = "Authenticate Using Biometrics", color = Color.White)

        // Handle biometric result display
        bioMetricResult?.let {
            when (it) {
                BioMetricResult.AuthFailed -> Text("Authentication Failed", color = Color.Red)
                is BioMetricResult.AuthError -> Text("Error: ${it.error}", color = Color.Red)
                BioMetricResult.AuthNotSet -> Text("Biometric Auth Not Set", color = Color.Red)
                BioMetricResult.FeatureUnavailable -> Text("Feature Unavailable", color = Color.Red)
                BioMetricResult.HardwareUnavailable -> Text("Hardware Unavailable", color = Color.Red)
                else -> Unit // Handle other cases if needed
            }
        }
    }
}
