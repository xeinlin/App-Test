package com.example.apptest.TaskandBackStack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apptest.BaseActivity
import com.example.apptest.R
import com.example.apptest.databinding.ActivityTwoBinding

class TwoActivity : BaseActivity<ActivityTwoBinding>() {

    companion object {

        fun getInstance(context: Context): Intent {
            return Intent(context, TwoActivity::class.java)
        }

    }

    override val pageTitle: String
        get() = "Two Activity"

    override fun setUpViewBinding(layoutInflater: LayoutInflater): ActivityTwoBinding {
        return ActivityTwoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnActivityOne.setOnClickListener {
            this.toOne()
        }
        binding.btnActivityTwo.setOnClickListener {
            this.toTwo()
        }
        binding.btnActivityThree.setOnClickListener {
            this.toThree()
        }

    }

    /*override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Toast.makeText(this, "New Intent", Toast.LENGTH_LONG).show()
    }*/

    private fun toOne() {
        val intent = OneActivity.getInstance(this)
        startActivity(intent)
    }

    private fun toTwo() {
        val intent = getInstance(this)
        startActivity(intent)
    }

    private fun toThree() {
        val intent = ThreeActivity.getInstance(this)
        startActivity(intent)
    }
}