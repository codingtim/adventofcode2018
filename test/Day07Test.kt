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

    private val exampleInput = listOf(
            Step("C", listOf()),
            Step("A", listOf("C")),
            Step("B", listOf("A")),
            Step("D", listOf("A")),
            Step("F", listOf("C")),
            Step("E", listOf("F", "D", "B"))
    )

    @Test
    internal fun solveTest() {
        assertEquals(solveSteps(exampleInput), "CABDFE")
    }

    @Test
    internal fun solveStepsConcurrentTest() {
        val range = CharRange('A', 'Z')
        assertEquals(15, solveStepsConcurrent(exampleInput, 2) { s -> range.indexOf(s.first())})
    }

    @Test
    internal fun puzzle() {
        //TODO compose these functions?
        println(solveSteps(parseSteps(getInput())))
    }

    @Test
    internal fun puzzleConcurrent() {
        val range = CharRange('A', 'Z')
        println(solveStepsConcurrent(parseSteps(getInput()), 5){ s -> 60 + range.indexOf(s.first())})
    }

    private fun getInput(): List<String> {
        return Files.readAllLines(Paths.get("test", "day07"))
    }


    private fun parseSteps(input: List<String>): List<Step> {
        return input.mapNotNull { Regex("Step (.) must be finished before step (.) can begin.").matchEntire(it) }
                .flatMap { listOf(
                        Step(it.destructured.component1(), listOf()),
                        Step(it.destructured.component2(), listOf(it.destructured.component1()))
                )}
                .fold(mutableMapOf<String, Step>()) {map, step ->
                    map.compute(step.name) {_, s -> step.join(s)}
                    map
                }
                .values
                .toList()
                .sortedWith(StepComparator)
    }
}