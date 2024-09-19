package com.example.apptest.di.hilt

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.apptest.databinding.ActivityHiltBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HiltActivity : AppCompatActivity() {

    companion object {

        fun getInstance(context: Context): Intent {
            return Intent(context, HiltActivity::class.java)
        }

    }

    @Inject
    lateinit var car: HiltCar

    private lateinit var binding: ActivityHiltBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHiltBinding.inflate(layoutInflater)
        setContentView(binding.root)

        car.drive()
    }

}