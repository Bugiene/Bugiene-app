package com.example.bugiene.ui.identify

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.bugiene.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpHasil()

        binding.customToolbar.btBack.setOnClickListener {
            startActivity(Intent(this@ResultActivity, IdentifyActivity::class.java))
        }
    }

    private fun setUpHasil() {
        val freshness = intent.getBooleanExtra("freshness", false)
        val accuracy = intent.getDoubleExtra("accuracy", 0.0)
        val uri = intent.getStringExtra("photo")

        var hasilFresh = freshnessOutput(freshness)
        var hasilPercentage = convertDouble(accuracy)

        if (accuracy < 1){
            if (uri != null) {
                binding.imageResult.setImageURI(uri.toUri())
            }
            binding.tvResult.text = "Rotten"
            binding.tvAkurasi.text = hasilPercentage
        }
        else{
            if (uri != null) {
                binding.imageResult.setImageURI(uri.toUri())
            }
            binding.tvResult.text = hasilFresh
            binding.tvAkurasi.text = hasilPercentage
        }

        Log.d("resut", "fresh : $hasilFresh dan persen : $hasilPercentage")

    }

    private fun freshnessOutput(hasil: Boolean): String {
        if (hasil) {
            return "Fresh"
        } else {
            return "Rotten"
        }
    }

    private fun convertDouble(angka: Double): String {
        var hasil = angka * 100
        var result = hasil.toInt()
        return "$result %"
    }
}