
internal fun react(chain: String): String {
    fun reactsWith(a: Char, b: Char): Boolean {
        return if (a in 'a'..'z' && b in 'A'..'Z') {
            return a.toUpperCase() == b
        } else if (a in 'A'..'Z' && b in 'a'..'z') {
            return a == b.toUpperCase()
        } else {
            false
        }
    }

    tailrec fun react(polymer: MutableList<Char>, index: Int): String {
        if (index == -1) return react(polymer, 0)
        return if (index >= polymer.size - 1) {
            polymer.joinToString(separator = "")
        } else {
            if (reactsWith(polymer[index], polymer[index + 1])) {
                polymer.removeAt(index)
                polymer.removeAt(index)
                react(polymer, index - 1)
            } else {
                react(polymer, index + 1)
            }
        }
    }
    return react(chain.toMutableList(), 0)
}

internal fun removeAll(c: Char, input: String) = input.replace(Regex("[$c${c.toLowerCase()}]"), "")

internal fun findCharToRemoveForShortestLength(input: String): Pair<Char, Int> {
    return CharRange('A', 'Z')
            .map { c -> Pair(c, removeAll(c, input)) }
            .map { (c, cleanedInput) -> Pair(c, react(cleanedInput)) }
            .map { (c, reacted) -> Pair(c, reacted.length) }
            .sortedBy { (_, length) -> length }
            .first()
}