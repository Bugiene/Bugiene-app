package com.example.bugiene.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import com.example.bugiene.MainActivity
import com.example.bugiene.R
import com.example.bugiene.databinding.ActivitySplashBinding
import com.example.bugiene.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding
    private val SPLASH_TIME_OUT = 3000L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            val token = PreferenceManager.getDefaultSharedPreferences(this)
                .getString("PREF_TOKEN", null)

            val intent = if (token != null){
                Intent(this, MainActivity::class.java)
            } else{
                Intent(this, LoginActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)
    }
}