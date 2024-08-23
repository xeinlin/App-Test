package com.example.apptest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.apptest.api.NetworkActivity
import com.example.apptest.broadcast.BroadcastActivity
import com.example.apptest.database.DatabaseActivity
import com.example.apptest.databinding.ActivityMainBinding
import com.example.apptest.intent.IntentActivity
import com.example.apptest.lifecycle.LifecycleActivity
import com.example.apptest.resource.ResourceActivity
import com.example.apptest.userinterface.UserInterfaceActivity
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
        Thread.sleep(3000)
        installSplashScreen()
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

        /*binding.btnTaskandBackStack.setOnClickListener {
            this.gotoTaskandBackStack()
        }*/

        binding.btnUserInterface.setOnClickListener {
            this.gotoUserInterface()
        }

        binding.btnResources.setOnClickListener {
            this.gotoResources()
        }

        /*binding.btnSharePreference.setOnClickListener {
            this.goToSharePreferenceActivity()
        }*/

        binding.btnDatabase.setOnClickListener {
            this.goToDatabaseActivity()
        }

        binding.btnNetworking.setOnClickListener {
            this.goToNetworkActivity()
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

    /*private fun gotoTaskandBackStack() {
        val intent = TaskandBackStackActivity.getInstance(this)
        startActivity(intent)
    }*/

    private fun gotoUserInterface() {
        val intent = UserInterfaceActivity.getInstance(this)
        startActivity(intent)
    }

    private fun gotoResources() {
        val intent = ResourceActivity.getInstance(this)
        startActivity(intent)
    }

    /*private fun goToSharePreferenceActivity() {
        val intent = SharePreferenceActivity.getInstance(this)
        startActivity(intent)
    }*/

    private fun goToDatabaseActivity() {
        val intent = DatabaseActivity.getInstance(this)
        startActivity(intent)
    }

    private fun goToNetworkActivity() {
        val intent = NetworkActivity.getInstance(this)
        startActivity(intent)
    }

}