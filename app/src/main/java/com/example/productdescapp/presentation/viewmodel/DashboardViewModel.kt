package com.example.productdescapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productdescapp.data.model.ProductList
import com.example.productdescapp.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// Presentation Layer - ViewModel for the dashboard screen
@HiltViewModel
class DashboardViewModel @Inject constructor(private val getCategoriesUseCase: GetProductsUseCase) : ViewModel() {

    private val _products = MutableStateFlow<List<ProductList>>(emptyList())
    val products: StateFlow<List<ProductList>> get() = _products

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            _products.value = getCategoriesUseCase()
        }
    }
}
