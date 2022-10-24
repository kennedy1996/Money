package com.money

fun convertToScale(input: Double) = String.format("%.${3}f", input)