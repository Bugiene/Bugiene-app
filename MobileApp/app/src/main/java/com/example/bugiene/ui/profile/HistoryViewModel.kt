package com.example.bugiene.ui.profile

import android.app.Application
import android.content.ContentValues
import android.preference.PreferenceManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bugiene.model.HistoryResponse
import com.example.bugiene.model.UserResultResponse
import com.example.bugiene.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/** Raihan Chaira on 6/2/2023
 * raihanchaira21@gmail.com
 */
class HistoryViewModel (application : Application) : AndroidViewModel(application) {

    val history = MutableLiveData<HistoryResponse>()

    private val _isLoading = MutableLiveData<Boolean>()

    private val token =
        PreferenceManager.getDefaultSharedPreferences(application).getString("PREF_TOKEN", "")

    private val id =
        PreferenceManager.getDefaultSharedPreferences(application).getString("PREF_ID", "")

//    init {
//        getHistory()
//    }

    fun getHistory() {
        _isLoading.value = true
        val hasil = "Bearer $token"
        if (token?.isNotEmpty() == true) {
            Log.d("MSGMODEL", "pesanHis = {$hasil}")
            if (id != null) {
                Log.d("hisviewmodelHASILID", "User ID: $id")
                ApiConfig.getApiService().getHistory(hasil, id)
                    .enqueue(object : Callback<HistoryResponse> {
                        override fun onResponse(
                            call: Call<HistoryResponse>,
                            response: Response<HistoryResponse>
                        ) {
                            _isLoading.value = false
                            if (response.isSuccessful) {
                                history.value = response.body()
                            }
                        }

                        override fun onFailure(call: Call<HistoryResponse>, t: Throwable) {
                            _isLoading.value = false
                            Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                        }

                    })
            }
        }
    }
}