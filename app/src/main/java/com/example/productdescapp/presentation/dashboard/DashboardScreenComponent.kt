package com.example.productdescapp.presentation.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.productdescapp.presentation.viewmodel.DashboardViewModel

@Composable
fun DashboardScreenComponent(padding:PaddingValues,viewModel: DashboardViewModel = hiltViewModel()) {
    val products by viewModel.products.collectAsState()

    // Display the list of products
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(top = 60.dp, bottom = 50.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products) { product ->
            ProductItem(product)
        }
    }
}
