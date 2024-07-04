package com.example.apptest.userinterface.listview

//noinspection SuspiciousImport
import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import com.example.apptest.BaseActivity
import com.example.apptest.R.drawable
import com.example.apptest.databinding.ActivitySpinnerViewBinding
import com.example.apptest.databinding.SpinnerViewBinding
import com.example.apptest.helper.showToast

class SpinnerViewActivity : BaseActivity<ActivitySpinnerViewBinding>() {

    companion object {

        fun getInstance(context: Context): Intent {
            return Intent(context, SpinnerViewActivity::class.java)
        }
    }

    override val pageTitle: String get() = "Spinner List"

    override fun setUpViewBinding(layoutInflater: LayoutInflater): ActivitySpinnerViewBinding {
        return ActivitySpinnerViewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupCustomAdapter()

    }

    private fun setupSimpleSpinnerAdapter() {
        val flavor = listOf(
            "Chocolate",
            "Strawberry",
            "Vanilla",
            "Mango",
            "Coconut"

        )

        val adapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, flavor)
        //adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter

    }

    private fun setupCustomAdapter() {
        val flavors = listOf(
            Pair(drawable.chocoice, "Chocolate"),
            Pair(drawable.icecream_strawberry, "Strawberry"),
            Pair(drawable.icecream_vanilla, "Vanilla"),
            Pair(drawable.icecream_mango, "Mango"),
            Pair(drawable.icecream_coconut, "Coconut")

        )
        val adapter = CustomSpinnerAdapter(flavors)
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                p0: AdapterView<*>?, p1: View?, p2: Int, id: Long
            ) {
                val item = adapter.getItem(p2) as? Pair<*, *>
                val name = item?.second as? String ?: "Unknown"
                this@SpinnerViewActivity.showToast(name)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


    }

}

private class CustomSpinnerAdapter(val items: List<Pair<Int, String>>) : BaseAdapter() {
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
        val binding = SpinnerViewBinding.inflate(LayoutInflater.from(p2?.context))
        binding.name.text = items[p0].second
        binding.image.setImageResource(items[p0].first)
        return binding.root
    }
}