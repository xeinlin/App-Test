package com.example.apptest.userinterface.drawer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.example.apptest.BaseActivity
import com.example.apptest.R
import com.example.apptest.databinding.ActivityDrawerBinding

class DrawerActivity : BaseActivity<ActivityDrawerBinding>() {

    companion object {
        fun getInstance(context: Context): Intent {
            return Intent(context, DrawerActivity::class.java)
        }
    }

    override val pageTitle: String = "Drawer"

    override fun setUpViewBinding(layoutInflater: LayoutInflater): ActivityDrawerBinding {
        return ActivityDrawerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnOpen.setOnClickListener {
            binding.drawerLayout.open()
        }

        // binding.drawer.setNavigationItemSelectedListener { true }

        binding.drawer.setNavigationItemSelectedListener { menuItem ->
            //val fragmentManager = supportFragmentManager
            //val transaction = fragmentManager.beginTransaction()

            when (menuItem.itemId) {
                R.id.send -> {
                    //val sendFragment = drawerSendFragment()
                    //transaction.replace(R.id.container_one, sendFragment,sendFragment.javaClass.name)
                    showFragment(SendFragment())
                }

                R.id.draft -> {
                    showFragment(DraftFragment())
                }

                R.id.trash -> {
                    showFragment(TrashFragment())
                }
            }
            binding.drawerLayout.close()
            //transaction.commit()
            menuItem.isChecked = true
            true
        }


    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container_one, fragment, fragment.javaClass.name)
            .commit()
    }


}