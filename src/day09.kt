private fun marbles(players: Int, max: Int): List<Pair<Int, Int>> {
    tailrec fun marbles(currentPlayer: Int, currentMarbleIndex: Int, marble: Int, circle: List<Int>, points: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
        if (marble == max + 1) return points
        if(marble % 23 == 0) {
            val indexToRemove = if(currentMarbleIndex - 7 < 0) circle.size + currentMarbleIndex - 7 else currentMarbleIndex - 7
            val p = Pair(currentPlayer, marble + circle[indexToRemove])
            return marbles(
                    if(currentPlayer + 1 == players) 0 else currentPlayer + 1,
                    indexToRemove,
                    marble + 1,
                    circle.subList(0, indexToRemove) + circle.subList(indexToRemove + 1, circle.size),
                    points + p
            )
        }

        val nextMarbleIndex = if(currentMarbleIndex + 1 == circle.size) 0 else currentMarbleIndex + 1
        val positionOfMarble = nextMarbleIndex + 1
        return if(positionOfMarble > circle.size) {
            marbles(
                    if(currentPlayer + 1 == players) 0 else currentPlayer + 1,
                    positionOfMarble,
                    marble + 1,
                    circle + marble,
                    points
            )
        } else {
            marbles(
                    if(currentPlayer + 1 == players) 0 else currentPlayer + 1,
                    positionOfMarble,
                    marble + 1,
                    circle.subList(0, positionOfMarble) + marble + circle.subList(positionOfMarble, circle.size),
                    points
            )
        }
    }
    return marbles(0, 0, 1, listOf(0), listOf())
}

internal fun maxMarbleScore(players: Int, maxMarble: Int): Int {
    return marbles(players, maxMarble)
            .fold(mutableMapOf<Int, Int>()) { acc, pair ->
                acc.compute(pair.first) { _, count -> if (count == null) pair.second else count + pair.second }
                acc
            }
            .values
            .sorted()
            .last()
}