package com.example.expensetracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.expensetracker.R
import com.example.expensetracker.models.Expense
import com.example.expensetracker.models.Income
import com.example.expensetracker.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CreateIncome : AppCompatActivity() {
    private lateinit var incomeEditText: EditText
    private lateinit var incomeValueEditText: EditText
    private lateinit var incomeAddButton: Button
    private lateinit var user: User
    private lateinit var usersRef: DatabaseReference

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_income)

        // FIREBASE
        auth = Firebase.auth
        database = Firebase.database
        usersRef = database.reference.child("users")

        // FINDING VIEWS
        incomeEditText = findViewById(R.id.income_name_editText)
        incomeValueEditText = findViewById(R.id.income_value_editText)
        incomeAddButton = findViewById(R.id.income_add_button)

        // SETTING USER
        user = intent.getSerializableExtra("user") as User

        incomeAddButton.setOnClickListener {
            //FORM
            var formOk = true
            if (incomeEditText.text.isEmpty()) {
                incomeEditText.error = "Income name cannot be empty!"
                formOk = false
            }
            if (incomeValueEditText.text.isEmpty()) {
                incomeValueEditText.error = "Income value cannot be empty!"
                formOk = false
            }
            if (formOk){
                val newIncome = Income(incomeEditText.text.toString().trim(),
                    incomeValueEditText.text.toString().toDouble())

                var userIncome = user.income

                if (userIncome == null) {
                    userIncome = mutableListOf(newIncome)
                    val userCopy = user.copy(income = userIncome)
                    Log.i("new user", userCopy.toString())
                    usersRef.child(auth.currentUser?.uid!!).setValue(userCopy)
                    finish()
                } else {
                    userIncome.add(newIncome)
                    val userCopy = user.copy(income = userIncome)
                    usersRef.child(auth.currentUser?.uid!!).setValue(userCopy)
                    finish()
                }
            }
        }
    }
}