package com.example.apptest.di.hilt

import android.util.Log
import javax.inject.Inject

class HiltCar @Inject constructor(private val engine: Engine) {

    fun drive() {
        engine.start()
        Log.d("TEST_LOG", "Car is driving")
    }

}