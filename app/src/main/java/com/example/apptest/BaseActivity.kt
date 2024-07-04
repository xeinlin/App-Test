package com.example.apptest

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB
    abstract val pageTitle: String

    abstract fun setUpViewBinding(layoutInflater: LayoutInflater): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setUpViewBinding(layoutInflater)
        setContentView(binding.root)

        Log.d("TAG", "pageTitle = $pageTitle ")
        supportActionBar?.title = pageTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(this is MainActivity)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}