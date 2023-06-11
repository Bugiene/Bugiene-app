package com.example.bugiene.ui.dashboard

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bugiene.MainActivity
import com.example.bugiene.R
import com.example.bugiene.databinding.ActivityDetailArticleBinding
import com.example.bugiene.model.Article

class DetailArticleActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.customToolbar.btBack.setOnClickListener {
            startActivity(Intent(this@DetailArticleActivity, MainActivity::class.java))
            finish()
        }

        binding.customToolbar.teks.text = "Article"

        val article = intent.getParcelableExtra<Article>(DashboardFragment.DATA)
        val tittle = binding.tvTittle
        val image = binding.ivImage
        val field = binding.tvField
        image.setImageResource(article?.photo!!)
        tittle.text = article.title
        field.text = article.field

    }

    companion object{
        fun start(context: Context, data: Article){
            val intentToDetail = Intent(context, DetailArticleActivity::class.java)
            intentToDetail.putExtra(DashboardFragment.DATA, data)
            context.startActivity(intentToDetail)
        }
    }
}