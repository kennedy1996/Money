package com.money.api.service

import android.util.Log
import com.money.api.entities.MoneyDtos
import com.money.api.inicializator.InicializatorRetrofit
import com.money.convertToScale

private const val TAG = "MoneyWebService"

class MoneyWebService {

    private val moneyApiService: MoneyApi =
        InicializatorRetrofit().moneyApi

    suspend fun convertMoney(contry_to: String, country_from: String, money: Double): MoneyDtos.Money?{
        var returnV : MoneyDtos.Money? = null
        try {
            val convertMoneyReturn = moneyApiService
                .convertMoney(contry_to, country_from, money)
            returnV = MoneyDtos.Money(convertToScale(convertMoneyReturn.result))
        } catch (e: Exception) {
            Log.e(TAG, "moneyApiService: ", e)
        }
        return returnV
    }
}