package com.example.bugiene.ui.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bugiene.MainActivity
import com.example.bugiene.databinding.ActivityTutorialBinding

class TutorialActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTutorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.customToolbar.teks.text = "Tutorial"

        binding.customToolbar.btBack.setOnClickListener {
            startActivity(Intent(this@TutorialActivity, MainActivity::class.java))
        }
    }
}