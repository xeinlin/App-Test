package com.example.apptest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
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

        supportActionBar?.title = "Main Menu"

        binding.btnLifecycle.setOnClickListener {
            this.gotoLifecycleScreen()
        }

        binding.btnBroadcast.setOnClickListener {
            this.gotoBroadcastScreen()
        }
    }

    private fun gotoLifecycleScreen() {
        //Toast.makeText(this, "Go to Lifecycle screen", Toast.LENGTH_LONG).show()
        val intent = Intent(this, LifecycleActivity::class.java)
        startActivity(intent)
    }

    private fun gotoBroadcastScreen() {
        //Toast.makeText(this, "Go to broadcast receiver", Toast.LENGTH_LONG).show()
        val intent = Intent(this, BroadcastActivity::class.java)
        startActivity(intent)
    }
}