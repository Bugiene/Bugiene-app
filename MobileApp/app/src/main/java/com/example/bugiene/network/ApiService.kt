package com.example.bugiene.network

import com.example.bugiene.model.DetectionResponse
import com.example.bugiene.model.HistoryResponse
import com.example.bugiene.model.LoginResponse
import com.example.bugiene.model.RegisterResponse
import com.example.bugiene.model.UserResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

/** Raihan Chaira on 5/26/2023
 * raihanchaira21@gmail.com
 */
interface ApiService {
    @FormUrlEncoded
    @POST("register")
    fun registerUser(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("login")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @GET("getUser")
    fun getUser(
        @Header("Authorization") authorization: String,
    ): Call<UserResponse>

    @GET("getHistory")
    fun getHistory(
        @Header("Authorization") authorization: String,
        @Query("userId") userId : String
    ): Call<HistoryResponse>

    @Multipart
    @POST("postDetection")
    fun postDetection(
        @Header("Authorization") authorization: String,
        @Part file: MultipartBody.Part
    ) : Call<DetectionResponse>
}