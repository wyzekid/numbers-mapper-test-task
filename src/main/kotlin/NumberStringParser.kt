import java.math.BigDecimal

interface NumberStringParser {

    fun isPatternMatch(inputString: String): Boolean
    fun parseString(inputString: String): BigDecimal?
    fun getStringType(): String

}