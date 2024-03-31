import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

class BankCardsParserTest {
    private val cardsParser = BankCardsParser()

    @Test
    fun shouldParseCardNumberWithZero() {
        val number = cardsParser.parseString("0234-5678-1234-5678")
        assertEquals(BigDecimal.valueOf(234567812345678), number)
    }

    @Test
    fun shouldParseLineCardNumber() {
        val number = cardsParser.parseString("4321-5678-1234-5678")
        assertEquals(BigDecimal.valueOf(4321567812345678), number)
    }

    @Test
    fun shouldParseSpaceCardNumber() {
        val number = cardsParser.parseString("4321 5678 1234 5678")
        assertEquals(BigDecimal.valueOf(4321567812345678), number)
    }

    @Test
    fun shouldFailNoSpacesCardNumber() {
        val number = cardsParser.parseString("4321567812345678")
        assertEquals(null, number)
    }

    @Test
    fun shouldFailParseLongCardNumber() {
        val number = cardsParser.parseString("4321-5678-1234-5678-1234")
        assertEquals(null, number)
    }

    @Test
    fun shouldFailParsePointCardNumber() {
        val number = cardsParser.parseString("4321.5678.1234.5678")
        assertEquals(null, number)
    }

}