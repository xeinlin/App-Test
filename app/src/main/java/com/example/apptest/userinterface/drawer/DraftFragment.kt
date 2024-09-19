package com.example.apptest.userinterface.drawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.apptest.databinding.FragmentDrawerDraftBinding
import com.example.apptest.userinterface.fragments.BaseFragment

class DraftFragment : BaseFragment<FragmentDrawerDraftBinding>() {
    companion object {
        fun getInstance(): DraftFragment {
            return DraftFragment()
        }
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentDrawerDraftBinding {
        return FragmentDrawerDraftBinding.inflate(inflater, container, false)
    }
}