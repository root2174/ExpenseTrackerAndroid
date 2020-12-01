package com.example.expensetracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.expensetracker.R
import com.example.expensetracker.models.Expense
import com.example.expensetracker.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CreateExpense : AppCompatActivity() {

    private lateinit var expenseEditText: EditText
    private lateinit var expenseValueEditText: EditText
    private lateinit var expenseAddButton: Button
    private lateinit var user: User
    private lateinit var usersRef: DatabaseReference

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_expense)

        // FIREBASE
        auth = Firebase.auth
        database = Firebase.database
        usersRef = database.reference.child("users")

        // FINDING VIEWS
        expenseEditText = findViewById(R.id.expense_name_editText)
        expenseValueEditText = findViewById(R.id.expense_value_editText)
        expenseAddButton = findViewById(R.id.expense_add_button)

        // SETTING USER
        user = intent.getSerializableExtra("user") as User

        // SETTING ON CLICK LISTENER
        expenseAddButton.setOnClickListener {
            //FORM
            var formOk = true
            if (expenseEditText.text.isEmpty()) {
                expenseEditText.error = "Expense name cannot be empty!"
                formOk = false
            }
            if (expenseValueEditText.text.isEmpty()) {
                expenseValueEditText.error = "Expense value cannot be empty!"
                formOk = false
            }
            if (formOk){
                val newExpense = Expense(expenseEditText.text.toString().trim(),
                    expenseValueEditText.text.toString().toDouble())

                var userExpenses = user.expenses

                if (userExpenses == null) {
                    userExpenses = mutableListOf(newExpense)
                    val userCopy = user.copy(expenses = userExpenses)
                    Log.i("new user", userCopy.toString())
                    usersRef.child(auth.currentUser?.uid!!).setValue(userCopy)
                    finish()
                } else {
                    userExpenses.add(newExpense)
                    val userCopy = user.copy(expenses = userExpenses)
                    usersRef.child(auth.currentUser?.uid!!).setValue(userCopy)
                    finish()
                }
            }

        }
    }
}