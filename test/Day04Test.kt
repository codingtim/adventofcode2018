import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.test.assertEquals

internal class Day04Test {

    @Test
    internal fun mostSleptTest() {
        val guardWhoSlepMostId = getGuardIdWhoSleptMost(listOf(
                Guard(10, listOf(
                        Sleep("1518-11-01", 5, 25),
                        Sleep("1518-11-01", 30, 55),
                        Sleep("1518-11-03", 24, 29)
                )),
                Guard(99, listOf(
                        Sleep("1518-11-02", 40, 50),
                        Sleep("1518-11-04", 36, 46),
                        Sleep("1518-11-05", 45, 55)
                ))
        ))
        assertEquals(10, guardWhoSlepMostId)
    }

    @Test
    internal fun findMinuteMostSleptTest() {
        val minuteMostSlept = findMinuteMostSleptWithFrequency(
                Guard(10, listOf(
                        Sleep("1518-11-01", 5, 25),
                        Sleep("1518-11-01", 30, 55),
                        Sleep("1518-11-03", 24, 29)
                )
                )).first
        assertEquals(24, minuteMostSlept)
    }

    @Test
    internal fun parseTest() {
        val input = listOf(
                "[1518-11-01 00:00] Guard #10 begins shift",
                "[1518-11-01 00:05] falls asleep",
                "[1518-11-01 00:25] wakes up",
                "[1518-11-01 00:30] falls asleep",
                "[1518-11-01 00:55] wakes up",
                "[1518-11-01 23:58] Guard #99 begins shift",
                "[1518-11-02 00:40] falls asleep",
                "[1518-11-02 00:50] wakes up",
                "[1518-11-03 00:05] Guard #10 begins shift",
                "[1518-11-03 00:24] falls asleep",
                "[1518-11-03 00:29] wakes up",
                "[1518-11-04 00:02] Guard #99 begins shift",
                "[1518-11-04 00:36] falls asleep",
                "[1518-11-04 00:46] wakes up",
                "[1518-11-05 00:03] Guard #99 begins shift",
                "[1518-11-05 00:45] falls asleep",
                "[1518-11-05 00:55] wakes up"
        )
        val expected = listOf(
                Guard(10, listOf(
                        Sleep("1518-11-01", 5, 25),
                        Sleep("1518-11-01", 30, 55),
                        Sleep("1518-11-03", 24, 29)
                )),
                Guard(99, listOf(
                        Sleep("1518-11-02", 40, 50),
                        Sleep("1518-11-04", 36, 46),
                        Sleep("1518-11-05", 45, 55)
                ))
        )
        val parsed = parse(input)
        assertEquals(expected, parsed)
    }

    @Test
    internal fun puzzle() {
        val guards = getGuards()
        val idOfMostSlept = getGuardIdWhoSleptMost(guards)
        val guard = guards.first { g -> g.id == idOfMostSlept }
        val minuteSlept = findMinuteMostSleptWithFrequency(guard).first
        println(minuteSlept * guard.id)
    }

    @Test
    internal fun puzzlePart2() {
        val guards = getGuards()
        val mostSlept = guards.map { g ->
            val minuteAndFreq = findMinuteMostSleptWithFrequency(g)
            Triple(g.id, minuteAndFreq.first, minuteAndFreq.second)
        }
                .sortedBy { (_, _, freq) -> -freq }
                .first()
        println(mostSlept.first * mostSlept.second)
    }

    private fun getGuards(): List<Guard> {
        val lines = Files.readAllLines(Paths.get("test", "day04"))
        lines.sort()
        return parse(lines)
    }
}