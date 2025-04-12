package com.example.spring_boot

import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
@Lazy
class LazyService {

    init {
        println("Lazy service initialized! (Should initialize at first use)")
    }

    fun authenticate(username: String, password: String): Boolean {
        return usersList.any {
            (it.name == username) && it.password == password
        }
    }
}