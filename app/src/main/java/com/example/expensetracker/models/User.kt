package com.example.expensetracker.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    var email: String = "",
    var name: String? = "",
    var phone: String? = "",
    var income: List<Income>? = null,
    var expenses: List<Expense>? = null,
)
