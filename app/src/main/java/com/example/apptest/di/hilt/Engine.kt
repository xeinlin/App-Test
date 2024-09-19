package com.example.apptest.di.hilt

import android.util.Log
import javax.inject.Inject

interface Engine {

    fun start()

}

class EngineImpl @Inject constructor() : Engine {

    override fun start() {
        Log.d("TEST_LOG", "Engine is starting")
    }

}
