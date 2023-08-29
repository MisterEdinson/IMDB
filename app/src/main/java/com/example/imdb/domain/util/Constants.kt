package com.example.imdb.domain.util

class Constants {
    companion object {
        const val BASE_URL = "https://api.kinopoisk.dev/v1.3/"
        const val TOKEN = "YTF7QW7-P734CS9-PSVB7FF-P6NQ28A"
        //const val TOKEN = "RM8MB09-WAKMH3J-JRPG286-ABZ48NZ"

        const val SORT_LIMIT = 20
        const val SORT_RATING_KP = "rating.kp"
        const val SORT_NAME = "name"
        const val SORT_RATING_IMDB = "rating.imdb"
        const val SORT_VOTES_IMDB = "votes.imdb"
        const val SORT_TYPE = "movie"

        const val FAVORITE = 1
        const val NO_FAVORITE = 0

    }
}