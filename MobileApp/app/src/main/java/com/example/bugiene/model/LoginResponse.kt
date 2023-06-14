package com.example.bugiene.model

import com.google.gson.annotations.SerializedName

/** Raihan Chaira on 5/29/2023
 * raihanchaira21@gmail.com
 */
class LoginResponse (
    @field:SerializedName("status")
    val status: Boolean,
    @field:SerializedName("message")
    val message : String,
    @field:SerializedName("data")
    val data : LoginResultRespons? = null
    )