package com.example.bugiene.model

import com.google.gson.annotations.SerializedName

/** Raihan Chaira on 6/11/2023
 * raihanchaira21@gmail.com
 */
class DetectionResponse(
    @field:SerializedName("status")
    val status: Boolean,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("result")
    val result : DetectionResultResponse
)
