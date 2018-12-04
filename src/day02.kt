internal fun checksum(input: List<String>): Int {
    return input.map { checksum(it.toCharArray()) }
            .reduce { (accTwos, accThrees), (twos, threes) -> Pair(accTwos + twos, accThrees + threes) }
            .let { (twos, threes) ->  twos * threes }
}

internal fun checksum(input: CharArray): Pair<Int, Int> {
    val map: MutableMap<Char, Int> = input.fold(mutableMapOf()) { map, c ->
        map.compute(c) { _, count -> if (count == null) 1 else count + 1 }
        map
    }
    return Pair(
            if (map.values.contains(2)) 1 else 0,
            if (map.values.contains(3)) 1 else 0
    )
}

internal fun diff(input: List<String>): String {
    for (s in input) {
        for (compare in input) {
            //we could skip when s == compare but we'll just calculate and skip diffCount = 0 :)
            if(diffCount(s, compare) == 1) return diff(s, compare)
        }
    }
    throw IllegalArgumentException("not found")
}

internal fun diff(first: String, second: String): String {
    return diff(first.toList(), second.toList(), "")
}

private tailrec fun diff(first: List<Char>, second: List<Char>, acc: String): String {
    if(first.isEmpty()) return acc
    return if(first.head == second.head) {
        diff(first.tail, second.tail, acc.plus(first.head))
    } else {
        diff(first.tail, second.tail, acc)
    }
}

internal fun diffCount(first: String, second: String): Int {
    return diffCount(first.toList(), second.toList(), 0)
}

private tailrec fun diffCount(first: List<Char>, second: List<Char>, acc: Int): Int {
    if(first.isEmpty()) return acc
    if(acc == 2) return acc
    return if(first.head != second.head) {
        diffCount(first.tail, second.tail, acc + 1)
    } else {
        diffCount(first.tail, second.tail, acc)
    }
}

