package com.example.apptest.userinterface.drawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.apptest.databinding.FragmentDrawerTrashBinding
import com.example.apptest.userinterface.fragments.BaseFragment

class TrashFragment : BaseFragment<FragmentDrawerTrashBinding>() {
    companion object {
        fun getInstance(): TrashFragment {
            return TrashFragment()
        }
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentDrawerTrashBinding {
        return FragmentDrawerTrashBinding.inflate(inflater, container, false)
    }
}