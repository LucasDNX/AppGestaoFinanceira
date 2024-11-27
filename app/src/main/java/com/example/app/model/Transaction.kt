package com.example.app.model

data class Transaction(
    val title: String,
    val description: String,
    val amount: Double,
    val isPositive: Boolean
)