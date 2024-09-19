package com.example.apptest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.apptest.database.dao.CategoryDAO
import com.example.apptest.database.dao.ProductDAO
import com.example.apptest.database.entity.Category
import com.example.apptest.database.entity.Product

@Database(
    entities = [Product::class, Category::class],
    version = DatabaseConfigs.DATABASE_VERSION,
    exportSchema = true
)
abstract class MyDatabase : RoomDatabase() {

    abstract fun productDAO(): ProductDAO
    abstract fun categoryDAO(): CategoryDAO

}