package com.example.bugiene.model

import com.google.gson.annotations.SerializedName

/** Raihan Chaira on 6/11/2023
 * raihanchaira21@gmail.com
 */
class DetectionResultResponse(
    @field:SerializedName("DetectId")
    val DetectId: String,
    @field:SerializedName("freshness")
    val freshness: Boolean,
    @field:SerializedName("accuracy")
    val accuracy : Double
)