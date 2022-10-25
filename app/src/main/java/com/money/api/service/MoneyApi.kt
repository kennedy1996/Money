package com.money.api.service

import com.money.api.entities.MoneyDtos
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MoneyApi {

    @Headers(
        "apikey: w1IlS27iE121UDVE6KgUE5GsJXPJ2tBV"
    )
    @GET("convert")
    suspend fun convertMoney(
        @Query("to") country_to: String,
        @Query("from") country_from: String,
        @Query("amount") to: Double
    ): MoneyDtos.Convert


}