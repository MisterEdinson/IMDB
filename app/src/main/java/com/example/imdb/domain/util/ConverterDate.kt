package com.example.imdb.domain.util

import java.text.SimpleDateFormat
import java.util.Locale

class ConverterDate {
    fun dateConverter(date: String?): String {
        val inPattern = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outPattern = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        try {
            val date = inPattern.parse(date)
            return outPattern.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return "-"
    }
}