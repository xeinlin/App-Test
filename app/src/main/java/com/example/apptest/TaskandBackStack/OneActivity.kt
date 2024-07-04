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
import com.example.apptest.databinding.ActivityOneBinding

class OneActivity : BaseActivity<ActivityOneBinding>() {

    companion object {

        fun getInstance(context: Context): Intent {
            return Intent(context, OneActivity::class.java)
        }

    }

    override val pageTitle: String
        get() = "One Activity"

    override fun setUpViewBinding(layoutInflater: LayoutInflater): ActivityOneBinding {
        return ActivityOneBinding.inflate(layoutInflater)
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
            this.toThere()
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
        val intent = TwoActivity.getInstance(this)
        startActivity(intent)
    }

    private fun toThere() {
        val intent = ThreeActivity.getInstance(this)
        startActivity(intent)
    }


}