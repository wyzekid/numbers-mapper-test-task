import java.math.BigDecimal

class BankCardsParser: NumberStringParser {

    private val regex = Regex("^\\d{4}[- ]\\d{4}[- ]\\d{4}[- ]\\d{4}$")

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
            println("Exception while parsing bank cards number string=$inputString: $e")
            return null
        }
    }

    override fun getStringType(): String {
        return "Bank Card Number"
    }

}