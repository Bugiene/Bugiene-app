package com.example.bugiene.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/** Raihan Chaira on 5/20/2023
 * raihanchaira21@gmail.com
 */
@Parcelize
data class Article(
    val title: String,
    val field: String,
    val photo: Int
    ) : Parcelable
