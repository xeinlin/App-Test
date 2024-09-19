package com.example.apptest.userinterface.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.apptest.databinding.FragmentSettingBinding
import com.example.apptest.userinterface.fragments.BaseFragment


class SettingFragment : BaseFragment<FragmentSettingBinding>() {
    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSettingBinding {
        return FragmentSettingBinding.inflate(inflater, container, false)
    }

}