package com.example.mobishalaassignment.architecture.retrofit.api.response.model.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    var results: List<MovieResult>
)
data class CastResponse(
    @SerializedName("cast")
    var cast: List<CastResult>
)
data class CastResult(
    @SerializedName("name")
    val castName : String,
    @SerializedName("profile_path")
    val profileImage : String
)
data class MovieDetailResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Float?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("genres")
    val genreList: List<Genre>?,
    @SerializedName("runtime")
    val runtime: Int?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("status")
    val status : String?,
    @SerializedName("production_companies")
    val productionCompany : List<ProductionComapany>,
)
data class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String?
)
data class ProductionComapany(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("origin_country")
    val originCountry: String?,
)