package com.example.projektowanieobiektoweapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.projektowanieobiektoweapp.R
import com.example.projektowanieobiektoweapp.RealmService
import com.example.projektowanieobiektoweapp.models.Category
import com.example.projektowanieobiektoweapp.models.Product

class CategoryAdapter(private val categories: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    // Keep track of expanded states
    private val expandedPositions = mutableSetOf<Int>()

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoryName: TextView = view.findViewById(R.id.categoryNameText)
        val productsContainer: LinearLayout = view.findViewById(R.id.productsContainer)
        val deleteCategoryButton: ImageButton = view.findViewById(R.id.deleteCategoryButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category_expandable, parent, false)
        return CategoryViewHolder(view)
    }
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.categoryName.text = category.name

        val products = RealmService.realm.query(Product::class, "categoryId == $0", category.id).find()

        holder.productsContainer.removeAllViews()
        products.forEach { product ->
            val productLayout = LinearLayout(holder.itemView.context).apply {
                orientation = LinearLayout.HORIZONTAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            }

            val productText = TextView(holder.itemView.context).apply {
                text = "- ${product.name} ($${product.price})"
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                setPadding(8, 4, 8, 4)
            }

            val deleteButton = ImageButton(holder.itemView.context).apply {
                setImageResource(android.R.drawable.ic_delete)
                background = null
                contentDescription = "Delete Product"
                setOnClickListener {
                    AlertDialog.Builder(holder.itemView.context)
                        .setTitle("Delete Product")
                        .setMessage("Are you sure you want to delete ${product.name}?")
                        .setPositiveButton("Delete") { _, _ ->
                            RealmService.realm.writeBlocking {
                                findLatest(product)?.let { delete(it) }
                            }
                            notifyItemChanged(position) // Refresh category
                        }
                        .setNegativeButton("Cancel", null)
                        .show()
                }
            }

            productLayout.addView(productText)
            productLayout.addView(deleteButton)
            holder.productsContainer.addView(productLayout)
        }

        val isExpanded = expandedPositions.contains(position)
        holder.productsContainer.visibility = if (isExpanded) View.VISIBLE else View.GONE
        holder.categoryName.setCompoundDrawablesWithIntrinsicBounds(
            0, 0,
            if (isExpanded) android.R.drawable.arrow_up_float else android.R.drawable.arrow_down_float,
            0
        )

        holder.categoryName.setOnClickListener {
            if (isExpanded) expandedPositions.remove(position) else expandedPositions.add(position)
            notifyItemChanged(position)
        }

        holder.deleteCategoryButton.setOnClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Delete Category")
                .setMessage("Are you sure you want to delete ${category.name}? All related products will also be deleted.")
                .setPositiveButton("Delete") { _, _ ->
                    RealmService.realm.writeBlocking {
                        val toDelete = findLatest(category)
                        if (toDelete != null) {
                            val relatedProducts = query(Product::class, "categoryId == $0", category.id).find()
                            delete(relatedProducts)
                            delete(toDelete)
                        }
                    }
                    expandedPositions.remove(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, itemCount - position)
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }


    override fun getItemCount(): Int = categories.size
}
