package cracking

/**
 * Given an M * N matrix in which each row and each column is sorted in ascending order,
 * write a method to cracking.find an element.
 */

typealias Column = Int
typealias Row = Int

fun IntArray.indexOf_mLogn(target: Int): Int {
    if (size == 0) return -1
    if (size == 1) return if (get(0) == target) 0 else -1

    val middleIndex = size / 2
    val middleValue = get(middleIndex)
    return if (target == middleValue) {
        middleIndex
    } else if (target < middleValue) {
        this.sliceArray(0 until middleIndex).indexOf_mLogn(target)
    } else {
        val targetIndex = this.sliceArray(middleIndex + 1 until size).indexOf_mLogn(target)
        if (targetIndex == -1) -1 else middleIndex + 1 + targetIndex
    }
}

fun Matrix.coordinateOf(target: Int): Pair<Column, Row> {
    if (this.isEmpty() || this[0].isEmpty()) return Pair(-1, -1)

    var col = size - 1
    var row = 0
    while (col >= 0 && row < this[0].size) {
        when {
            this[col][row] < target -> row++
            this[col][row] > target -> col--
            else -> return Pair(col, row)
        }
    }
    return Pair(-1, -1)
}

fun Matrix.find(target: Int): Pair<Column, Row> {
    forEachIndexed { columnIndex, ints ->
        val rowIndex = ints.indexOf_mLogn(target)
        if (rowIndex > -1) return Pair(columnIndex, rowIndex)
    }
    throw RuntimeException("No contain")
}

fun main(args: Array<String>) {
    println(1 / 2)
    val matrix = arrayOf(
            intArrayOf(1, 2, 3, 5, 6, 7, 8, 9),
            intArrayOf(2, 3, 7, 14, 15, 20, 27, 38),
            intArrayOf(3, 4, 10, 16, 25, 26, 39, 41),
            intArrayOf(8, 9, 12, 18, 32, 33, 56, 58),
            intArrayOf(9, 12, 13, 24, 35, 46, 57, 68)
    )
    println(matrix.find(57))
    println(matrix.coordinateOf(57))
}