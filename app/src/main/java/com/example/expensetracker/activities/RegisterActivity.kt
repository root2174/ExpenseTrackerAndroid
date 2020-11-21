package com.example.expensetracker.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.expensetracker.R

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var register: Button
    private lateinit var goToLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

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
        }
    }
}