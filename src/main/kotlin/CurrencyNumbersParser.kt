import java.math.BigDecimal

class CurrencyNumbersParser(
    private val negativeNumbersParser: NegativeNumbersParser,
    private val positiveNumbersParser: PositiveNumbersParser
): NumberStringParser {

    private val regexRemoveSpaces = Regex("""(?<!\d)\s(?!\d)""")

    override fun isPatternMatch(inputString: String): Boolean {
        return inputString.contains(Regex("[$£]"))
    }

    override fun parseString(inputString: String): BigDecimal? {
        try {
            val replacedString = inputString.replaceSymbols()
            if (positiveNumbersParser.isPatternMatch(replacedString)) {
                return positiveNumbersParser.parseString(replacedString)
            }
            if (negativeNumbersParser.isPatternMatch(inputString)) {
                return negativeNumbersParser.parseString(replacedString)
            }
            println("Currency number doesn't match positive or negative number")
            return null
        } catch (e: Exception) {
            println("Exception while parsing currencies number string=$inputString: $e")
            return null
        }
    }

    override fun getStringType(): String {
        return "Currency Number"
    }

    private fun String.replaceSymbols(): String {
        return this
            .replace(regexRemoveSpaces, "")
            .replace("[$£]", "")
    }

}