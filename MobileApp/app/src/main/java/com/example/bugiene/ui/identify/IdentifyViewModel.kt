package com.example.bugiene.ui.identify

import android.app.Application
import android.content.ContentValues
import android.preference.PreferenceManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bugiene.model.DetectionResponse
import com.example.bugiene.model.UserResultResponse
import com.example.bugiene.network.ApiConfig
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

/** Raihan Chaira on 6/11/2023
 * raihanchaira21@gmail.com
 */

//MENYESUAIKAN
class IdentifyViewModel(application : Application) : AndroidViewModel(application)  {

    private val _result = MutableLiveData<DetectionResponse>()
    val data : LiveData<DetectionResponse> = _result

    private val token =
        PreferenceManager.getDefaultSharedPreferences(application).getString("PREF_TOKEN", "")

    fun postAddStory(photo: File) {
        val result = "Bearer $token"

        val requestImageFile = photo.asRequestBody("image/jpeg".toMediaType())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "image",
            photo.name,
            requestImageFile
        )

        if (token != null) {
            ApiConfig.getApiService().postDetection(result, imageMultipart)
                .enqueue(object : Callback<DetectionResponse> {
                    override fun onResponse(
                        call: Call<DetectionResponse>,
                        response: Response<DetectionResponse>
                    ) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()
                            if (responseBody != null && !responseBody.status) {
                                _result.postValue(response.body())
                            }
                        }
                    }

                    override fun onFailure(call: Call<DetectionResponse>, t: Throwable) {
                        Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                    }
                })
        }
    }
}