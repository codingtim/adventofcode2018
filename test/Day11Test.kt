import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day11Test {

    @Test
    internal fun testCellPower() {
        assertEquals(4, fuelCellPower(3, 5, 8))
        assertEquals(-5, fuelCellPower(122, 79, 57))
        assertEquals(0, fuelCellPower(217, 196, 39))
        assertEquals(4, fuelCellPower(101, 153, 71))
    }

    @Test
    internal fun testSimulateGrid() {
        val (topLeftLocation, totalPower) = findHighestPowerSquare(18)
        assertEquals(FuelCellLocation(33, 45), topLeftLocation)
        assertEquals(totalPower, 29)
        val (topLeftLocation2, totalPower2) = findHighestPowerSquare(42)
        assertEquals(FuelCellLocation(21, 61), topLeftLocation2)
        assertEquals(totalPower2, 30)
    }

    @Test
    internal fun puzzle() {
        println(findHighestPowerSquare(4455))
    }
}