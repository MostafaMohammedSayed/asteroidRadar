package com.udacity.asteroidradar.network

import com.udacity.asteroidradar.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

const val apikey="CYkLSKAQbsieLwnBLXawvGPjHn4w2QFT5FPJpH2h"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(Constants.BASE_URL)
    .build()

interface AsteroidApiService {
    @GET("neo/rest/v1/feed?&api_key=" + apikey)
    fun getNeo():
            Call<String>

    @GET("planetary/apod?api_key=" + apikey)
    fun getImgOfTheDay():
            Call<String>
}

object AsteroidApi {
    val retrofitService : AsteroidApiService by lazy {
        retrofit.create(AsteroidApiService::class.java)
    }
}



