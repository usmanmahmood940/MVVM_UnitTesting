package com.example.mvvm_unittesting.db
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvm_unittesting.model.ProductListItem

@Database(entities = [ProductListItem::class], version = 1)
abstract class FakerDB : RoomDatabase() {

    abstract fun getFakerDAO() : FakerDAO

}