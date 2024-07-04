package com.example.apptest.userinterface.recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apptest.BaseActivity
import com.example.apptest.databinding.ActivityRecyclerViewBinding
import com.example.apptest.databinding.RecyclerViewItemBinding

class RecyclerViewActivity : BaseActivity<ActivityRecyclerViewBinding>() {

    companion object {

        fun getInstance(context: Context): Intent {
            return Intent(context, RecyclerViewActivity::class.java)
        }
    }

    override val pageTitle: String get() = "Recycler View"

    override fun setUpViewBinding(layoutInflater: LayoutInflater): ActivityRecyclerViewBinding {
        return ActivityRecyclerViewBinding.inflate(layoutInflater)
    }

    private val adapter = MyAdapter()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        binding.btnFruits.setOnClickListener {
            val fruits = listOf(
                "Apple", "Banana", "Orange", "Grape", "Mango",
                "Watermelon", "Kiwi", "Pineapple", "Blueberry", "Pear"
            )
            adapter.items = fruits
            adapter.notifyDataSetChanged()
        }

        binding.btnCountries.setOnClickListener {
            val countries = listOf(
                "Thailand", "Myanmar", "Singapore", "Vietnam", "philippines",
                "Watermelon", "Kiwi", "Pineapple", "Blueberry", "Pear"
            )
            adapter.items = countries
            adapter.notifyDataSetChanged()
        }
    }

    private class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {

        var items = listOf<String>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(
                RecyclerViewItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }

        override fun getItemCount(): Int {
            return items.count()
        }

        override fun onViewRecycled(holder: MyViewHolder) {
            super.onViewRecycled(holder)
            holder.clearData()
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.setupData(items[position])
        }
    }

    private class MyViewHolder(val binding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setupData(value: String) {
            binding.tvTitle.text = value
            binding.tvDesc.text = "This is $value"
        }

        @SuppressLint("SetTextI18n")
        fun clearData() {
            binding.tvTitle.text = "Title"
            binding.tvDesc.text = "Description"
        }

    }


}