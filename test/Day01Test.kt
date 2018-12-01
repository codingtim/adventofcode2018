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
}