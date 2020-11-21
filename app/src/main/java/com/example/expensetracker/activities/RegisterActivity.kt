package com.example.expensetracker.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.expensetracker.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var register: Button
    private lateinit var goToLogin: TextView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth

        // Finding Views
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
                                Toast.makeText(this, "User created!", Toast.LENGTH_SHORT)
                                    .show()
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