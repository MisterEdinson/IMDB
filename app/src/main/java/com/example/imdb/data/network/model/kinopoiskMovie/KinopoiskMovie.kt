package com.example.imdb.data.network.model.kinopoiskMovie

data class KinopoiskMovie(
	val fees: Fees? = null,
	val year: Int? = null,
	val rating: Rating? = null,
	val videos: Videos? = null,
	val type: String? = null,
	val typeNumber: Int? = null,
	val logo: Logo? = null,
	val top250: Int? = null,
	val id: Int? = null,
	val watchability: Watchability? = null,
	val budget: Budget? = null,
	val updatedAt: String? = null,
	val images: Images? = null,
	val top10: Any? = null,
	val audience: List<AudienceItem?>? = null,
	val backdrop: Backdrop? = null,
	val distributors: Distributors? = null,
	val similarMovies: List<SimilarMoviesItem?>? = null,
	val countries: List<CountriesItem?>? = null,
	val shortDescription: String? = null,
	val ageRating: Int? = null,
	val persons: List<PersonsItem?>? = null,
	val sequelsAndPrequels: List<Any?>? = null,
	val deletedAt: Any? = null,
	val lists: List<Any?>? = null,
	val name: String? = null,
	val seriesLength: Any? = null,
	val spokenLanguages: List<SpokenLanguagesItem?>? = null,
	val slogan: String? = null,
	val poster: Poster? = null,
	val status: Any? = null,
	val imagesInfo: ImagesInfo? = null,
	val description: String? = null,
	val facts: List<FactsItem?>? = null,
	val movieLength: Int? = null,
	val ticketsOnSale: Boolean? = null,
	val genres: List<GenresItem?>? = null,
	val enName: Any? = null,
	val alternativeName: String? = null,
	val ratingMpaa: String? = null,
	val totalSeriesLength: Any? = null,
	val externalId: ExternalId? = null,
	val technology: Technology? = null,
	val premiere: Premiere? = null,
	val isSeries: Boolean? = null,
	val names: List<NamesItem?>? = null,
	val seasonsInfo: List<Any?>? = null,
	val votes: Votes? = null,
	val productionCompanies: List<ProductionCompaniesItem?>? = null
)
