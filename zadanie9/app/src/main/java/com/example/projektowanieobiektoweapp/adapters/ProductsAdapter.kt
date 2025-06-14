package com.example.projektowanieobiektoweapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projektowanieobiektoweapp.R
import com.example.projektowanieobiektoweapp.models.Category
import com.example.projektowanieobiektoweapp.models.Product
import io.realm.kotlin.Realm

class ProductAdapter(
    private var categories: List<Category>,
    private val realm: Realm,
    private val onAddToCart: (Product) -> Unit,
) : RecyclerView.Adapter<ProductAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val categoryName: TextView = view.findViewById(R.id.categoryNameText)
        val productsContainer: LinearLayout = view.findViewById(R.id.productsContainer)
        val header: LinearLayout = view.findViewById(R.id.categoryHeader)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_categoreis_and_products, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.categoryName.text = category.name
        holder.productsContainer.removeAllViews()

        // Load products in this category
        val products = realm.query(Product::class, "categoryId == $0", category.id).find()

        Log.d("ProductAdapter", "Category ${category.name} has ${products.size} products")

        for (product in products) {
            val productView = LayoutInflater.from(holder.view.context)
                .inflate(R.layout.item_product, holder.productsContainer, false)

            val nameText = productView.findViewById<TextView>(R.id.productNameText)
            val priceText = productView.findViewById<TextView>(R.id.productPriceText)
            val addToCartBtn = productView.findViewById<ImageButton>(R.id.addToCartButton)

            nameText.text = product.name
            priceText.text = "$${product.price}"
            addToCartBtn.setOnClickListener { onAddToCart(product) }

            holder.productsContainer.addView(productView)
        }

        // Toggle expand/collapse
        holder.header.setOnClickListener {
            holder.productsContainer.visibility =
                if (holder.productsContainer.visibility == View.VISIBLE) View.GONE
                else View.VISIBLE
        }


    }
}
