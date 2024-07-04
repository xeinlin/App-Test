package com.example.apptest.userinterface.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.apptest.databinding.FragmentNotificitionsBinding
import com.example.apptest.userinterface.base_fragment.BaseFragment

class NotificationsFragment : BaseFragment<FragmentNotificitionsBinding>() {
    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentNotificitionsBinding {
        return FragmentNotificitionsBinding.inflate(inflater, container, false)
    }


}