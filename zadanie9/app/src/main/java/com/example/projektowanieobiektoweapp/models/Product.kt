package com.example.projektowanieobiektoweapp.models

import com.android.identity.util.UUID
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Product : RealmObject {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var name: String = ""
    var price: Double = 0.0
    var categoryId: String = ""
}