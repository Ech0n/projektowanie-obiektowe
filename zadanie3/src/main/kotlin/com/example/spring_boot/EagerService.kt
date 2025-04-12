package com.example.spring_boot

import org.springframework.stereotype.Component

@Component
class EagerService {

    init {
        println("Eager Service Initialized! (Should initialize at server startup)")
    }

    fun authenticate(username: String, password: String): Boolean {
        return usersList.any {
            (it.name == username) && it.password == password
        }
    }
}