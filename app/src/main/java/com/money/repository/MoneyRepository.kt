package com.money.repository

import com.money.api.service.MoneyWebService
import com.money.api.entities.MoneyDtos

class MoneyRepository {
    private val webClient = MoneyWebService()

    suspend fun convertMoney(contry_to: String, country_from: String, money: Double): MoneyDtos.Money? {
        return webClient.convertMoney(contry_to, country_from, money)
    }
}