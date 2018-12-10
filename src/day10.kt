import kotlin.math.absoluteValue

internal data class MovingPoint(val x: Int, val y: Int, val vx: Int, val vy: Int) {
    fun move(): MovingPoint = copy(x = x + vx, y = y + vy)
}

internal tailrec fun findMessage(points: List<MovingPoint>, square: Int): List<MovingPoint> {
    val ys = points.map { it.y }
    val currentSquare = (ys.min()!! - ys.max()!!).absoluteValue
    return if (currentSquare <= square) points else findMessage(points.map { it.move() }, square)
}

internal fun printMessage(points: List<MovingPoint>) {
    val maxX = points.map { it.x }.max()!!
    val minX = points.map { it.x }.min()!!
    val maxY = points.map { it.y }.max()!!
    val minY = points.map { it.y }.min()!!
    for (y in minY..maxY) {
        for (x in minX..maxX) {
            if(points.find { point ->(point.x == x && point.y == y) } != null) {
                print('X')
            } else {
                print('.')
            }
        }
        println()
    }
}