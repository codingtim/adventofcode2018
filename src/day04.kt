data class Sleep(val date: String, val from: Int, val until: Int)
data class Guard(val id: Int, val sleeps: List<Sleep>)

internal fun getGuardIdWhoSleptMost(listOf: List<Guard>): Int {
    return listOf.map { guard -> Pair(guard.id, totalSleep(guard.sleeps)) }
            .sortedBy { (_, sleepTime) -> -sleepTime }
            .first()
            .first
}

private fun totalSleep(sleeps: List<Sleep>): Int {
    return sleeps.map { (_, from, until) -> until - from }.sum()
}

internal fun findMinuteMostSleptWithFrequency(guard: Guard): Pair<Int, Int> {
    val map: Map<Int, Int> = guard.sleeps.flatMap { (_, from, until) -> IntRange(from, until) }
            .fold(mutableMapOf()) { map, i ->
                map.compute(i) { _, count -> if (count == null) 1 else count + 1 }
                map
            }
    return map.toList().sortedBy { (_, count) -> -count }.first()
}