package com.example.productdescapp.domain.usecase

import com.example.productdescapp.data.model.ProductList
import com.example.productdescapp.data.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val repository: ProductRepository) {
    suspend operator fun invoke(): List<ProductList> {
        return repository.getProducts()
    }
}
