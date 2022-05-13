package com.example.connect.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.connect.Validator
import com.example.connect.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    val validator = Validator()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            buttonSignUpSignUp.setOnClickListener {
                val name = editTextNameSignUp.text.toString()
                val email = editTextEmailSignUp.text.toString()
                val password = editTextPasswordSignUp.text.toString()
                val confirmPassword = editTextConfirmPasswordSignUp.text.toString()
                if (validator.checkEmail(email) &&
                        validator.checkPassword(password) &&
                        validator.checkConfirmPassword(confirmPassword, password) &&
                        name.isNotEmpty()) {
                    finish()
                }
            }
            buttonCancelSignUp.setOnClickListener {
                finish()
            }
        }
    }
}