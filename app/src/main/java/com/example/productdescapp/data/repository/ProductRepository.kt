package com.example.productdescapp.data.repository

import com.example.productdescapp.data.model.ProductList

// Data Layer - Interface for the repository
interface ProductRepository {
    suspend fun getProducts(): List<ProductList>
}
