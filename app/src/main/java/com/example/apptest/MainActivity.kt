package com.example.apptest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.example.apptest.broadcast.BroadcastActivity
import com.example.apptest.databinding.ActivityMainBinding
import com.example.apptest.intent.IntentActivity
import com.example.apptest.lifecycle.LifecycleActivity
import java.util.Date

class MainActivity : BaseActivity<ActivityMainBinding>() {

    companion object {

        fun getInstance(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }

    }

    override val pageTitle: String get() = "Main Menu"
    override fun setUpViewBinding(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLifecycle.setOnClickListener {
            this.gotoLifecycleScreen()
        }

        binding.btnBroadcast.setOnClickListener {
            this.gotoBroadcastScreen()
        }

        binding.btnIntent.setOnClickListener {
            this.gotoIntentActivity()
        }

    }

    private fun gotoLifecycleScreen() {
        val intent = LifecycleActivity.getInstance(this)
        startActivity(intent)
    }

    private fun gotoBroadcastScreen() {
        val intent = BroadcastActivity.getInstance(this)
        startActivity(intent)
    }

    private fun gotoIntentActivity() {
        val intent = IntentActivity.getInstance(this, Date().toString())
        startActivity(intent)
    }

}