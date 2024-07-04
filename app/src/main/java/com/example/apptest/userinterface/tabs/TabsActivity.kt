package com.example.apptest.userinterface.tabs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.example.apptest.BaseActivity
import com.example.apptest.R
import com.example.apptest.databinding.ActivityTabsBinding
import com.google.android.material.tabs.TabLayout

class TabsActivity : BaseActivity<ActivityTabsBinding>() {

    companion object {

        fun getInstance(context: Context): Intent {
            return Intent(context, TabsActivity::class.java)
        }
    }

    override val pageTitle: String get() = "Tabs"

    override fun setUpViewBinding(layoutInflater: LayoutInflater): ActivityTabsBinding {
        return ActivityTabsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.supportActionBar?.hide()
        this.setupTabs()

    }

    private val homeFragment: HomeFragment by lazy { HomeFragment() }
    private val notiFragment: NotificationsFragment by lazy { NotificationsFragment() }
    private val settingsFragment: SettingFragment by lazy { SettingFragment() }

    private val tabSelectedListener by lazy {
        object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(p0: TabLayout.Tab?) {
                p0?.let {
                    selectedTab = it.tag as Tabs

                }
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabReselected(p0: TabLayout.Tab?) {}

        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.findFragmentByTag(fragment.javaClass.name)?.let {
            supportFragmentManager
                .beginTransaction()
                .show(it)
                .commit()
        } ?: run {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, fragment, fragment.javaClass.name)
                .commit()
        }
    }

    private fun hideFragment(fragment: Fragment) {
        supportFragmentManager.findFragmentByTag(fragment.javaClass.name)?.let {
            supportFragmentManager
                .beginTransaction()
                .hide(fragment)
                .commit()
        }
    }

    private fun setupTabs() {
        Tabs.entries.forEach {
            val tabItem = binding.tabLayout.newTab()
            tabItem.tag = it
            tabItem.text = getString(it.title)
            tabItem.icon = AppCompatResources.getDrawable(this, it.icon)
            binding.tabLayout.addTab(tabItem)
        }
        binding.tabLayout.addOnTabSelectedListener(tabSelectedListener)
        selectedTab = Tabs.entries.get(binding.tabLayout.selectedTabPosition)

    }

    private fun getFragmentByTab(tab: Tabs): Fragment {
        return when (tab) {
            Tabs.HOME -> homeFragment
            Tabs.NOTIFICATIONS -> notiFragment
            Tabs.SETTINGS -> settingsFragment

        }
    }

    private enum class Tabs(val title: Int, val icon: Int) {
        NOTIFICATIONS(R.string.tab_noti, R.drawable.ic_noti),
        HOME(R.string.tab_home, R.drawable.ic_home),
        SETTINGS(R.string.tab_setting, R.drawable.ic_setting)

    }

    private var selectedTab: Tabs? = null
        set(value) {
            if (field == value || value == null) return

            field?.let { hideFragment(getFragmentByTab(it)) }
            showFragment(getFragmentByTab(value))

            field = value

        }
        get() {
            return field
        }

}