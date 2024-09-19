package com.example.apptest.userinterface.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.apptest.R
import com.example.apptest.databinding.FragmentChildBinding
import com.example.apptest.userinterface.fragments.BaseFragment


class childFragment : BaseFragment<FragmentChildBinding>() {

    companion object {
        fun getInstance(): childFragment {
            return childFragment()
        }
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentChildBinding {
        return FragmentChildBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnChildFragment2.setOnClickListener { showChildFragment2(childFragment2.getInstance()) }
    }

    private fun showChildFragment2(fragment: Fragment) {

        requireActivity().supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right, R.anim.slightly_slide_out_right,
                R.anim.slightly_slide_in_right, R.anim.slide_out_right
            )
            .add(R.id.child_cotainer_2, fragment)
            .addToBackStack(fragment.javaClass.name)
            .commit()

    }

}