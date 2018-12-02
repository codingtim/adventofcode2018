import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths

internal class Day02Test {

    @Test
    fun checksumParts() {
        assertEquals(Pair(0, 0), checksum("abcdef".toCharArray()))
        assertEquals(Pair(1, 1), checksum("bababc".toCharArray()))
        assertEquals(Pair(1, 0), checksum("abbcde".toCharArray()))
        assertEquals(Pair(0, 1), checksum("abcccd".toCharArray()))
        assertEquals(Pair(1, 0), checksum("aabcdd".toCharArray()))
        assertEquals(Pair(1, 0), checksum("abcdee".toCharArray()))
        assertEquals(Pair(0, 1), checksum("ababab".toCharArray()))
    }

    @Test
    fun checksum() {
        assertEquals(12, checksum(listOf(
                "abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab"
        )))
    }

    @Test
    internal fun puzzle() {
        val input = Files.readAllLines(Paths.get("test", "day02"))
        println(checksum(input))
    }
}