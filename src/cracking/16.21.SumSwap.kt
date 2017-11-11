package cracking

/**
 * Given two arrays of integers,
 * find a pair of values (one value from each array) that you can swap to give the two arrays the same sum.
 */
fun sumSwap(a: IntArray, b: IntArray): Pair<Int, Int> {
    val sumA = a.sum()
    val sumB = b.sum()
    if (sumA == sumB) return Pair(-1, -1)

    a.toSet().forEach { v ->
        val potB: Int = (((sumB - sumA) / 2.toLong()) + v).toInt()
        if (b.contains(potB)) return Pair(v, potB)
    }
    return Pair(-1, -1)
}

fun main(args: Array<String>) {
    println(sumSwap(intArrayOf(4, 1, 2, 1, 1, 2), intArrayOf(3, 6, 3, 3)))
}