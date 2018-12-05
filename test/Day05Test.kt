import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.test.assertEquals

internal class Day05Test {

    @Test
    internal fun reactions() {
        assertEquals("", react("aA"))
        assertEquals("aa", react("aa"))
        assertEquals("AA", react("AA"))
        assertEquals("", react("Aa"))
        assertEquals("cc", react("cAac"))
        assertEquals("", react("CAac"))
        assertEquals("d", react("CAacd"))
        assertEquals("dabCBAcaDA", react("dabAcCaCBAcCcaDA"))
    }

    @Test
    internal fun puzzle() {
        val length = react(getInput()).length
        println(length)
    }

    @Test
    internal fun remove() {
        assertEquals("dbcCCBcCcD", removeAll('A', "dabAcCaCBAcCcaDA"))
        assertEquals("daAcCaCAcCcaDA", removeAll('B', "dabAcCaCBAcCcaDA"))
        assertEquals("dabAaBAaDA", removeAll('C', "dabAcCaCBAcCcaDA"))
        assertEquals("abAcCaCBAcCcaA", removeAll('D', "dabAcCaCBAcCcaDA"))
    }

    @Test
    internal fun charToRemove() {
        assertEquals('C', findCharToRemoveForShortestLength("dabAcCaCBAcCcaDA").first)
    }

    @Test
    internal fun puzzlePart2() {
        val input = getInput()
        val result = findCharToRemoveForShortestLength(input)
        println("${result.first}/${result.first.toLowerCase()}")
        println(result.second)
    }

    private fun getInput(): String {
        return Files.readAllLines(Paths.get("test", "day05"))[0]
    }
}