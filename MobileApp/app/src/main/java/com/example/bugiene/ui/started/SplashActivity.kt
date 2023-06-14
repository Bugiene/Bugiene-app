package com.example.bugiene.ui.started

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.util.Log
import com.example.bugiene.MainActivity
import com.example.bugiene.databinding.ActivitySplashBinding
import com.example.bugiene.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding
    private val SPLASH_TIME_OUT = 3000L
    private lateinit var sharedPreferences: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
            val token = PreferenceManager.getDefaultSharedPreferences(this)
                .getString("PREF_TOKEN", null)
            val userId = sharedPreferences.getString("PREF_ID", null)
            Log.d("splashHASILID", "hasil User ID: $userId")

            val intent = if (token != null){
                Intent(this, MainActivity::class.java)
            } else{
                Intent(this, OnBoardingActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)
    }
}