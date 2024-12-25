package com.example.user_db

data class
myData(
    val limit: Int,
    val skip: Int,
    val total: Int,
    val users: List<User>
)