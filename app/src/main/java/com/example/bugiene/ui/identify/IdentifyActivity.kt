package com.example.bugiene.ui.identify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bugiene.MainActivity
import com.example.bugiene.databinding.ActivityIdentifyBinding

class IdentifyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIdentifyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdentifyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.customToolbar.btBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java )
            startActivity(intent)
            finish()
        }

    }
}