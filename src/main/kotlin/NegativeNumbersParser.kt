import java.math.BigDecimal

class NegativeNumbersParser(private val positiveNumbersParser: PositiveNumbersParser): NumberStringParser {

    private val regexRemoveSpaces = Regex("""(?<!\d)\s(?!\d)""")

    override fun isPatternMatch(inputString: String): Boolean {
        val replacedString = inputString.replaceNegativeSymbols()
        return positiveNumbersParser.isPatternMatch(replacedString)
    }

    override fun parseString(inputString: String): BigDecimal? {
        return try {
            val replacedString = inputString.replaceNegativeSymbols()
            val parsedPositive = positiveNumbersParser.parseString(replacedString)
            parsedPositive?.multiply(BigDecimal(-1))
        } catch (e: Exception) {
            println("Exception while parsing negative number string=$inputString: $e")
            null
        }
    }

    override fun getStringType(): String {
        return "Negative Decimal Number"
    }

    private fun String.replaceNegativeSymbols(): String {
        val input = this.replace(regexRemoveSpaces, "")
        if (input.startsWith("-")) {
            return input.substring(1)
        }
        if (input.endsWith("-")) {
            return input.substring(0, input.length - 1)
        }
        if (input.startsWith("(") && input.endsWith(")")) {
            return input.substring(1, input.length - 1)
        }
        return this
    }

}