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

    @Test
    internal fun marbleCircleTest() {
        val marbleCircle = MarbleCircle()
        assertEquals(listOf(0), marbleCircle.toList())
        marbleCircle.insert(1)
        assertEquals(listOf(0, 1), marbleCircle.toList())
        marbleCircle.insert(2)
        assertEquals(listOf(0, 2, 1), marbleCircle.toList())
        marbleCircle.insert(3)
        assertEquals(listOf(0, 2, 1, 3), marbleCircle.toList())
        marbleCircle.insert(4)
        assertEquals(listOf(0, 4, 2, 1, 3), marbleCircle.toList())
        marbleCircle.insert(5)
        assertEquals(listOf(0, 4, 2, 5, 1, 3), marbleCircle.toList())
    }

    @Test
    internal fun testRemoveCurrent() {
        val marbleCircle = MarbleCircle()
        for(i in 1..5) {
            marbleCircle.insert(i)
        }
        assertEquals(5, marbleCircle.removeCurrent())
        assertEquals(listOf(0, 4, 2, 1, 3), marbleCircle.toList())
    }

    @Test
    internal fun testMoveCounterClockWise() {
        val marbleCircle = MarbleCircle()
        for(i in 1..5) {
            marbleCircle.insert(i)
        }
        marbleCircle.moveCounterClockWise(2)
        assertEquals(4, marbleCircle.removeCurrent())
        assertEquals(listOf(0, 2, 5, 1, 3), marbleCircle.toList())
    }
}