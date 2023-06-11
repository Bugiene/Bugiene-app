package com.example.bugiene.ui.register

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bugiene.model.RegisterResponse
import com.example.bugiene.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/** Raihan Chaira on 5/29/2023
 * raihanchaira21@gmail.com
 */
class RegisterViewModel : ViewModel() {

    val registrationResult = MutableLiveData<RegisterResponse?>()

    fun RegisterUser(username : String, email : String, password : String) {
        registrationResult.value = null
        val call = ApiConfig.getApiService().registerUser(username, email, password)

        call.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful) {
                    registrationResult.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}