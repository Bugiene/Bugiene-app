package com.example.bugiene.model

import com.google.gson.annotations.SerializedName

/** Raihan Chaira on 6/2/2023
 * raihanchaira21@gmail.com
 */
class HistoryResponse(
    @field:SerializedName("error")
    val error: Boolean,
    @field:SerializedName("message")
    val message : String,
    @field:SerializedName("listHistory")
    val listHistory : List<HistoryResultResponse>? = null
)
