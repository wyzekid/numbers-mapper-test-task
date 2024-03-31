import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

class NegativeNumbersParserTest {

    private val positiveNumbersParser = PositiveNumbersParser()
    private val negativeNumbersParser = NegativeNumbersParser(positiveNumbersParser)

    @Test
    fun shouldReturnNegativeNumberWithComma() {
        val number = negativeNumbersParser.parseString("-234,567,111")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(-234567111), number)
    }

    @Test
    fun shouldReturnNegativeNumberWithPoint() {
        val number = negativeNumbersParser.parseString("34.567.111-")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(-34567111), number)
    }

    @Test
    fun shouldReturnNegativeNumberWithSpace() {
        val number = negativeNumbersParser.parseString("- 4 567 111")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(-4567111), number)
    }

    @Test
    fun shouldReturnNegativeNumberWithDecimalComma() {
        val number = negativeNumbersParser.parseString("4,567.111 -")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(-4567.111), number)
    }

    @Test
    fun shouldReturnNegativeNumberWithDecimalPoint() {
        val number = negativeNumbersParser.parseString("(4.567,1111)")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(-4567.1111), number)
    }

}