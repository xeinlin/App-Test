package com.example.apptest.userinterface.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.apptest.databinding.FragmentChild2Binding
import com.example.apptest.userinterface.base_fragment.BaseFragment


class childFragment2 : BaseFragment<FragmentChild2Binding>() {
    companion object {
        fun getInstance(): childFragment2 {
            return childFragment2()
        }
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentChild2Binding {
        return FragmentChild2Binding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}