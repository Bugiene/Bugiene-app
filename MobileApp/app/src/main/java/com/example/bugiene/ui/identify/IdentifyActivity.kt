package com.example.bugiene.ui.identify

import android.Manifest
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import com.example.bugiene.MainActivity
import com.example.bugiene.databinding.ActivityIdentifyBinding
import com.example.bugiene.helper.compressImage
import com.example.bugiene.helper.createCustomTempFile
import com.example.bugiene.helper.uriToFile
import java.io.File

class IdentifyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIdentifyBinding
    private lateinit var currentPhotoPath: String
    private lateinit var identifyViewModel: IdentifyViewModel
    private var getFile: File? = null
    private lateinit var uri : Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdentifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        identifyViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[IdentifyViewModel::class.java]

        setupActivity()
    }


    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val myFile = File(currentPhotoPath)
            uri = myFile.toUri()
            getFile = myFile
            myFile.let { file ->
                binding.ivPic.setImageBitmap(BitmapFactory.decodeFile(file.path))
            }
        }
    }

    private fun observeResult() {
        if (getFile != null){
            val compress = compressImage(getFile!!, 80)
            identifyViewModel.postAddStory(compress!!)
            identifyViewModel.data.observe(this) { detectionResponse ->
                val result = detectionResponse.result

                if (result != null){
                    showLoading(true)
                    Toast.makeText(this@IdentifyActivity, "Sucsess", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@IdentifyActivity, ResultActivity::class.java)
                    intent.putExtra("photo", uri.toString())
                    intent.putExtra("freshness", result.freshness)
                    intent.putExtra("accuracy", result.accuracy)
                    showLoading(false)
                    startActivity(intent)
                }else{
                    showLoading(false)
                    Toast.makeText(this@IdentifyActivity, "Opps Something Wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun setupActivity() {
        showLoading(false)

        binding.customToolbar.btBack.setOnClickListener {
            startActivity(Intent(this@IdentifyActivity, MainActivity::class.java))
        }

        binding.btSubmit.setOnClickListener {
            showLoading(true)
            observeResult()
        }

        binding.customToolbar.teks.text = "Identify"

        binding.btCamera.setOnClickListener { startTakePhoto() }
        binding.btGallery.setOnClickListener { startGallery() }
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
            uri = selectedImg
            selectedImg.let { uri ->
                val file = uriToFile(uri, this@IdentifyActivity)
                getFile = file
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

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
        }
    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}

//private fun allPermissionsGranted() = IdentifyActivity.REQUIRED_PERMISSIONS.filterNot { permission ->
//    permission == Manifest.permission.WRITE_EXTERNAL_STORAGE && Build.VERSION.SDK_INT <= 33
//}.all {
//    ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
//}
//override fun onRequestPermissionsResult(
//    requestCode: Int,
//    permissions: Array<String>,
//    grantResults: IntArray
//) {
//    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    if (requestCode == IdentifyActivity.REQUEST_CODE_PERMISSIONS && Build.VERSION.SDK_INT <= 33) {
//        if (allPermissionsGranted()) {
//            // Permissions are granted, proceed with setup
//            setupActivity()
//        } else {
//            Toast.makeText(this, "Permissions not granted", Toast.LENGTH_SHORT).show()
//            finish()            }
//    }
//}