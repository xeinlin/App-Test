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
import com.example.apptest.databinding.ActivityThreeBinding

class ThreeActivity : BaseActivity<ActivityThreeBinding>() {

    companion object {

        fun getInstance(context: Context): Intent {
            return Intent(context, ThreeActivity::class.java)
        }

    }

    override val pageTitle: String
        get() = "Three Activity"

    override fun setUpViewBinding(layoutInflater: LayoutInflater): ActivityThreeBinding {
        return ActivityThreeBinding.inflate(layoutInflater)
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

   /* override fun onNewIntent(intent: Intent?) {
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

    private fun toThree() {
        val intent = ThreeActivity.getInstance(this)
        startActivity(intent)
    }
}