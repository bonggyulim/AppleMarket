package com.example.applemarket.common

import java.text.DecimalFormat

object DecimalFormat {
    fun decimalFormat(num: Int): String {
        val dec = DecimalFormat("###,###,###원")
        return dec.format(num)
    }
}