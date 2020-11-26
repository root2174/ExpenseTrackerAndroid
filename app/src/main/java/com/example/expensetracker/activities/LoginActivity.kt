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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var goToRegister: TextView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        // Finding Views
        email = findViewById(R.id.login_email)
        password = findViewById(R.id.login_password)
        loginButton = findViewById(R.id.login_button)
        goToRegister = findViewById(R.id.login_goToRegister)

        // Setting onClickListeners
        goToRegister.setOnClickListener(this)
        loginButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.login_goToRegister -> {
                val registerActivityIntent = Intent(this, RegisterActivity::class.java)
                startActivity(registerActivityIntent)
            }

            R.id.login_button -> {
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

                    auth.signInWithEmailAndPassword(emailText, passwordText)
                        .addOnCompleteListener(this) {task ->
                            if (task.isSuccessful) {
                                val it = Intent(this, MainActivity::class.java)
                                startActivity(it)
                            } else {
                                Toast.makeText(this, "Unable to login!", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                }
            }
        }
    }
}