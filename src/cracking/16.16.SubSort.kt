package cracking

/**
 * Given an array of integers, write a method to find indices m and n such that
 * if you sorted elements m through n, the entire array would be sorted.
 * Minimize n - m (that is , find the smallest such sequence)
 */
fun IntArray.subSort(): Pair<Int, Int> {
    var left = 0
    var right = size - 1
    while (left < right &&
            this[left] < this[left + 1]) left++
    while (right > left &&
            this[right] > this[right - 1]) right--

    if (left >= right) return Pair(-1, -1)

    var min = Int.MAX_VALUE
    var max = Int.MIN_VALUE
    (left..right).forEach { i ->
        this[i].let { v ->
            if (min > v) min = v
            if (max < v) max = v
        }
    }
    while (left >= 0 && this[left] > min) left--
    while (right < size && this[right] < max) right++

    return Pair(left + 1, right - 1)
}

fun main(args: Array<String>) {
    println(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8).subSort())
    println(intArrayOf(1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19).subSort())
    println(intArrayOf(1).subSort())
    println(intArrayOf().subSort())
    println(intArrayOf(6, 5, 4, 3, 2, 1).subSort())
}