package com.example.bugiene.ui.started

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bugiene.R
import com.example.bugiene.databinding.ActivityOnBoardingBinding
import com.example.bugiene.ui.login.LoginActivity
import com.example.bugiene.ui.register.RegisterActivity

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding : ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this@OnBoardingActivity, LoginActivity::class.java))
        }
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this@OnBoardingActivity, RegisterActivity::class.java))
        }
    }
}