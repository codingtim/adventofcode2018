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
    val map: MutableMap<Int, Int> = mutableMapOf()
    //TODO better more kotlin way to do this?
    guard.sleeps.flatMap { (_, from, until) -> IntRange(from, until) }
            .forEach { i ->
                map.compute(i) { _, count -> if (count == null) 1 else count + 1 }
            }
    return map.toList().sortedBy { (_, count) -> -count }.first()
}

internal fun parse(input: List<String>): List<Guard> {
    val regex = Regex("(.*) Guard #(.*?) begins shift")
    val sleepRegex = Regex("\\[(.*?) (.*?):(.*?)] (falls|wakes)(.*)")
    val iterator = input.iterator()

    var s = iterator.next()
    val matchResult = regex.matchEntire(s)!!
    var guardId = Integer.parseInt(matchResult.destructured.component2())
    val map: MutableMap<Int, Guard> = mutableMapOf()
    while (iterator.hasNext()) {
        s = iterator.next()
        val sleepMatch = sleepRegex.matchEntire(s)
        if (sleepMatch?.value == s) {
            val date = sleepMatch.destructured.component1()
            val from = Integer.parseInt(sleepMatch.destructured.component3())
            val to = Integer.parseInt(sleepRegex.matchEntire(iterator.next())!!.destructured.component3())
            if(map.containsKey(guardId)) {
                val guard = map[guardId]
                map[guardId] = Guard(guardId, guard!!.sleeps.plus(Sleep(date, from, to)))
            } else {
                map[guardId] = Guard(guardId, mutableListOf(Sleep(date, from, to)))
            }
        } else {
            val regexResult = regex.matchEntire(s)
            if (regexResult?.value == s) {
                guardId = Integer.parseInt(regexResult.destructured.component2())
            }
        }
    }
    return map.values.toList()
}