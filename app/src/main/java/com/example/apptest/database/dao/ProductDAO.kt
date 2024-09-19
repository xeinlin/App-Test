package com.example.apptest.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.apptest.database.entity.Product

@Dao
interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: Product)

    @Insert
    fun insertAll(vararg product: Product)

    @Insert
    fun insertAll(products: List<Product>)

    @Query("SELECT * FROM product")
    fun getAllProducts(): List<Product>

    @Delete
    fun delete(product: Product)

    @Update
    fun update(product: Product)

    @Query("SELECT * FROM product WHERE price <= :price ORDER BY name")
    fun getProductsByMaxPrice(price: Double): List<Product>

}