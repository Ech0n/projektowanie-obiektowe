package com.example.projektowanieobiektoweapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projektowanieobiektoweapp.adapters.ProductAdapter
import com.example.projektowanieobiektoweapp.databinding.FragmentProductsBinding
import com.example.projektowanieobiektoweapp.models.CartItem
import com.example.projektowanieobiektoweapp.models.Category
import com.example.projektowanieobiektoweapp.models.Product
import kotlinx.coroutines.launch

class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding
    private lateinit var adapter: ProductAdapter
    private var products: List<Product> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupCategoryProductList()
    }

    private fun setupCategoryProductList() {
        val realmCategories = RealmService.realm.query(Category::class).find()
        Log.d("PoAPP","Size of categories ${realmCategories.size}")
        adapter = ProductAdapter(
            categories = realmCategories,
            realm = RealmService.realm,
            onAddToCart = { product ->
                lifecycleScope.launch {
                    RealmService.realm.write {
                        copyToRealm(CartItem().apply {
                            productId = product.id
                            quantity = 1
                            pricePerItem = product.price
                        })
                    }
                    Toast.makeText(requireContext(), "Added to cart", Toast.LENGTH_SHORT).show()
                }
            },
        )

        binding.productsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.productsRecyclerView.adapter = adapter
    }
    private fun fetchProducts() {
        val realmProducts = RealmService.realm.query(Product::class).find()
        products = realmProducts
    }
}