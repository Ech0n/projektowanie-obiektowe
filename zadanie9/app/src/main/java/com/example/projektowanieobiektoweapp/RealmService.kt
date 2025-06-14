package com.example.projektowanieobiektoweapp

import android.content.Context
import com.example.projektowanieobiektoweapp.models.CartItem
import com.example.projektowanieobiektoweapp.models.Category
import com.example.projektowanieobiektoweapp.models.Product
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

object RealmService {
    lateinit var realm: Realm

    fun init(context: Context) {
        val config = RealmConfiguration.Builder(
            schema = setOf(Product::class, Category::class, CartItem::class))
            .deleteRealmIfMigrationNeeded()
            .build()

        realm = Realm.open(config)
    }
}
