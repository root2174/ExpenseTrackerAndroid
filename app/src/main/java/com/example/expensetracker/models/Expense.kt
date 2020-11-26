package com.example.expensetracker.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Expense(
    val name: String? = "",
    val value: Int? = null
)
