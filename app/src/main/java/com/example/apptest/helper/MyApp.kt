package com.example.apptest.helper

import android.app.Application
import androidx.room.Room
import com.example.apptest.database.MyDatabase

class MyApp : Application() {

    lateinit var db: MyDatabase


    override fun onCreate() {
        super.onCreate()

        this.initDatabase()
    }

    private fun initDatabase() {
        this.db = Room.databaseBuilder(
            applicationContext, MyDatabase::class.java, "my-database"
        ).allowMainThreadQueries().build()
    }

}