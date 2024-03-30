package ru.raiffeisen.fxpi.external.rates.ru.raiffeisen.fxpi.external.rates.mapper

import java.math.BigDecimal

class BankCardsParser: NumberStringParser {

    private val regex = Regex("^[2-6](\\d{4}[ -]){3}\\d{4}[ -]?$")

    override fun isPatternMatch(inputString: String): Boolean {
        return inputString.matches(regex)
    }

    override fun parseString(inputString: String): BigDecimal? {
        try {
            val matchResult = regex.matchEntire(inputString) ?: return null
            val numberString = matchResult.value
                .replace(" ", "")
                .replace("-", "")
            return BigDecimal(numberString)
        } catch (e: Exception) {
            TODO("Not yet implemented")
        }
    }

    override fun getStringType(): String {
        return "Bank Card Number"
    }

}