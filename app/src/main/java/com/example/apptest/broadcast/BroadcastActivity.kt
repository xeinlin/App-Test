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
import android.view.LayoutInflater
import com.example.apptest.BaseActivity
import com.example.apptest.databinding.ActivityBroadcastBinding
import com.example.apptest.lifecycle.LifecycleActivity

class BroadcastActivity : BaseActivity<ActivityBroadcastBinding>() {

    companion object {

        const val CUSTOM_ACTION_NAME = "customBroadcastAction"

        fun getInstance(context: Context): Intent {
            return Intent(context, BroadcastActivity::class.java)
        }

    }

    private lateinit var timeReceiver: TickTimeReceiver
    private lateinit var customReceiver: CustomReceiver
    private val customActionName = "customBoradcastAction"
    override val pageTitle: String get() = "Broadcast Receiver"

    override fun setUpViewBinding(layoutInflater: LayoutInflater): ActivityBroadcastBinding {
        return ActivityBroadcastBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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