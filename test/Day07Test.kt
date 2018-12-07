import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.test.assertEquals

internal class Day07Test {

    @Test
    internal fun parseTest() {
        val input = listOf("Step C must be finished before step A can begin.",
                "Step C must be finished before step F can begin.",
                "Step A must be finished before step B can begin.",
                "Step A must be finished before step D can begin.",
                "Step B must be finished before step E can begin.",
                "Step D must be finished before step E can begin.",
                "Step F must be finished before step E can begin.")
        val result = parseSteps(input)
        assertEquals(listOf(
                Step("C", listOf()),
                Step("A", listOf("C")),
                Step("B", listOf("A")),
                Step("D", listOf("A")),
                Step("F", listOf("C")),
                Step("E", listOf("F", "D", "B"))
        ), result)
    }

    @Test
    internal fun solveTest() {
        val input = listOf(
                Step("C", listOf()),
                Step("A", listOf("C")),
                Step("B", listOf("A")),
                Step("D", listOf("A")),
                Step("F", listOf("C")),
                Step("E", listOf("F", "D", "B"))
        )
        assertEquals(solveSteps(input), "CABDFE")
    }

    @Test
    internal fun puzzle() {
        //TODO compose these functions?
        println(solveSteps(parseSteps(getInput())))
    }

    private fun getInput(): List<String> {
        return Files.readAllLines(Paths.get("test", "day07"))
    }
}