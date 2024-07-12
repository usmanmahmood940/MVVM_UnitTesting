package com.example.mvvm_unittesting.repository

import com.example.mvvm_unittesting.util.NetworkResult
import com.example.mvvm_unittesting.model.ProductListItem
import com.example.mvvm_unittesting.api.ProductsAPI

class ProductRepository(private val productsAPI: ProductsAPI) {

    suspend fun getProducts(): NetworkResult<List<ProductListItem>> {
        val response = productsAPI.getProducts()
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkResult.Success(responseBody)
            } else {
                NetworkResult.Error("Something went wrong")
            }
        } else {
            NetworkResult.Error("Something went wrong")
        }
    }
}