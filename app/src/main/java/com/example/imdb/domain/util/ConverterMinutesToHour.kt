package com.example.imdb.domain.util

class ConverterMinutesToHour {
    fun converte(int:String?): String{
        val minutes = int?.toInt()
        minutes?.let {
            val hours = minutes / 60
            val remaining = minutes % 60
            return "${hours}ч ${remaining} мин"
        } ?: return "-"
    }
}