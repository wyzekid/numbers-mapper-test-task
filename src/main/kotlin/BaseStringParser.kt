class BaseStringParser {
    private val bankCardsParser = BankCardsParser()
    private val positiveNumbersParser = PositiveNumbersParser()
    private val negativeNumbersParser = NegativeNumbersParser(positiveNumbersParser = positiveNumbersParser)
    private val currencyNumbersParser = CurrencyNumbersParser(
        positiveNumbersParser = positiveNumbersParser,
        negativeNumbersParser = negativeNumbersParser
    )

    private val stringParsers = listOf(
        bankCardsParser,
        positiveNumbersParser,
        negativeNumbersParser,
        currencyNumbersParser
    )

    fun parseNumericString(inputString: String?): ParsingResult {
        if (inputString?.isInvalid()!!) {
            return ParsingResult(parsedNumber = null, description = "Invalid input string format")
        }
        for (mapper in stringParsers) {
            val isMatch = mapper.isPatternMatch(inputString)
            if (!isMatch) {
                continue
            }
            return ParsingResult(
                parsedNumber = mapper.parseString(inputString),
                description = mapper.getStringType()
            )
        }
        return ParsingResult(parsedNumber = null, description = "Unknown input string format")
    }

    fun parseCustomNumericString(
        inputString: String?,
        customParser: NumberStringParser
    ): ParsingResult {
        if (inputString?.isInvalid()!!) {
            return ParsingResult(parsedNumber = null, description = "Invalid input string format")
        }
        if (!customParser.isPatternMatch(inputString)) {
            return ParsingResult(
                parsedNumber = null,
                description = "String cannot be parsed with provided parser"
            )
        }
        return ParsingResult(
            parsedNumber = customParser.parseString(inputString),
            description = customParser.getStringType()
        )
    }

    private fun String.isInvalid(): Boolean {
        return this.isBlank() || this.length > 32
    }
}