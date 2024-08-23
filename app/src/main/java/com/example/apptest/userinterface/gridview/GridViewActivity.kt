package com.example.apptest.userinterface.gridview

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.apptest.BaseActivity
import com.example.apptest.R
import com.example.apptest.databinding.ActivityGridViewBinding
import com.example.apptest.databinding.GridItemBinding
import com.example.apptest.helper.showToast

class GridViewActivity : BaseActivity<ActivityGridViewBinding>() {

    companion object {

        fun getInstance(context: Context): Intent {
            return Intent(context, GridViewActivity::class.java)
        }
    }

    override val pageTitle: String get() = "Grid View"

    override fun setUpViewBinding(layoutInflater: LayoutInflater): ActivityGridViewBinding {
        return ActivityGridViewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupCustomAdapter()

    }

    private fun setupCustomAdapter() {
        val fruits = listOf(
            Pair(R.drawable.python2, "Python"),
            Pair(R.drawable.java, "Java"),
            Pair(R.drawable.kotlin2, "Kotlin"),
            Pair(R.drawable.html, "Html"),
            Pair(R.drawable.csharp, "Csharp")

        )
        val adapter = GridAdapter(fruits)

        binding.grv.setOnItemClickListener { _, _, position, _ ->
            val item = adapter.getItem(position) as? Pair<*, *>
            val name = item?.second as? String ?: "Unknown"
            this@GridViewActivity.showToast(name)

        }
        binding.grv.adapter = adapter

    }

}


private class GridAdapter(val items: List<Pair<Int, String>>) : BaseAdapter() {
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
        val binding = GridItemBinding.inflate(LayoutInflater.from(p2?.context))
        binding.gridtv.text = items[p0].second
        binding.gridimg.setImageResource(items[p0].first)
        return binding.root
    }


}