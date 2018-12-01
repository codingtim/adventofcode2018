fun freq(input: String): Int {
    val parts = input.split(", ")
    return freq(parts, 0)
}

fun freq(input: List<String>): Int {
    return freq(input, 0)
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

val <T> List<T>.tail: List<T>
    get() = drop(1)

val <T> List<T>.head: T
    get() = first()