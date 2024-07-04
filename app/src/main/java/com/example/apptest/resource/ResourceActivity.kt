package com.example.apptest.resource

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDelegate
import com.example.apptest.BaseActivity
import com.example.apptest.R
import com.example.apptest.databinding.ActivityResourceBinding
import com.example.apptest.helper.showToast

class ResourceActivity : BaseActivity<ActivityResourceBinding>() {

    companion object {

        fun getInstance(context: Context): Intent {
            return Intent(context, ResourceActivity::class.java)
        }
    }

    override val pageTitle: String get() = getString(R.string.resources)

    override fun setUpViewBinding(layoutInflater: LayoutInflater): ActivityResourceBinding {
        return ActivityResourceBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvDynamicString.text = getString(
            R.string.label_dynamic_string,
            "MgMg", "apples", "bananas", 3, 5
        )

        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )

        }

    }

    private var isTheme = false // white

    private fun changeTheme() {
        showToast("Change Theme")

        if (isTheme) {
            setTheme(R.style.Light_Theme)
        } else {
            setTheme(R.style.Dark_theme)
        }
        isTheme = !isTheme
    }


}
