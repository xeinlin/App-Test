package com.example.apptest.api

import com.example.apptest.api.model.MoviePage
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("discover/movie")
    fun getMovies(): Single<MoviePage>

}