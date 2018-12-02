internal fun freq(input: List<String>): Int {
    return freq(input, 0)
}

internal fun freq(input: String): Int {
    val parts = input.split(", ")
    return freq(parts, 0)
}

private fun freq(parts: List<String>, acc: Int): Int {
    return if (parts.isEmpty()) {
        acc
    } else {
        val operation = parts.head
        val amount = Integer.parseInt(operation.drop(1))
        when (operation.first()) {
            '+' -> freq(parts.tail, acc + amount)
            '-' -> freq(parts.tail, acc - amount)
            else -> throw IllegalArgumentException("invalid operation $operation")
        }
    }
}

internal fun double(input: String): Int {
    val parts = input.split(", ")
    return double(parts)
}

internal fun double(input: List<String>): Int {
    return double(input, 0, mutableSetOf(0), input)
}

private tailrec fun double(parts: List<String>, acc: Int, knownFreqs: MutableSet<Int>, freshParts: List<String>): Int {
    return if (parts.isEmpty()) {
        double(freshParts, acc, knownFreqs, freshParts)
    } else {
        val operation = parts.head
        val amount = Integer.parseInt(operation.drop(1))
        val freq = when (operation.first()) {
            '+' -> acc + amount
            '-' -> acc - amount
            else -> throw IllegalArgumentException("invalid operation $operation")
        }
        return if(!knownFreqs.add(freq)) {
            freq
        } else {
            double(parts.tail, freq, knownFreqs, freshParts)
        }
    }
}

val <T> List<T>.tail: List<T>
    get() = drop(1)

val <T> List<T>.head: T
    get() = first()