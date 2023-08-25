package com.example.imdb.data.network.model.kinopoisk

data class Kinopoisk(
	val total: Int? = null,
	val pages: Int? = null,
	val docs: List<DocsItem?>? = null,
	val limit: Int? = null,
	val page: Int? = null
)
