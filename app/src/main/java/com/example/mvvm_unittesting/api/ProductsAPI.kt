package com.example.mvvm_unittesting.api

import com.example.mvvm_unittesting.model.ProductListItem
import retrofit2.Response
import retrofit2.http.GET

interface ProductsAPI {

    @GET("/products")
    suspend fun getProducts() : Response<List<ProductListItem>>

}