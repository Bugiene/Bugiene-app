package com.example.bugiene.model

import com.google.gson.annotations.SerializedName

/** Raihan Chaira on 6/2/2023
 * raihanchaira21@gmail.com
 */
class HistoryResultResponse (
    @field:SerializedName("DetectId")
    val DetectId: String,

    @field:SerializedName("userId")
    val userId: String,

    @field:SerializedName("imageUrl")
    val imageUrl: String,

    @field:SerializedName("freshness")
    val freshness: String,

    @field:SerializedName("accuracy")
    val accuracy: Float,

    @field:SerializedName("createdAt")
    val createdAt: String
    )