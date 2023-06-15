package com.example.bugiene.model

import com.google.gson.annotations.SerializedName

/** Raihan Chaira on 5/30/2023
 * raihanchaira21@gmail.com
 */
class UserResponse (
    @field:SerializedName("status")
    val error: Boolean,
    @field:SerializedName("message")
    val message : String,
    @field:SerializedName("user")
    val user : UserResultResponse
)