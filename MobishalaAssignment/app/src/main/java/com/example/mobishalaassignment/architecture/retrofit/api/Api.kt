package com.example.mobishalaassignment.architecture.retrofit.api

import com.example.mobishalaassignment.architecture.retrofit.api.response.model.movie.CastResponse
import com.example.mobishalaassignment.architecture.retrofit.api.response.model.movie.MovieDetailResponse
import com.example.mobishalaassignment.architecture.retrofit.api.response.model.movie.MovieResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface Api {
    @GET("movie/popular")
    fun getPopularMovies():Observable<MovieResponse>
    @GET("movie/now_playing")
    fun getNowPlayingMovies():Observable<MovieResponse>
    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") movieId: Int): Observable<MovieDetailResponse>
    @GET("movie/{id}/credits")
    fun getCastDetails(@Path("id") movieId: Int): Observable<CastResponse>
}