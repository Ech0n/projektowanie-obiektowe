package com.example.projektowanieobiektoweapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projektowanieobiektoweapp.adapters.CartAdapter
import com.example.projektowanieobiektoweapp.databinding.CartFragmentBinding
import com.example.projektowanieobiektoweapp.models.CartItem

class CartFragment : Fragment() {

    private lateinit var binding: CartFragmentBinding
    private lateinit var adapter: CartAdapter
    private var cartItems: List<CartItem> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CartFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = CartAdapter(cartItems, RealmService.realm, viewLifecycleOwner.lifecycleScope) {
            loadCartItems()
        }
        loadCartItems()


        binding.cartRecyclerView.adapter = adapter
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    fun loadCartItems() {
        val cartItems = RealmService.realm.query(CartItem::class).find()
        adapter.cartItems = cartItems
        adapter.notifyDataSetChanged()
    }
}