import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.test.assertEquals

class Day01Test {


    @Test
    fun plus() {
        val freq = freq("+1, +1, +1")
        assertEquals(3, freq)
    }

    @Test
    fun min() {
        val freq = freq("-1, -2, -3")
        assertEquals(-6, freq)
    }

    @Test
    fun combination() {
        val freq = freq("+1, +1, -2")
        assertEquals(0, freq)
    }

    @Test
    internal fun puzzle() {
        val input = Files.readAllLines(Paths.get("test", "day01"))
        println(freq(input))
    }

    @Test
    internal fun double() {
        assertEquals(0, double("+1, -1"))
        assertEquals(10, double("+3, +3, +4, -2, -4"))
        assertEquals(5, double("-6, +3, +8, +5, -6"))
        assertEquals(14, double("+7, +7, -2, -7, -4"))
    }

    @Test
    internal fun puzzleDouble() {
        val input = Files.readAllLines(Paths.get("test", "day012"))
        println(double(input))
    }
}