package com.example.apptest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.apptest.broadcast.BroadcastActivity
import com.example.apptest.databinding.ActivityMainBinding
import com.example.apptest.lifecycle.LifecycleActivity

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
    }

    private fun gotoLifecycleScreen() {
        //Toast.makeText(this, "Go to Lifecycle screen", Toast.LENGTH_LONG).show()
        val intent = LifecycleActivity.getInstance(this)
        startActivity(intent)
    }

    private fun gotoBroadcastScreen() {
        //Toast.makeText(this, "Go to broadcast receiver", Toast.LENGTH_LONG).show()
        val intent = BroadcastActivity.getInstance(this)
        startActivity(intent)
    }
}