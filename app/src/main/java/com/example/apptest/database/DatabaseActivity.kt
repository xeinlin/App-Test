@file:Suppress("DEPRECATION")

package com.example.apptest.database

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apptest.BaseActivity
import com.example.apptest.database.entity.Product
import com.example.apptest.databinding.ActivityDatabaseBinding
import com.example.apptest.databinding.ProductItemBinding
import com.example.apptest.helper.MyApp
import com.example.apptest.helper.ScreenAnimation
import com.example.apptest.helper.showDialogFragment
import com.example.apptest.helper.showToast


class DatabaseActivity : BaseActivity<ActivityDatabaseBinding>() {

    private val db by lazy { (application as MyApp).db }

    private val adapter = MyAdapter(this::onClickProduct)

    companion object {

        fun getInstance(context: Context): Intent {
            return Intent(context, DatabaseActivity::class.java)
        }

    }

    override val pageTitle: String = "Database"

    override fun setUpViewBinding(layoutInflater: LayoutInflater): ActivityDatabaseBinding {
        return ActivityDatabaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setupUI()
        this.getProducts()
    }

    private fun setupUI() {
        binding.rvProduct.adapter = adapter
        binding.rvProduct.layoutManager = LinearLayoutManager(this)

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            this.getProducts()
        }

        binding.floatingActionButton.setOnClickListener {
            showDialogFragment(
                AddProductFragment.getInstance(),
                animation = ScreenAnimation.ENTER_UP_EXIT_STAY
            )
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getProducts() {
        AsyncTask.execute { // Insert Data
            val products = this.db.productDAO().getAllProducts()
            runOnUiThread {
                adapter.items = products
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun onClickProduct(product: Product) {
        showToast("Clicked ${product.name}")
    }

    private class MyAdapter(
        private val onClickProduct: (Product) -> Unit
    ) : RecyclerView.Adapter<MyViewHolder>() {

        var items = listOf<Product>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(
                ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                onClickProduct
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
            Log.d("TAG", "onBindViewHolder: $position")
        }

    }

    private class MyViewHolder(
        val binding: ProductItemBinding,
        private val onClickProduct: (Product) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setupData(product: Product) {
            binding.tvTitle.text = product.name
            binding.tvDesc.text = product.description
            binding.tvPrice.text = "${product.price} MMK"

            itemView.setOnClickListener {
                onClickProduct.invoke(product)
            }
            itemView.setOnLongClickListener {
                // to do
                true
            }
        }

        @SuppressLint("SetTextI18n")
        fun clearData() {
            binding.tvTitle.text = "Product Name"
            binding.tvDesc.text = "Description"
            binding.tvPrice.text = "0 MMK"
        }
    }
}