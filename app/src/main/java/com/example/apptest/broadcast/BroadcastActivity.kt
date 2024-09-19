package com.example.apptest.broadcast

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.apptest.BaseActivity
import com.example.apptest.databinding.ActivityBroadcastBinding

class BroadcastActivity : BaseActivity<ActivityBroadcastBinding>() {

    companion object {

        const val CUSTOM_ACTION_NAME = "customBroadcastAction"

        fun getInstance(context: Context): Intent {
            return Intent(context, BroadcastActivity::class.java)
        }

    }

    override val pageTitle: String get() = "Broadcast Receiver"

    override fun setUpViewBinding(layoutInflater: LayoutInflater): ActivityBroadcastBinding {
        return ActivityBroadcastBinding.inflate(layoutInflater)
    }

    private lateinit var timeReceiver: TickTimeReceiver
    private lateinit var customReceiver: CustomReceiver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        timeReceiver = TickTimeReceiver()
        customReceiver = CustomReceiver()

        binding.buttonCustomBroadcast.setOnClickListener {
            val intent = Intent()
            intent.setAction(CUSTOM_ACTION_NAME)
            sendBroadcast(intent)
        }
    }


    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onResume() {
        super.onResume()

        val filter: IntentFilter = IntentFilter().also { it.addAction(Intent.ACTION_TIME_TICK) }
        registerReceiver(timeReceiver, filter)

        val customFilter: IntentFilter = IntentFilter().also { it.addAction(CUSTOM_ACTION_NAME) }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(customReceiver, customFilter, RECEIVER_NOT_EXPORTED)
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

        interface AirplaneModeListener {
            fun onAirplaneModeChanged(isAirplaneModeOn: Boolean)
        }

        companion object {
            var listeners: ArrayList<AirplaneModeListener> = ArrayList()
        }

        @SuppressLint("UnsafeProtectedBroadcastReceiver")
        override fun onReceive(p0: Context?, p1: Intent?) {
            Log.d("TAG", "AirplaneModeReceiver Received")
            p1?.let { intent ->
                val isAirplaneModeOn = intent.getBooleanExtra("state", false)
                listeners.forEach {
                    it.onAirplaneModeChanged(isAirplaneModeOn)
                }
                Log.d("TAG", "Is Airplane Mode On = $isAirplaneModeOn")
            }
        }

    }

    class TickTimeReceiver : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            Log.d("TAG", "TickTimeReceiver Received")

        }

    }

}