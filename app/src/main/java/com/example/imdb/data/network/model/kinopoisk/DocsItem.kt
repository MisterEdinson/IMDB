package com.example.imdb.data.network.model.kinopoisk

data class DocsItem(
	val year: Int? = null,
	val rating: Rating? = null,
	val externalId: ExternalId? = null,
	val description: String? = null,
	val countries: List<CountriesItem?>? = null,
	val shortDescription: String? = null,
	val type: String? = null,
	val movieLength: Int? = null,
	val names: List<NamesItem?>? = null,
	val genres: List<GenresItem?>? = null,
	val name: String? = null,
	val enName: Any? = null,
	val logo: Logo? = null,
	val votes: Votes? = null,
	val id: Int? = null,
	val alternativeName: String? = null,
	val poster: Poster? = null,
	val watchability: Watchability? = null,
	val color: String? = null
)
