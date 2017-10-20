package cracking

/**
 * Given an M * N matrix in which each row and each column is sorted in ascending order,
 * write a method to cracking.find an element.
 */

typealias Matrix = Array<IntArray>

data class Coordinate(val row: Int, val column: Int) {
    fun inBounds(matrix: Matrix) = row >= 0 && row < matrix.size && column >= 0 && column < matrix[0].size

    fun isBefore(c: Coordinate) = row <= c.row && column <= c.column

    companion object Factory {
        fun toAverage(min: Coordinate, max: Coordinate) =
                Coordinate(row = (min.row + max.row) / 2, column = (min.column + max.column) / 2)
    }

    override fun toString(): String = "[$row, $column]"
}

fun Matrix.findElement(target: Int, origin: Coordinate, destination: Coordinate): Coordinate {
    if (!origin.inBounds(this) || !destination.inBounds(this)) {
        return Coordinate(-1, -1)
    }

    if (this[origin.row][origin.column] == target) {
        return origin
    } else if (!origin.isBefore(destination)) {
        return Coordinate(-1, -1)
    }

    var start = origin.copy()
    val diagDist = Math.min(destination.row - origin.row, destination.column - origin.column)
    var end = Coordinate(start.row + diagDist, start.column + diagDist)
    var p: Coordinate

    while (start.isBefore(end)) {
        p = Coordinate.toAverage(start, end)

        println("p= $p, start= $start, end= $end")
        if (target > this[p.row][p.column]) {
            start = Coordinate(p.row + 1, p.column + 1)
        } else {
            end = Coordinate(p.row - 1, p.column - 1)
        }
    }

    return partitionAndSearch(target, origin, destination, start)
}

fun Matrix.partitionAndSearch(target: Int, origin: Coordinate, destination: Coordinate, pivot: Coordinate): Coordinate {
    val lowerLeftOrigin = Coordinate(pivot.row, origin.column)
    val lowerLeftDestination = Coordinate(destination.row, pivot.column - 1)
    val upperRightOrigin = Coordinate(origin.row, pivot.column)
    val upperRightDest = Coordinate(pivot.row - 1, destination.column)

    println("lowerLeftOrigin= $lowerLeftOrigin, lowerLeftDestination= $lowerLeftDestination, upperRightOrigin= $upperRightOrigin, upperRightDest= $upperRightDest")
    val lowerLeft = findElement(target, lowerLeftOrigin, lowerLeftDestination)
    if (lowerLeft.row == -1 && lowerLeft.column == -1) {
        return findElement(target, upperRightOrigin, upperRightDest)
    }
    return lowerLeft
}

fun Matrix.findElement(target: Int): Coordinate =
        findElement(target, Coordinate(0, 0), Coordinate(size - 1, this[0].size - 1))


fun main(args: Array<String>) {
    val matrix = arrayOf(
            intArrayOf(1, 2, 3, 5, 6, 7, 8, 9),
            intArrayOf(2, 3, 7, 14, 15, 20, 27, 38),
            intArrayOf(3, 4, 10, 16, 25, 26, 39, 41),
            intArrayOf(8, 9, 12, 18, 32, 33, 56, 58),
            intArrayOf(9, 12, 13, 24, 35, 46, 57, 68)
    )
    println("started")
    println(matrix.findElement(59))
    println("finished")
}