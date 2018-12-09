import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day09Test {

    @Test
    internal fun marblesTest() {
        assertEquals(32, maxMarbleScore(9, 25))
        assertEquals(8317, maxMarbleScore(10, 1618))
        assertEquals(146373, maxMarbleScore(13, 7999))
        assertEquals(2764, maxMarbleScore(17, 1104))
        assertEquals(54718, maxMarbleScore(21, 6111))
        assertEquals(37305, maxMarbleScore(30, 5807))
    }

    @Test
    internal fun puzzle(){
        println(maxMarbleScore(455, 71223))
    }
}