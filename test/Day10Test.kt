import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.test.assertEquals

internal class Day10Test {

    @Test
    internal fun messageTest() {
        val input = listOf(
                "position=< 9,  1> velocity=< 0,  2>",
                "position=< 7,  0> velocity=<-1,  0>",
                "position=< 3, -2> velocity=<-1,  1>",
                "position=< 6, 10> velocity=<-2, -1>",
                "position=< 2, -4> velocity=< 2,  2>",
                "position=<-6, 10> velocity=< 2, -2>",
                "position=< 1,  8> velocity=< 1, -1>",
                "position=< 1,  7> velocity=< 1,  0>",
                "position=<-3, 11> velocity=< 1, -2>",
                "position=< 7,  6> velocity=<-1, -1>",
                "position=<-2,  3> velocity=< 1,  0>",
                "position=<-4,  3> velocity=< 2,  0>",
                "position=<10, -3> velocity=<-1,  1>",
                "position=< 5, 11> velocity=< 1, -2>",
                "position=< 4,  7> velocity=< 0, -1>",
                "position=< 8, -2> velocity=< 0,  1>",
                "position=<15,  0> velocity=<-2,  0>",
                "position=< 1,  6> velocity=< 1,  0>",
                "position=< 8,  9> velocity=< 0, -1>",
                "position=< 3,  3> velocity=<-1,  1>",
                "position=< 0,  5> velocity=< 0, -1>",
                "position=<-2,  2> velocity=< 2,  0>",
                "position=< 5, -2> velocity=< 1,  2>",
                "position=< 1,  4> velocity=< 2,  1>",
                "position=<-2,  7> velocity=< 2, -2>",
                "position=< 3,  6> velocity=<-1, -1>",
                "position=< 5,  0> velocity=< 1,  0>",
                "position=<-6,  0> velocity=< 2,  0>",
                "position=< 5,  9> velocity=< 1, -2>",
                "position=<14,  7> velocity=<-2,  0>",
                "position=<-3,  6> velocity=< 2, -1>"
        )
        val result = findMessage(parse(input), 8)
        printMessage(result)
    }

    @Test
    internal fun parseTest() {
        assertEquals(
                listOf(MovingPoint(-2, 3, 1, 0)),
                parse(listOf("position=<-2,  3> velocity=< 1,  0>"))
        )
    }

    @Test
    internal fun moveTest() {
        assertEquals(
                MovingPoint(-1, 5, 1, 2),
                MovingPoint(-2, 3, 1, 2).move()
        )
    }

    private fun parse(input: List<String>): List<MovingPoint> {
        return input.mapNotNull { Regex("position=<(.*),(.*)> velocity=<(.*),(.*)>").matchEntire(it) }
                .map { it.destructured }
                .map {
                    MovingPoint(
                            Integer.parseInt(it.component1().trim()),
                            Integer.parseInt(it.component2().trim()),
                            Integer.parseInt(it.component3().trim()),
                            Integer.parseInt(it.component4().trim())
                    )
                }
    }

    @Test
    internal fun puzzle() {
        val points = parse(getInput())
        printMessage(findMessage(points, 15))
    }

    private fun getInput() = Files.readAllLines(Paths.get("test", "day10"))

}