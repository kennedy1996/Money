package com.money.api.entities

class MoneyDtos {
    class Convert(
        val sucess: Boolean,
        val query: Queri,
        val result: Double
    )

    class Queri(
        val from: String,
        val to: String,
        val amount: Double
    )
    class Money(
        val result: String
    )
}