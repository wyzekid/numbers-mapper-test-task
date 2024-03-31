import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

class PositiveNumbersParserTest {

    private val positiveNumbersParser = PositiveNumbersParser()

    @Test
    fun shouldReturnPositiveNumberWithoutSeparators() {
        val number = positiveNumbersParser.parseString("234567111")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(234567111), number)
    }

    @Test
    fun shouldReturnPositiveNumberSimpleDotDecimal() {
        val number = positiveNumbersParser.parseString("101.23")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(101.23), number)
    }

    @Test
    fun shouldReturnPositiveNumberSimpleCommaDecimal() {
        val number = positiveNumbersParser.parseString("1455,10")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(1455.10).setScale(2), number)
    }

    @Test
    fun shouldReturnPositiveNumberWithComma() {
        val number = positiveNumbersParser.parseString("234,567,111")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(234567111), number)
    }

    @Test
    fun shouldReturnPositiveNumberWithPoint() {
        val number = positiveNumbersParser.parseString("34.567.111")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(34567111), number)
    }

    @Test
    fun shouldReturnPositiveNumberWithSpace() {
        val number = positiveNumbersParser.parseString("4 567 111")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(4567111), number)
    }

    @Test
    fun shouldReturnPositiveNumberWithDecimalComma() {
        val number = positiveNumbersParser.parseString("4,567.111")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(4567.111), number)
    }

    @Test
    fun shouldReturnPositiveNumberWithDecimalPoint() {
        val number = positiveNumbersParser.parseString("4.567,1111")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(4567.1111), number)
    }

    @Test
    fun shouldReturnPositiveNumberWithSpaceAndPoint() {
        val number = positiveNumbersParser.parseString("4 567.1111")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(4567.1111), number)
    }

    @Test
    fun shouldReturnPositiveNumberWithSpaceAndComma() {
        val number = positiveNumbersParser.parseString("4 567,1111")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(4567.1111), number)
    }


}