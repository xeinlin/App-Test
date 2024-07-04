package com.example.apptest.userinterface.listview

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import com.example.apptest.BaseActivity
import com.example.apptest.R
import com.example.apptest.databinding.ActivityListViewBinding
import com.example.apptest.databinding.ListviewItemBinding
import com.example.apptest.helper.showToast

class ListViewActivity : BaseActivity<ActivityListViewBinding>() {

    companion object {

        fun getInstance(context: Context): Intent {
            return Intent(context, ListViewActivity::class.java)
        }
    }

    override val pageTitle: String get() = "List View For Fruits"

    override fun setUpViewBinding(layoutInflater: LayoutInflater): ActivityListViewBinding {
        return ActivityListViewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupCustomAdapter()
    }

    private fun setupSimpleAdapter() {
        val fruits = listOf(
            "Apple", "Banana", "Orange", "Grape", "Mango",
            "Watermelon", "Kiwi", "Pineapple", "Blueberry", "Pear"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, fruits
        )

        binding.listview.adapter = adapter
    }

    private fun setupCustomAdapter() {
        val fruits = listOf(
            Pair(R.drawable.apple2, "Apple"),
            Pair(R.drawable.banana, "Banana"),
            Pair(R.drawable.orange, "Orange"),
            Pair(R.drawable.grape, "Grape"),
            Pair(R.drawable.mango, "Mango"),
            Pair(R.drawable.watermelon, "Watermelon"),
            Pair(R.drawable.kiwi, "Kiwi"),
            Pair(R.drawable.pineapple2, "Pineapple"),
            Pair(R.drawable.blueberry, "Blueberry"),
            Pair(R.drawable.pear, "Pear")
        )
        val adapter = CustomAdapter(fruits)

        binding.listview.setOnItemClickListener { _, _, position, _ ->
            val item = adapter.getItem(position) as? Pair<*, *>
            val name = item?.second as? String ?: "Unknown"
            this@ListViewActivity.showToast(name)

        }
        binding.listview.adapter = adapter

    }

}

private class CustomAdapter(val items: List<Pair<Int, String>>) : BaseAdapter() {
    override fun getCount(): Int {
        return items.count()
    }

    override fun getItem(p0: Int): Any {
        return items[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val binding = ListviewItemBinding.inflate(LayoutInflater.from(p2?.context))
        binding.name.text = items[p0].second
        binding.image.setImageResource(items[p0].first)
        return binding.root
    }


}