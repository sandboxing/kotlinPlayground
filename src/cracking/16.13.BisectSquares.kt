package cracking

/**
 * Given two squares on a two-dimensional plane, find a line that would cut these two
 * squares in half. Assume that the top and the bottom sides of the square run parallel to the x-axis
 */
fun cuttingInHalfLine(a: Square, b: Square): ALine {
    return ALine(a.center, b.center)
}

data class ALine(
        val a: Point,
        val b: Point
)

data class Square(
        private val topLeft: Point,
        private val length: Double
) {

    val center = Point(topLeft.x + length / 2, topLeft.y + length / 2)
}

fun main(args: Array<String>) {
    println(
            cuttingInHalfLine(
                    Square(Point(5.0, 5.0), 3.0),
                    Square(Point(4.0, 4.0), 2.0)))
    println(
            cuttingInHalfLine(
                    Square(Point(-5.0, -5.0), 10.0),
                    Square(Point(-7.0, 24.0), 14.0)))
}