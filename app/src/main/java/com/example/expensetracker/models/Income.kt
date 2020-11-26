package com.example.expensetracker.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Income(
    val name: String? = "",
    val value: Int? = null
)
