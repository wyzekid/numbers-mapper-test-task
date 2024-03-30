package ru.raiffeisen.fxpi.external.rates.ru.raiffeisen.fxpi.external.rates.mapper

import java.math.BigDecimal

class PositiveNumbersParser: NumberStringParser {

    private val regex = Regex("""[0-9]+([,.\s][0-9]+)?""")

    override fun isPatternMatch(inputString: String): Boolean {
        return regex.matches(inputString)
    }

    override fun parseString(inputString: String): BigDecimal? {
        try {
            val matchResult = regex.matchEntire(inputString) ?: return null
            val numberString = matchResult.value
                .replace(",", "")
                .replace(" ", "")
                .replace(".", "")
            return BigDecimal(numberString)
        } catch (e: Exception) {
            println("Exception while parsing positive number string=$inputString: $e")
            return null
        }
    }

    override fun getStringType(): String {
        return "Positive Decimal Number"
    }
}