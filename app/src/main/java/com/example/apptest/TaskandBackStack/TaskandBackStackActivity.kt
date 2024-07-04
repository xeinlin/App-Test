package com.example.apptest.TaskandBackStack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.example.apptest.BaseActivity
import com.example.apptest.databinding.ActivityTaskandBackStackBinding

class TaskandBackStackActivity : BaseActivity<ActivityTaskandBackStackBinding>() {

    companion object {

        fun getInstance(context: Context): Intent {
            return Intent(context, TaskandBackStackActivity::class.java)
        }

    }

    override val pageTitle: String
        get() = "Task and Back Stack"

    override fun setUpViewBinding(layoutInflater: LayoutInflater): ActivityTaskandBackStackBinding {
        return ActivityTaskandBackStackBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*binding.btnActivityOne.setOnClickListener {
            this.toOne()
        }
        binding.btnActivityTwo.setOnClickListener {
            this.toTwo()
        }
        binding.btnActivityThree.setOnClickListener {
            this.toThree()
        }*/

    }

    /*private fun toOne() {
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
    }*/

}