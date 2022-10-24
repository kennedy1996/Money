package com.money.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.money.ui.viewModel.MoneyViewModel
import com.money.R

class MoneyActivity : AppCompatActivity() {

    private val viewModel by lazy {
        val provider = ViewModelProvider(this)
        provider.get(MoneyViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.money_activity)
        title = "Money Converter EUR/USD"
        onConverter()
    }

    private fun onConverter() {
        val typeMoney = findViewById<TextView>(R.id.money_activity_convert_to_type_money)
        val buttonDolarFrom = findViewById<Button>(R.id.money_activity_button_dolar_from)
        val buttonEuroFrom = findViewById<Button>(R.id.money_activity_button_euro_from)
        val progressBar = findViewById<ProgressBar>(R.id.money_activity_progressBar)
        val valueField = findViewById<EditText>(R.id.money_activity_value_field)
        val newValue = findViewById<TextView>(R.id.money_activity_new_value)

        buttonEuroFrom.setOnClickListener {
            if (checkData(valueField)) onClickConverter(
                typeMoney,
                progressBar,
                valueField,
                "USD",
                "EUR",
                newValue
            )
        }
        buttonDolarFrom.setOnClickListener {
            if (checkData(valueField)) onClickConverter(
                typeMoney,
                progressBar,
                valueField,
                "EUR",
                "USD",
                newValue
            )
        }

    }

    private fun checkData(valueField: EditText): Boolean {
        var returnV = false
        if (valueField.text.isNullOrBlank()) {
            valueField.error = "Blank Field!"
        } else if (valueField.text.toString().toDouble() < 0) {
            valueField.error = "Number Invalid!"
        } else {
            returnV = true
        }
        return returnV
    }

    private fun onClickConverter(
        typeMoney: TextView,
        progressBar: ProgressBar,
        valueField: EditText,
        contry_to: String,
        countr_from: String,
        newValue: TextView
    ) {
        var typeMoneyValue = "to EURO"
        if (contry_to == "EUR") typeMoneyValue = "to DOLAR"
        typeMoney.text = typeMoneyValue
        progressBar.visibility = View.VISIBLE
        viewModel.convertMoney(contry_to, countr_from, valueField.text.toString().toDouble())
        afterTimeCheckConverter(
            progressBar,
            newValue,
            contry_to,
            countr_from,
            valueField.text.toString().toDouble()
        )
    }

    private fun afterTimeCheckConverter(
        progressBar: ProgressBar,
        newValue: TextView,
        country_to: String,
        country_from: String,
        toDouble: Double
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            progressBar.visibility = View.GONE
            newValue.visibility = View.VISIBLE
            val getMoney = viewModel.getConvertMoney().value
            if (getMoney != null) {
                if (getMoney.result.isNotEmpty()) {

                    val text = "$country_to $toDouble\n=\n$country_from ${getMoney.result}"

                    newValue.text = text


                } else newValue.text = "Invalid"
            } else newValue.text = "Internet Error"

        }, 2000)
    }
}