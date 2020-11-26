package com.example.expensetracker.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.expensetracker.R
import com.example.expensetracker.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var fullName: EditText
    private lateinit var phone: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var register: Button
    private lateinit var goToLogin: TextView


    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //FIREBASE
        auth = Firebase.auth
        database = Firebase.database

        // Finding Views
        fullName = findViewById(R.id.fullName)
        phone = findViewById(R.id.phone)
        email = findViewById(R.id.register_email)
        password = findViewById(R.id.register_password)
        register = findViewById(R.id.register_button)
        goToLogin = findViewById(R.id.register_goToLogin)

        // Setting onClickListeners
        goToLogin.setOnClickListener(this)
        register.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.register_goToLogin -> {
                val loginActivityIntent = Intent(this, LoginActivity::class.java)
                finish()
                startActivity(loginActivityIntent)
            }

            R.id.register_button -> {
                var formOk = true

                if (fullName.text.isEmpty()) {
                    fullName.error = "Name cannot be empty!"
                    formOk = false
                }

                if (phone.text.isEmpty()) {
                    phone.error = "Phone cannot be empty!"
                    formOk = false
                }

                if (email.text.isEmpty()) {
                    email.error = "Email cannot be empty!"
                    formOk = false
                }

                if (password.text.isEmpty()) {
                    password.error = "Password cannot be empty!"
                    formOk = false
                }

                if (formOk) {
                    val emailText = email.text.toString().trim()
                    val passwordText = password.text.toString().trim()
                    auth.createUserWithEmailAndPassword(emailText, passwordText)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                val userRef = database.getReference("users")
                                val user = User(emailText,
                                    fullName.text.toString().trim(),
                                    phone.text.toString().trim(),
                                    null,
                                    null,
                                )
                                val uid = auth.currentUser?.uid
                                userRef.child(uid!!).setValue(user)
                                Toast.makeText(this, "User created!", Toast.LENGTH_SHORT)
                                    .show()
                                finish()
                            } else {
                                Toast.makeText(this, "Could not create user!", Toast.LENGTH_SHORT)
                                    .show()
                            }

                        }

                }
            }
        }
    }
}