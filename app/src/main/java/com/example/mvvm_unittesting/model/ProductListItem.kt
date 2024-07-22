package com.example.mvvm_unittesting.model

import androidx.room.Entity

@Entity
data class ProductListItem(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val title: String
)