package com.example.expensetracker.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.expensetracker.R
import com.example.expensetracker.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(), View.OnClickListener {



    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var usersRef: DatabaseReference
    private lateinit var user: User

    private lateinit var goToCreateExpense: Button
    private lateinit var goToCreateIncome: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //FINDING VIEWS
        goToCreateExpense = findViewById(R.id.goTo_create_expense_button)
        goToCreateIncome = findViewById(R.id.goTo_create_income_button)

        goToCreateExpense.setOnClickListener(this)
        goToCreateIncome.setOnClickListener(this)

        auth = Firebase.auth
        database = Firebase.database
        usersRef = database.reference.child("users")
        usersRef.orderByKey().equalTo(auth.currentUser?.uid!!)
            .addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {

                user = snapshot.getValue(User::class.java)!!
                Log.i("APP", "user: $user")
                Log.i("APP", "snapshot: $snapshot")
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.goTo_create_expense_button -> {
                val it = Intent(this, CreateExpense::class.java)
                it.putExtra("user", user)
                startActivity(it)
            }

            R.id.goTo_create_income_button -> {
                val it = Intent(this, CreateIncome::class.java)
                it.putExtra("user", user)
                startActivity(it)
            }

        }
    }
}