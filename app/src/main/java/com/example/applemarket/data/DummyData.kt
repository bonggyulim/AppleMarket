package com.example.applemarket.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DummyData(
    val num: Int,
    val image: Int,
    val product: Int,
    val introduction: Int,
    val sellerId: String,
    val price: Int,
    val address: String,
    var like: Int,
    var chat: Int,
    var check: Boolean
) : Parcelable
