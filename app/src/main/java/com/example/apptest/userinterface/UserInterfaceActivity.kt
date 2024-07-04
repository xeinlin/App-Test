package com.example.apptest.userinterface

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.example.apptest.BaseActivity
import com.example.apptest.databinding.ActivityUserInterfaceBinding
import com.example.apptest.userinterface.drawer.DrawerActivity
import com.example.apptest.userinterface.gridview.GridViewActivity
import com.example.apptest.userinterface.listview.ListViewActivity
import com.example.apptest.userinterface.listview.SpinnerViewActivity
import com.example.apptest.userinterface.recyclerview.RecyclerViewActivity
import com.example.apptest.userinterface.tabs.TabsActivity

class UserInterfaceActivity : BaseActivity<ActivityUserInterfaceBinding>() {

    companion object {

        fun getInstance(context: Context): Intent {
            return Intent(context, UserInterfaceActivity::class.java)
        }
    }

    override val pageTitle: String
        get() = "User Interface"

    override fun setUpViewBinding(layoutInflater: LayoutInflater): ActivityUserInterfaceBinding {
        return ActivityUserInterfaceBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnDrawer.setOnClickListener { this.gotoDrawerActivity() }
        binding.btnTabs.setOnClickListener { this.gotoTabsActivity() }
        binding.btnListview.setOnClickListener { this.gotoListViewActivity() }
        binding.btnSpinner.setOnClickListener { this.gotoSpinner() }
        binding.btnGridview.setOnClickListener { this.gotoGridView() }
        binding.btnRecyclerview.setOnClickListener { this.gotoRecyclerView() }

    }

    private fun gotoDrawerActivity() {
        val intent = DrawerActivity.getInstance(this)
        startActivity(intent)
    }

    private fun gotoTabsActivity() {
        val intent = TabsActivity.getInstance(this)
        startActivity(intent)
    }

    private fun gotoListViewActivity() {
        val intent = ListViewActivity.getInstance(this)
        startActivity(intent)
    }

    private fun gotoSpinner() {
        val intent = SpinnerViewActivity.getInstance(this)
        startActivity(intent)
    }

    private fun gotoGridView() {
        val intent = GridViewActivity.getInstance(this)
        startActivity(intent)
    }

    private fun gotoRecyclerView() {
        val intent = RecyclerViewActivity.getInstance(this)
        startActivity(intent)
    }


}