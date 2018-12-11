internal fun fuelCellPower(x: Int, y: Int, serialNumber: Int): Int {
    val rackId = x + 10
    val total = (rackId * y + serialNumber) * rackId
    return (total / 100 % 10) - 5
}

private fun fuelSquare(topLeftLocation: FuelCellLocation, serialNumber: Int): FuelCellSquare {
    val totalPower = IntRange(0, 2).flatMap { x -> IntRange(0, 2).map { y -> Pair(x, y) } }
            .map { (x, y) -> Pair(topLeftLocation.x + x, topLeftLocation.y + y) }
            .map { fuelCellPower(it.first, it.second, serialNumber) }
            .sum()
    return FuelCellSquare(topLeftLocation, totalPower)
}

data class FuelCellLocation(val x: Int, val y: Int)

data class FuelCellSquare(val topLeftLocation: FuelCellLocation, val totalPower: Int)

internal fun simulateGrid(serialNumber: Int): List<FuelCellSquare> {
    return IntRange(1, 298)
            .flatMap { x -> IntRange(1, 298).map { y -> FuelCellLocation(x, y) } }
            .map { fuelSquare(it, serialNumber) }
}

internal fun findHighestPowerSquare(serialNumber: Int) = simulateGrid(serialNumber).sortedBy { -it.totalPower }.first()
