package cracking

/**
 * Given a two-dimensional graph with points on it,
 * find a line which passes the most number of points
 */
fun bestDagger(graph: List<Point>): Line {
    if (graph.size == 1) return Line(0.0, graph[0].y)

    if (graph.size == 2) return Line.fromTwoPoints(graph[0], graph[1])

    val lines = mutableMapOf<Line, Int>()
    (0..(graph.size - 1)).forEach { i ->
        ((i + 1)..(graph.size - 1)).forEach { j ->
            val line = Line.fromTwoPoints(graph[i], graph[j])
            lines.put(line, lines.getOrDefault(line, 1) + 1)
        }
    }
    return lines.maxBy { entry -> entry.value }!!.key
}

fun main(args: Array<String>) {
    println(
            bestDagger(
                    listOf(
                            Point(4.0, 7.0)
                    )))
    println(
            bestDagger(
                    listOf(
                            Point(0.0, 0.0),
                            Point(2.0, 2.0)
                    )))
    println(
            bestDagger(
                    listOf(
                            Point(0.0, 0.0),
                            Point(2.0, 2.0),
                            Point(4.0, 3.0),
                            Point(7.0, 4.5)
                    )))
}