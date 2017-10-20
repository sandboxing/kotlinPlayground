package cracking

data class Point(val x: Double, val y: Double)

data class Line(val a: Double, val b: Double) {

    fun computeX(y: Double) = (y - b) / a
    fun computeY(x: Double) = a * x + b

    companion object Factory {
        fun fromTwoPoints(first: Point, second: Point): Line {
            val a = (second.y - first.y) / (second.x - first.x)
            val b = first.y - a * first.x

            return Line(a, b)
        }
    }
}

fun intersectionFor(first: Line, second: Line): Point? {
    if (first.a == second.a) {
        return if (first.b != second.b) null else Point(0.0, first.computeY(0.0))
    }

    val y = (second.b - second.a * first.b / first.a) / (1 - second.a / first.a)
    return Point(first.computeX(y), y)
}

fun main(args: Array<String>) {
    val lineA = Line.fromTwoPoints(Point(1.0, 5.0), Point(2.0, 6.0))
    val lineB = Line.fromTwoPoints(Point(1.0, 2.0), Point(1.0, 3.0))

    println(lineA)
    println(lineB)
    println(intersectionFor(lineA, lineB))
}