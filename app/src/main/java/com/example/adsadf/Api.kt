package com.example.adsadf

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("forces")
    fun getForces(): Call<List<Forces>>

    @GET("forces/{forceName}")
    fun getSpecialForce(@Path("forceName") forceName: String): Call<SpecialForce>

    @GET("crimes-at-location")
    fun getCrimesAtLocation( @Query("date") date: String,
                             @Query("lat") lat: String,
                             @Query("lng") lng: String): Call<List<Crime>>

    @GET("crimes-no-location")
    fun getCrimesWithoutLocation( @Query("category") category : String,
                                  @Query("force") force: String,
                                  @Query("date") date: String): Call<List<Crime>>

    companion object {
        const val BASE_URL = "https://data.police.uk/api/"
    }
}
