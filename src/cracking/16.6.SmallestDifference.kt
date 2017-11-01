package cracking

/**
 * Given two arrays of integers, compute the pair of values with the smallest (non-negative) difference.
 * Return the difference.
 */
fun smallestDiff(a: IntArray, b: IntArray): Int {
    return a.flatMap { i ->
        b.map { j ->
            Math.abs(i - j)
        }
    }.min() ?: -1
}

fun main(args: Array<String>) {
    val a = intArrayOf(1, 3, 15, 11, 2)
    val b = intArrayOf(23, 127, 235, 19, 8)

    println(smallestDiff(a, b))
}

// AB, there should be a A + B