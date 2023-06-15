package com.example.bugiene.ui.register

import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import androidx.core.content.contentValuesOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bugiene.model.RegisterResponse
import com.example.bugiene.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.coroutineContext

/** Raihan Chaira on 5/29/2023
 * raihanchaira21@gmail.com
 */
class RegisterViewModel : ViewModel() {

    val registrationResult = MutableLiveData<RegisterResponse?>()

    fun registerUser(username : String, email : String, password : String) {
        registrationResult.value = null
        val call = ApiConfig.getApiService().registerUser(username, email, password)

        call.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful) {
                    registrationResult.postValue(response.body())
                }else if (response.code() ==  409){
                    registrationResult.postValue(RegisterResponse(error = true, message = "Email already exist"))
                }else if (response.code() == 500){
                    registrationResult.postValue(RegisterResponse(error = true, message = "Ops something wrong"))
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                registrationResult.postValue(RegisterResponse(error = true, message = "Something wrong"))
            }
        })
    }
}