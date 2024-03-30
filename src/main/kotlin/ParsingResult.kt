package ru.raiffeisen.fxpi.external.rates.ru.raiffeisen.fxpi.external.rates.mapper

import java.math.BigDecimal

data class ParsingResult(
    val parsedNumber: BigDecimal?,
    val description: String?
)
