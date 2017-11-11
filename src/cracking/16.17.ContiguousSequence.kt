package cracking

fun IntArray.largestSum(): Int {
    if (isEmpty()) return 0

    var sumMax = this[0]
    var sum = sumMax
    (1 until size).forEach { i ->
        val v = this[i]

        sum += v
        if (sum > sumMax) sumMax = sum
        if (sum < 0) sum = 0
        if (sumMax < v) sumMax = v
    }
    return sumMax
}

fun main(args: Array<String>) {
    println(intArrayOf(2, -8, 3, -2, 4, -10).largestSum())
    println(intArrayOf(-3, -4, -1, -13).largestSum())
    println(intArrayOf(2, -8, 3, -2, 4, -10, 32).largestSum())
    println(intArrayOf(2, -1, 3, -2, 4, -10, 5).largestSum())
    println(intArrayOf(2, -1, 3, -2, 4, -4, 5).largestSum())
}