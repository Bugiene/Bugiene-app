package com.example.bugiene.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bugiene.R
import com.example.bugiene.databinding.ActivityRegisterBinding
import com.example.bugiene.ui.login.LoginActivity
import com.example.bugiene.ui.login.LoginViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel : RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.tvToLogin.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        viewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        )[RegisterViewModel::class.java]

        regisUser()
    }

    private fun regisUser() {
        showLoading(false)

        binding.btRegis.setOnClickListener {
            val username = binding.edRegisUsername.text.toString().trim()
            val email = binding.edRegisEmail.text.toString().trim()
            val pass = binding.edRegisPass.text.toString().trim()
            val passConfirm = binding.edRegisConfpass.text.toString().trim()

            val errorMessages = mutableListOf<String>()

            if (username.isEmpty()) {
                errorMessages.add("Please enter a username")
                binding.edRegisUsername.error = "Please enter a username"
            } else {
                binding.edRegisUsername.error = null
            }

            if (email.isEmpty()) {
                errorMessages.add("Please enter an email")
                binding.edRegisEmail.error = "Please enter an email"
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                errorMessages.add("Invalid email format")
                binding.edRegisEmail.error = "Invalid email format"
            } else {
                binding.edRegisEmail.error = null
            }

            if (pass.isEmpty()) {
                errorMessages.add("Please enter a password")
                binding.edRegisPass.error = "Please enter a password"
            } else if (pass.length < 8) {
                errorMessages.add("Password should be at least 8 characters long")
                binding.edRegisPass.error = "Password should be at least 8 characters long"
            } else {
                binding.edRegisPass.error = null
            }

            if (passConfirm.isEmpty()) {
                errorMessages.add("Please confirm your password")
                binding.edRegisConfpass.error = "Please confirm your password"
            } else {
                binding.edRegisConfpass.error = null
            }

            if (pass != passConfirm) {
                errorMessages.add("Password and confirmation do not match")
                binding.edRegisPass.error = "Password and confirmation do not match"
                binding.edRegisConfpass.error = "Password and confirmation do not match"
            }

            if (errorMessages.isEmpty()) {
                binding.btRegis.isEnabled = false
                showLoading(true)
                viewModel.registerUser(username, email, pass)
            } else {
                val errorMessage = errorMessages.joinToString("\n")
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.registrationResult.observe(this, Observer { result ->
            if (result != null) {
                showLoading(false)
                binding.btRegis.isEnabled = true
                if (!result.error) {
                    Toast.makeText(this, "Registration success!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                    showLoading(false)
                    finish()
                } else {
                    showLoading(false)
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
        }
    }
}