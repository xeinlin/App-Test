package com.example.apptest.helper

import android.app.Application
import androidx.room.Room
import com.example.apptest.database.DatabaseConfigs
import com.example.apptest.database.MyDatabase
import com.example.apptest.database.migration.Migration1to2

class MyApp : Application() {

    lateinit var db: MyDatabase


    override fun onCreate() {
        super.onCreate()

        this.initDatabase()
    }

    private fun initDatabase() {
        this.db = Room.databaseBuilder(
            applicationContext,
            MyDatabase::class.java,
            DatabaseConfigs.DATABASE_NAME
        )
            .addMigrations(Migration1to2())
            .allowMainThreadQueries()
            .build()
    }

}