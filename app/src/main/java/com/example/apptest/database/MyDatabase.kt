package com.example.apptest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.apptest.database.dao.ProductDAO
import com.example.apptest.database.entity.Product

@Database(
    entities = [Product::class],
    version = 1,
)
abstract class MyDatabase : RoomDatabase() {

    abstract fun productDAO(): ProductDAO

}