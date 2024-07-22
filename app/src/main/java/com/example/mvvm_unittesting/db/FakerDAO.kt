package com.example.mvvm_unittesting.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvm_unittesting.model.ProductListItem

@Dao
interface FakerDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProducts(products : List<ProductListItem>)

    @Query("SELECT * FROM ProductListItem")
    suspend fun getProducts() : List<ProductListItem>

}