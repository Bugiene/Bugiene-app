package com.example.bugiene.ui.profile

import android.app.Application
import android.preference.PreferenceManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bugiene.model.UserResponse
import com.example.bugiene.model.UserResultResponse
import com.example.bugiene.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/** Raihan Chaira on 5/29/2023
 * raihanchaira21@gmail.com
 */
class GetUserViewModel(application : Application) : AndroidViewModel(application) {

    private val _user = MutableLiveData<UserResultResponse>()
    val user: LiveData<UserResultResponse> = _user

    private val token =
        PreferenceManager.getDefaultSharedPreferences(application).getString("PREF_TOKEN", "")

    fun getUserData(){
        val bearer = "Bearer $token"
        ApiConfig.getApiService().getUser(bearer)
            .enqueue(object : Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful){
                        val responseBody = response.body()
                        if (responseBody != null && !responseBody.error){
                            responseBody?.user.let {
                                _user.value = it
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

}