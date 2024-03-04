package com.example.apptest.broadcast

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.apptest.databinding.ActivityBroadcastBinding

class BroadcastActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBroadcastBinding

    private lateinit var timeReceiver: TickTimeReceiver
    private lateinit var customReceiver: CustomReceiver

    private val customActionName = "customBoradcastAction"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBroadcastBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Broadcast Receiver"

        timeReceiver = TickTimeReceiver()
        customReceiver = CustomReceiver()

        binding.buttonCustomBroadcast.setOnClickListener {
            val intent = Intent()
            intent.setAction(customActionName)
            sendBroadcast(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter().also { it.addAction(Intent.ACTION_TIME_TICK) }
        registerReceiver(timeReceiver, filter)

        val customFilter = IntentFilter().also { it.addAction(customActionName) }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(customReceiver, customFilter, RECEIVER_EXPORTED)
        } else {
            registerReceiver(customReceiver, customFilter)
        }
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(timeReceiver)
        unregisterReceiver(customReceiver)
    }

    class AirplaneModeReceiver : BroadcastReceiver() {
        @SuppressLint("UnsafeProtectedBroadcastReceiver")
        override fun onReceive(context: Context?, p1: Intent?) {
            Log.d("TAG", "AirplaneModeReceiver Received")
            Log.d("TAG", "Broadcast has intent = ${p1 != null}")
            p1?.let {
                Log.d("TAG", "Is Airplane Mode )n = ${p1.getBooleanExtra("state", false)}")
            }

        }

    }

    class TickTimeReceiver : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            Log.d("TAG", "TickTimeReceiver Received")
        }
    }
}