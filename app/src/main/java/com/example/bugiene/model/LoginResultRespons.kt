package com.example.bugiene.model

import com.google.gson.annotations.SerializedName

/** Raihan Chaira on 5/29/2023
 * raihanchaira21@gmail.com
 */
class LoginResultRespons (
    @field:SerializedName("userId")
    val userId: String,
    @field:SerializedName("username")
    val username : String,
    @field:SerializedName("email")
    val email : String,
    @field:SerializedName("accessToken")
    val accessToken : String,
    )