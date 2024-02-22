package com.example.apptest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.addCallback
import com.example.apptest.databinding.ActivityMainBinding
import java.util.Date
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("TAG", "onCreate")
        binding.tvGreeting.text = "Hi"

        binding.btnShowTime.setOnClickListener {
            this.count++
            this.showCount()
        }
        binding.btnExit.setOnClickListener {
            finish()
        }
        onBackPressedDispatcher.addCallback {
            Log.d("TAG", "onBackPressedDispatcher")
            finish()
        }
        this.showCount()
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

    private fun showCount() {
        binding.tvGreeting.text = this.count.toString()
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