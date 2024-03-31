import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

class CurrenciesNumbersParserTest {

    private val positiveNumbersParser = PositiveNumbersParser()
    private val currencyNumbersParser = CurrencyNumbersParser(
        positiveNumbersParser = positiveNumbersParser,
        negativeNumbersParser = NegativeNumbersParser(positiveNumbersParser = positiveNumbersParser)
    )

    @Test
    fun shouldReturnCurrencyWithPrefixComma() {
        val number = currencyNumbersParser.parseString("$234,567,111")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(234567111), number)
    }

    @Test
    fun shouldReturnCurrencyWithPrefixCommaSpace() {
        val number = currencyNumbersParser.parseString("$ 234,567,111")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(234567111), number)
    }

    @Test
    fun shouldReturnCurrencyWithPostfix() {
        val number = currencyNumbersParser.parseString("234,567,111$")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(234567111), number)
    }

    @Test
    fun shouldReturnCurrencyWithPostfixSpace() {
        val number = currencyNumbersParser.parseString("34.567.111 $")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(34567111), number)
    }

    @Test
    fun shouldReturnCurrencyNegativeWithSpace() {
        val number = currencyNumbersParser.parseString("-$ 4 567 111")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(-4567111), number)
    }

    @Test
    fun shouldReturnCurrencyNegativeWithSpace_2() {
        val number = currencyNumbersParser.parseString("$- 4 567 111")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(-4567111), number)
    }

    @Test
    fun shouldReturnCurrencyNegativeWithoutSpace() {
        val number = currencyNumbersParser.parseString("$-4 567 111")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(-4567111), number)
    }

    @Test
    fun shouldReturnCurrencyWithSpaceAfter() {
        val number = currencyNumbersParser.parseString("4 567 111 -$")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(-4567111), number)
    }

    @Test
    fun shouldReturnCurrencyWithSpaceAfter_2() {
        val number = currencyNumbersParser.parseString("4,567.111-$")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(-4567.111), number)
    }

    @Test
    fun shouldReturnCurrencyWithParenthesesPrefix() {
        val number = currencyNumbersParser.parseString("$(4.567,1111)")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(-4567.1111), number)
    }

    @Test
    fun shouldReturnCurrencyWithParenthesesPostfix() {
        val number = currencyNumbersParser.parseString("($4.567,1111)")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(-4567.1111), number)
    }

    @Test
    fun shouldReturnCurrencyWithParenthesesInside() {
        val number = currencyNumbersParser.parseString("(4.567,1111$)")
        println("number=$number")
        assertEquals(BigDecimal.valueOf(-4567.1111), number)
    }
}