package com.example.projektowanieobiektoweapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projektowanieobiektoweapp.R
import com.example.projektowanieobiektoweapp.models.CartItem
import com.example.projektowanieobiektoweapp.models.Product
import io.realm.kotlin.Realm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CartAdapter(
    var cartItems: List<CartItem>,
    private val realm: Realm,
    private val coroutineScope: CoroutineScope,
    private val onQuantityChanged: () -> Unit // to notify parent when cart changes
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val productNameText: TextView = view.findViewById(R.id.productNameText)
        val minusButton: ImageButton = view.findViewById(R.id.minusButton)
        val quantityText: TextView = view.findViewById(R.id.quantityText)
        val plusButton: ImageButton = view.findViewById(R.id.plusButton)
        val totalPriceText: TextView = view.findViewById(R.id.totalPriceText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun getItemCount(): Int = cartItems.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]
        // Lookup product name by productId (assuming Realm has Product data)
        val product = realm.query(Product::class, "id == $0", cartItem.productId).find().firstOrNull()

        holder.productNameText.text = product?.name ?: "Unknown"
        holder.quantityText.text = cartItem.quantity.toString()
        val totalPrice = cartItem.quantity * cartItem.pricePerItem
        holder.totalPriceText.text = "$%.2f".format(totalPrice)

        holder.minusButton.setOnClickListener {
            val newQuantity = cartItem.quantity - 1
            coroutineScope.launch {
                realm.write {
                    val managedItem = findLatest(cartItem)
                    if (managedItem != null) {
                        if (newQuantity <= 0) {
                            delete(managedItem)
                        } else {
                            managedItem.quantity = newQuantity
                        }
                    }
                }
                onQuantityChanged() // Callback to refresh UI or reload cart
            }
        }

        holder.plusButton.setOnClickListener {
            coroutineScope.launch {
                realm.write {
                    findLatest(cartItem)?.let {
                        it.quantity = cartItem.quantity + 1
                    }
                }
                onQuantityChanged()
            }
        }
    }
}
