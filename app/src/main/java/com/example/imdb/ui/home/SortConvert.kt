package com.example.imdb.ui.home

import com.example.imdb.domain.util.Constants.Companion.SORT_NAME
import com.example.imdb.domain.util.Constants.Companion.SORT_RATING_IMDB
import com.example.imdb.domain.util.Constants.Companion.SORT_RATING_KP
import com.example.imdb.domain.util.Constants.Companion.SORT_VOTES_IMDB

class SortConvert {
    fun converted(sort:String): String{
        return when(sort){
            "по рейтингу" -> SORT_RATING_KP
            "по алфавиту" -> SORT_NAME
            "рейтинг IMDB" -> SORT_RATING_IMDB
            "кол.оценок IMDB" -> SORT_VOTES_IMDB
            else -> "error"
        }
    }
}