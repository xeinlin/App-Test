@file:Suppress("DEPRECATION")

package com.example.apptest.database

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.apptest.database.entity.Product
import com.example.apptest.databinding.FragmentAddProductBinding
import com.example.apptest.helper.MyApp
import com.example.apptest.helper.NoTitleBar
import com.example.apptest.userinterface.fragments.BaseFragment
import kotlin.random.Random


class AddProductFragment : BaseFragment<FragmentAddProductBinding>(), NoTitleBar {

    companion object {
        private const val PRODUCT = "isEditMode"

        fun getInstance(product: Product? = null): AddProductFragment {
            val fragment = AddProductFragment()
            val bundle = Bundle()
            bundle.putSerializable(PRODUCT, product)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var editProduct: Product? = null

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentAddProductBinding {
        return FragmentAddProductBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        editProduct = arguments?.getSerializable(PRODUCT) as? Product
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.editProduct?.let {
            binding.edtProductName.setText(it.name)
            binding.edtProductDesc.setText(it.description)
        }
        binding.tvTitle.text = if (editProduct != null) "Update Product" else "Add Product"
        binding.btnInsertProduct.text = if (editProduct != null) "Update" else "Add"

        binding.btnInsertProduct.setOnClickListener {
            if (editProduct != null) {
                updateProduct()
            } else {
                addProduct()
            }
        }
    }

    private fun addProduct() {
        val name = binding.edtProductName.text.toString()
        val desc = binding.edtProductDesc.text.toString()
        val price = Random.nextInt(500, 1000).toDouble()
        val brand = "My Brand"

        val product = Product(
            id = 0,
            name = name,
            description = desc,
            price = price,
            brand = brand
        )

        AsyncTask.execute {
            (requireActivity().application as MyApp).db.productDAO().insert(product)
            requireActivity().runOnUiThread { parentFragmentManager.popBackStackImmediate() }
            (requireActivity() as? DatabaseActivity)?.refreshAdapterForNewProduct(product)
        }

    }

    private fun updateProduct() {
        val name = binding.edtProductName.text.toString()
        val desc = binding.edtProductDesc.text.toString()

        val product = Product(
            id = editProduct!!.id,
            name = name,
            description = desc,
            price = editProduct!!.price,
            brand = editProduct!!.brand
        )

        AsyncTask.execute {
            (requireActivity().application as MyApp).db.productDAO().update(product)
            requireActivity().runOnUiThread { parentFragmentManager.popBackStackImmediate() }
            (requireActivity() as? DatabaseActivity)?.refreshAdapterForUpdatedProduct(product)
        }
    }

}