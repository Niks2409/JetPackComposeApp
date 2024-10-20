package com.example.productdescapp.domain.repo

import com.example.productdescapp.data.model.ProductList
import com.example.productdescapp.data.remote.ApiService
import com.example.productdescapp.data.repository.ProductRepository
import javax.inject.Inject

// Data Layer - Concrete implementation of the repository interface
class ProductRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ProductRepository {

    override suspend fun getProducts(): List<ProductList> {
        // Implementation might involve getting data from the network or database
        return apiService.getProducts() // For example, getting data from an API
    }
}
