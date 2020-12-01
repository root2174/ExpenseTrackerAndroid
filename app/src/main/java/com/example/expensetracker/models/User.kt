package com.example.expensetracker.models

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class User(
    var email: String = "",
    var name: String? = "",
    var phone: String? = "",
    var income: MutableList<Income>? = null,
    var expenses: MutableList<Expense>? = null,
) : Serializable
