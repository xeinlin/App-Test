package com.example.apptest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.apptest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvGreeting.text = "Hi"

        Log.d("TAG","onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("TAG","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG","onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("TAG","onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG","onDestroy")
    }
}