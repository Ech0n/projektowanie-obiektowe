package com.example.projektowanieobiektoweapp

import android.os.Bundle
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
import com.example.projektowanieobiektoweapp.adapters.CategoryAdapter
import com.example.projektowanieobiektoweapp.databinding.FragmentCategoriesBinding
import com.example.projektowanieobiektoweapp.models.Category
import com.example.projektowanieobiektoweapp.models.Product
import kotlinx.coroutines.launch

class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var adapter: CategoryAdapter
    private var categories: List<Category> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = CategoryAdapter(categories)
        binding.categoriesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.categoriesRecyclerView.adapter = adapter

        fetchCategories()

        binding.addCategoryFab.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_add_category, null)
            val editTextName = dialogView.findViewById<EditText>(R.id.editTextCategoryName)

            AlertDialog.Builder(requireContext())
                .setTitle("Add Category")
                .setView(dialogView)
                .setPositiveButton("Add") { _, _ ->
                    val name = editTextName.text.toString()

                    if (name.isNotBlank()) {
                        lifecycleScope.launch {
                            val newCategory = Category().apply {
                                this.name = name
                            }

                            RealmService.realm.write {
                                copyToRealm(newCategory)
                            }

                            fetchCategories()
                            Toast.makeText(requireContext(), "Category added", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(requireContext(), "Name cannot be empty", Toast.LENGTH_SHORT).show()
                    }
                }
                .setNegativeButton("Cancel", null)
                .show()
        }


        setupAddProductDialog()

    }
    private fun setupAddProductDialog() {

        binding.addProductFab.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_add_product, null)
            val editTextName = dialogView.findViewById<EditText>(R.id.editTextName)
            val editTextPrice = dialogView.findViewById<EditText>(R.id.editTextPrice)
            val categorySpinner = dialogView.findViewById<Spinner>(R.id.categorySpinner)

            // Fetch categories from Realm
            val categories = RealmService.realm.query(Category::class).find()
            val categoryNames = categories.map { it.name }

            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categoryNames)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            categorySpinner.adapter = adapter

            AlertDialog.Builder(requireContext())
                .setTitle("Add Product")
                .setView(dialogView)
                .setPositiveButton("Add") { _, _ ->
                    val name = editTextName.text.toString()
                    val priceText = editTextPrice.text.toString()
                    val selectedCategoryIndex = categorySpinner.selectedItemPosition

                    if (name.isNotBlank() && priceText.isNotBlank() && selectedCategoryIndex >= 0) {
                        val price = priceText.toDoubleOrNull()
                        if (price != null) {
                            val selectedCategoryId = categories[selectedCategoryIndex].id

                            lifecycleScope.launch {
                                val newProduct = Product().apply {
                                    this.name = name
                                    this.price = price
                                    this.categoryId = selectedCategoryId
                                }

                                RealmService.realm.write {
                                    copyToRealm(newProduct)
                                }

                                fetchCategories()
                                Toast.makeText(requireContext(), "Product added", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(requireContext(), "Invalid price", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                    }
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }
    private fun fetchCategories() {
        val realmCategories = RealmService.realm.query(Category::class).find()
        categories = realmCategories
        adapter = CategoryAdapter(categories)
        binding.categoriesRecyclerView.adapter = adapter
    }
}
