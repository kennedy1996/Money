package com.money.api.inicializator

import com.money.api.service.MoneyApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class InicializatorRetrofit {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.apilayer.com/currency_data/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val moneyApi = retrofit.create(MoneyApi::class.java)
}