package com.example.bugiene.network

import com.example.bugiene.model.LoginResponse
import com.example.bugiene.model.RegisterResponse
import com.example.bugiene.model.UserResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

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
        @Field("email") name: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @GET("getUser")
    fun getUser(
        @Header("Authorization") authorization: String,
    ) : Call<UserResponse>
}