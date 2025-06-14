package com.example.projektowanieobiektoweapp.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.UUID

class CartItem : RealmObject {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var productId: String = ""
    var quantity: Int = 1
    var pricePerItem: Double = 0.0
}
