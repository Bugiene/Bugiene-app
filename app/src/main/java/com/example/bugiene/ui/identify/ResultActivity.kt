package com.example.bugiene.ui.identify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bugiene.R
import com.example.bugiene.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding : ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}