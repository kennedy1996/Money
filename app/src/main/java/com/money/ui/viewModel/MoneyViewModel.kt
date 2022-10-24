package com.money.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.money.api.entities.MoneyDtos
import com.money.repository.MoneyRepository
import kotlinx.coroutines.launch

class MoneyViewModel : ViewModel() {

    private val repository = MoneyRepository()
    private var moneyResult = MutableLiveData<MoneyDtos.Money>()

    fun convertMoney(contry_to: String, country_from: String, money: Double) {
        viewModelScope.launch {
            moneyResult.value = repository.convertMoney(contry_to, country_from, money)
        }
    }

    fun getConvertMoney(): MutableLiveData<MoneyDtos.Money> {
        return moneyResult
    }
}