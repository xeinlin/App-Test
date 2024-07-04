package com.example.apptest.userinterface.drawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.apptest.databinding.FragmentDrawerSendBinding
import com.example.apptest.userinterface.base_fragment.BaseFragment

class SendFragment : BaseFragment<FragmentDrawerSendBinding>() {

    companion object {
        fun getInstance(): SendFragment {
            return SendFragment()
        }
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentDrawerSendBinding {
        return FragmentDrawerSendBinding.inflate(inflater, container, false)
    }
}