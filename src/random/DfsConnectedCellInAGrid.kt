package random

/**
 * https://www.hackerrank.com/challenges/ctci-connected-cell-in-a-grid
 */

import java.util.*

typealias Matrix = Array<IntArray>
val Matrix.width
    get() = this.size
val Matrix.height
    get() = this[0].size

val regionCount = IntArray(100)
val visited = mutableSetOf<Pair<Int, Int>>()
var globalIndex = 0

fun getBiggestRegion(matrix: Matrix): Int {
    if (matrix.width == 0 || matrix.height == 0) return 0

    (0 until matrix.width).forEach i@ { i ->
        (0 until matrix.height).forEach j@ { j ->
            if (matrix[i][j] == 1) {
                if (visited.contains(Pair(i, j))) return@j
                countGraph(matrix, i, j, globalIndex++)
            }
        }
    }

    return regionCount.max() ?: 0
}

fun countGraph(matrix: Matrix, i: Int, j: Int, graphIndex: Int) {
    if (i < 0 || j < 0 ||
            i >= matrix.width || j >= matrix.height) return

    if (matrix[i][j] == 0) return

    Pair(i, j).let {
        if (visited.contains(it)) return
        visited.add(it)
    }

    regionCount[graphIndex]++

    countGraph(matrix, i, j + 1, graphIndex)
    countGraph(matrix, i + 1, j, graphIndex)
    countGraph(matrix, i + 1, j + 1, graphIndex)
    countGraph(matrix, i - 1, j, graphIndex)
    countGraph(matrix, i, j - 1, graphIndex)
    countGraph(matrix, i - 1, j - 1, graphIndex)
    countGraph(matrix, i - 1, j + 1, graphIndex)
    countGraph(matrix, i + 1, j - 1, graphIndex)
}

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val m = scanner.nextInt()
    val matrix = Array(n) { IntArray(m) }
    for (grid_i in 0 until n) {
        for (grid_j in 0 until m) {
            matrix[grid_i][grid_j] = scanner.nextInt()
        }
    }
    println(getBiggestRegion(matrix))
}