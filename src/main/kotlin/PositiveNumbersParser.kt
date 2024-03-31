import java.math.BigDecimal
import java.math.RoundingMode

class PositiveNumbersParser: NumberStringParser {

    private val regex = Regex("""\d{1,3}(?:[,.\s]?\d{1,10})*""")

    override fun isPatternMatch(inputString: String): Boolean {
        return regex.matches(inputString)
    }

    override fun parseString(inputString: String): BigDecimal? {
        try {
            val matchResult = regex.matchEntire(inputString) ?: return null
            val decimalString = matchResult.value.processDecimalParts()
            if (decimalString != null) {
                val number = BigDecimal(decimalString)
                val decimalPartLength = decimalString.split(".")[1].length
                return number.setScale(decimalPartLength, RoundingMode.HALF_UP)
            }
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

    private fun String.isSimpleDecimal(): Boolean {
        val regex = Regex("\\d{1,15}[,.]\\d{2}")
        return regex.matches(this)
    }

    private fun String.processDecimalParts(): String? {
        if (this.isSimpleDecimal()) {
            return this.replace(",", ".")
        }
        if (this.contains(" ") && this.contains(".")) {
            if (this.indexOf(" ") < this.indexOf('.')) {
                return this.replace(" ", "")
            }
        }
        if (this.contains(" ") && this.contains(",")) {
            if (this.indexOf(" ") < this.indexOf(',')) {
                return this.replace(" ", "")
                    .replace(",", ".")
            }
        }
        if (this.contains(",") && this.contains(".")) {
            if (this.indexOf(",") < this.indexOf(".")) {
                return this.replace(",", "")
            }
        }
        if (this.contains(".") && this.contains(",")) {
            if (this.indexOf(".") < this.indexOf(",")) {
                return this
                    .replace(".", "")
                    .replace(",", ".")
            }
        }
        return null
    }
}