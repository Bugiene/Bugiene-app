package com.example.bugiene.ui.identify

import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.bugiene.MainActivity
import com.example.bugiene.databinding.ActivityIdentifyBinding
import com.example.bugiene.helper.createCustomTempFile
import com.example.bugiene.helper.uriToFile
import java.io.File

class IdentifyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIdentifyBinding
    private lateinit var currentPhotoPath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdentifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.customToolbar.btBack.setOnClickListener {
            startActivity(Intent(this@IdentifyActivity, MainActivity::class.java))
        }

        binding.customToolbar.teks.text = "Identify"

        binding.btCamera.setOnClickListener { startTakePhoto() }
        binding.btGallery.setOnClickListener { startGallery() }

        binding.btSubmit.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "This Feature Under Build",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val myFile = File(currentPhotoPath)

            myFile.let { file ->
                binding.ivPic.setImageBitmap(BitmapFactory.decodeFile(file.path))
            }
        }
    }

    private fun startTakePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(packageManager)
        createCustomTempFile(application).also {
            val photoURI: Uri = FileProvider.getUriForFile(
                this@IdentifyActivity,
                "com.example.bugiene",
                it
            )
            currentPhotoPath = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            launcherIntentCamera.launch(intent)
        }
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg = result.data?.data as Uri
            selectedImg.let { uri ->
                val myFile = uriToFile(uri, this@IdentifyActivity)
                binding.ivPic.setImageURI(uri)
            }
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }


}