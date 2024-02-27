package com.example.apptest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.addCallback
import com.example.apptest.databinding.ActivityBroadcastBinding
import com.example.apptest.databinding.ActivityLifecycleBinding
import com.example.apptest.databinding.ActivityMainBinding

class LifecycleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLifecycleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", "onCreate")

        binding = ActivityLifecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Activity Lifecycle"

        onBackPressedDispatcher.addCallback {
            Log.d("TAG", "onBackPressedDispatcher")
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.d("TAG", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        Log.d("TAG", "onRestoreInstanceState")

    }

    override fun onStart() {
        super.onStart()
        Log.d("TAG", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("TAG", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "onDestroy")
    }

}