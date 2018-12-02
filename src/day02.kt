internal fun checksum(input: List<String>): Int {
    return input.map { checksum(it.toCharArray()) }
            .reduce { (accTwos, accThrees), (twos, threes) -> Pair(accTwos + twos, accThrees + threes) }
            .let { (twos, threes) ->  twos * threes }
}

internal fun checksum(input: CharArray): Pair<Int, Int> {
    val map: MutableMap<Char, Int> =  mutableMapOf()
    for (c in input) {
        map.compute(c) { _, count -> if (count == null) 1 else count + 1 }
    }
    return Pair(
            if (map.values.contains(2)) 1 else 0,
            if (map.values.contains(3)) 1 else 0
    )
}

