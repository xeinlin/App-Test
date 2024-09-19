package com.example.apptest.observer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.apptest.BaseActivity
import com.example.apptest.R
import com.example.apptest.broadcast.BroadcastActivity
import com.example.apptest.databinding.ActivityObserverBinding
import com.example.apptest.databinding.FragmentObserverBinding
import com.example.apptest.helper.NoTitleBar
import com.example.apptest.userinterface.fragments.BaseFragment

class ObserverActivity : BaseActivity<ActivityObserverBinding>() {

    companion object {
        fun getInstance(context: Context): Intent {
            return Intent(context, ObserverActivity::class.java)
        }
    }

    override val pageTitle: String = "Observer Pattern"
    override fun setUpViewBinding(layoutInflater: LayoutInflater): ActivityObserverBinding {
        return ActivityObserverBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addFragment(ObserverFragment(), R.id.container_one)
        addFragment(ObserverFragment(), R.id.container_two)
        addFragment(ObserverFragment(), R.id.container_three)
    }

    private fun addFragment(fragment: Fragment, containerId: Int) {
        supportFragmentManager
            .beginTransaction()
            .add(containerId, fragment)
            .commit()
    }

    class ObserverFragment : BaseFragment<FragmentObserverBinding>(), NoTitleBar,
        BroadcastActivity.AirplaneModeReceiver.AirplaneModeListener {

        override fun setupViewBinding(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): FragmentObserverBinding {
            return FragmentObserverBinding.inflate(inflater, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            BroadcastActivity.AirplaneModeReceiver.listeners.add(this)
        }

        override fun onAirplaneModeChanged(isAirplaneModeOn: Boolean) {
            binding.tvHello.text = if (isAirplaneModeOn) "Airplane Mode On" else "Airplane Mode Off"
        }

    }

}