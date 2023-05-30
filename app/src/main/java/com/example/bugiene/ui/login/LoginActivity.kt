package com.example.bugiene.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.bugiene.MainActivity
import com.example.bugiene.R
import com.example.bugiene.databinding.ActivityLoginBinding
import com.example.bugiene.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var viewModel : LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        binding.tvToRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        viewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        )[LoginViewModel::class.java]

        loginUser()

    }

    private fun loginUser() {
        showLoading(false)

        binding.btLogin.setOnClickListener {
            val username = binding.edLoginEmail.text.toString().trim()
            val password = binding.edLoginPass.text.toString().trim()

            val errorMessages = mutableListOf<String>()

            if (username.isEmpty()) {
                errorMessages.add("Please enter a username")
                binding.edLoginEmail.error = "Please enter a username"
            } else {
                binding.edLoginEmail.error = null
            }

            if (password.isEmpty()) {
                errorMessages.add("Please enter a password")
                binding.edLoginPass.error = "Please enter a password"
            } else {
                binding.edLoginPass.error = null
            }

            if (errorMessages.isEmpty()) {
                binding.btLogin.isEnabled = false
                showLoading(true)
                viewModel.LoginUser(username, password)
            } else {
                val errorMessage = errorMessages.joinToString("\n")
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.LoginResult.observe(this) { result ->
            if (result != null) {
                showLoading(false)
                binding.btLogin.isEnabled = true
                if (!result.error){
                    saveToken(result.data.accessToken)
                    Toast.makeText(this, "Login   success!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    showLoading(false)
                    finish()
                }else {
                    showLoading(false)
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun saveToken(token: String?) {
        PreferenceManager.getDefaultSharedPreferences(this@LoginActivity)
            .edit()
            .putString("PREF_TOKEN", token)
            .apply()
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
        }
    }
}