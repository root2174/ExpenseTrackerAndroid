package com.example.expensetracker.models

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class Income(
    val name: String? = "",
    val value: Double? = null
) : Serializable
