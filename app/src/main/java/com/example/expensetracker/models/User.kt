package com.example.expensetracker.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    var email: String? = "",
    var name: String? = "",
    var phone: String? = "",
)
