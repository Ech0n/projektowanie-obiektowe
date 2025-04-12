package com.example.spring_boot

data class User (
    val id: Int,
    val name: String,
    val email: String,
    val password: String
)

val usersList = listOf(
    User(1, "user1", "user@example.com", "password"),
    User(2, "user2", "user2@example.com", "password"),
    User(3, "admin", "admin@example.com", "admin")
)