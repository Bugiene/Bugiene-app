package com.example.bugiene.ui.login

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bugiene.model.LoginResponse
import com.example.bugiene.model.User
import com.example.bugiene.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/** Raihan Chaira on 5/29/2023
 * raihanchaira21@gmail.com
 */
class LoginViewModel : ViewModel() {

    val LoginResult = MutableLiveData<LoginResponse?>()

    fun loginUser(email : String, password : String) {
        LoginResult.value = null
        val call = ApiConfig.getApiService().loginUser(email, password)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    LoginResult.postValue(response.body())
                }else if (response.code() == 401){
                    LoginResult.postValue(LoginResponse(status = true, message = "invalid email or password"))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure : ${t.message.toString()}")
                LoginResult.postValue(LoginResponse(status = true, message = "Something wrong"))
            }
        })
    }
}