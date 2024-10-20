package com.example.productdescapp.data.remote

import com.example.productdescapp.data.model.ProductList
import retrofit2.http.GET

// Data Layer - Retrofit interface
interface ApiService {
    @GET("/v3/b/6712540ae41b4d34e444ef58?meta=false")
    suspend fun getProducts(): List<ProductList>
}