@file:Suppress("DEPRECATION")

package com.example.apptest.database

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apptest.BaseActivity
import com.example.apptest.R
import com.example.apptest.database.entity.Product
import com.example.apptest.databinding.ActivityDatabaseBinding
import com.example.apptest.databinding.ProductItemBinding
import com.example.apptest.helper.MyApp
import com.example.apptest.helper.ScreenAnimation
import com.example.apptest.helper.showDialogFragment
import com.example.apptest.helper.showToast


class DatabaseActivity : BaseActivity<ActivityDatabaseBinding>() {

    private val db by lazy { (application as MyApp).db }

    private val adapter = MyAdapter(this::onClickEdit, this::onClickDelete)

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
    private fun getProducts() {
        AsyncTask.execute {
            val products = this.db.productDAO().getAllProducts()
            runOnUiThread {
                adapter.setNewData(products)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun deleteProduct(product: Product) {
        AsyncTask.execute {
            this.db.productDAO().delete(product)
            runOnUiThread {
                adapter.removeItem(product)
                showToast("Product deleted")
            }
        }
    }

    fun refreshAdapterForNewProduct(product: Product) {
        runOnUiThread {
            adapter.addItem(product)
            showToast("Product added")
        }
    }

    fun refreshAdapterForUpdatedProduct(product: Product) {
        runOnUiThread {
            adapter.updateItem(product)
            showToast("Product updated")
        }
    }

    private fun onClickEdit(product: Product) {
        showDialogFragment(
            AddProductFragment.getInstance(product),
            animation = ScreenAnimation.ENTER_UP_EXIT_STAY
        )
    }

    private fun onClickDelete(product: Product) {
        this.deleteProduct(product)
    }

    private class MyAdapter(
        private val onClickEdit: (Product) -> Unit,
        private val onClickDelete: (Product) -> Unit
    ) : RecyclerView.Adapter<MyViewHolder>() {

        private var items = arrayListOf<Product>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(
                ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                onClickEdit, onClickDelete
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

        @SuppressLint("NotifyDataSetChanged")
        fun setNewData(products: List<Product>) {
            items.clear()
            items.addAll(products)
            notifyDataSetChanged()
        }

        fun addItem(product: Product) {
            items.add(product)
            notifyItemInserted(items.lastIndex)
        }

        fun removeItem(product: Product) {
            val position = items.indexOf(product)
            items.remove(product)
            notifyItemRemoved(position)
        }

        fun updateItem(product: Product) {
            val position = items.indexOfFirst { item ->
                item.id == product.id
            }
            items[position] = product
            notifyItemChanged(position)
        }

    }

    private class MyViewHolder(
        val binding: ProductItemBinding,
        private val onClickEdit: (Product) -> Unit,
        private val onClickDelete: (Product) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setupData(product: Product) {
            binding.tvTitle.text = product.name
            binding.tvDesc.text = product.description
            binding.tvPrice.text = "${product.price} MMK"

            itemView.setOnLongClickListener {
                val menu = PopupMenu(itemView.context, binding.tvTitle, Gravity.END)
                menu.inflate(R.menu.product_item_menu)
                menu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.edit -> {
                            onClickEdit.invoke(product)
                            true
                        }

                        R.id.delete -> {
                            onClickDelete.invoke(product)
                            true
                        }

                        else -> {
                            false
                        }
                    }
                }
                menu.show()
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