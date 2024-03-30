import java.math.BigDecimal

class BankCardsParser: NumberStringParser {

    private val regex = Regex("^[2-6](\\d{4}[ -]){3}\\d{4}[ -]?$")

    override fun isPatternMatch(inputString: String): Boolean {
        return inputString.matches(regex)
    }

    override fun parseString(inputString: String): BigDecimal? {
        return try {
            val numberString = inputString
                .replace(" ", "")
                .replace("-", "")
            BigDecimal(numberString)
        } catch (e: Exception) {
            println("Exception while parsing bank cards number string=$inputString: $e")
            null
        }
    }

    override fun getStringType(): String {
        return "Bank Card Number"
    }

}