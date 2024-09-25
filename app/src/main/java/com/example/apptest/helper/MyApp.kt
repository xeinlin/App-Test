package com.example.apptest.helper

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import dagger.hilt.android.HiltAndroidApp
import com.example.apptest.database.DatabaseConfigs
import com.example.apptest.database.MyDatabase
import com.example.apptest.database.migration.Migration1to2
import com.example.apptest.di.koin.appModule
import com.example.apptest.di.koin.networkModule
import com.example.apptest.di.koin.repoModule
import com.example.apptest.di.koin.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class MyApp : Application() {

    lateinit var db: MyDatabase


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(appModule, networkModule, repoModule, vmModule)
        }

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