package com.example.productdescapp.ui.dashboard

import com.example.productdescapp.presentation.dashboard.DashboardScreenComponent
import com.example.productdescapp.presentation.viewmodel.DashboardViewModel

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import com.example.productdescapp.data.model.ProductList
import com.example.productdescapp.ui.theme.ProductDescAppTheme // Update if using a custom theme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
class DashboardScreenComponentTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    @MockK
    lateinit var viewModel: DashboardViewModel

    @Before
    fun setup() {
        hiltRule.inject()
        MockKAnnotations.init(this)
    }

    @Test
    fun dashboardScreen_displaysProducts() {
        // Mock the ViewModel and its data using MockK
        val mockProducts = listOf(
            ProductList(1, "Wireless Headphones", ""),
            ProductList(2, "Bluetooth Speaker", "")
        )
        val productsStateFlow = MutableStateFlow(mockProducts)

        every { viewModel.products } returns productsStateFlow

        // Set up the composable function for testing
        composeTestRule.setContent {
            ProductDescAppTheme { // If you're using a theme
                DashboardScreenComponent(
                    padding = PaddingValues(0.dp),
                    viewModel = viewModel // Use the mocked viewModel instance
                )
            }
        }

        // Assertions: Check if the products are displayed
        composeTestRule.onNodeWithText("Wireless Headphones").assertIsDisplayed()
        composeTestRule.onNodeWithText("Bluetooth Speaker").assertIsDisplayed()
    }
}