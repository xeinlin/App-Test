package com.example.apptest.userinterface.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.apptest.R
import com.example.apptest.databinding.FragmentHomeBinding
import com.example.apptest.userinterface.fragments.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    companion object {
        fun getInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.visibility = View.GONE

        binding.btnChildFragment.setOnClickListener {
            showChildFragment1(childFragment.getInstance())
            binding.btnBack.visibility = View.VISIBLE
        }

        binding.btnBack.setOnClickListener {
            binding.btnBack.visibility = View.GONE
            requireActivity().supportFragmentManager
                .popBackStack(
                    childFragment::class.java.name,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
        }


    }

    private fun showChildFragment1(fragment: Fragment) {

        requireActivity().supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right, R.anim.slightly_slide_out_right,
                R.anim.slightly_slide_in_right, R.anim.slide_out_right
            )
            .add(R.id.child_cotainer_1, fragment)
            .addToBackStack(fragment.javaClass.name)
            .commit()

    }


}
