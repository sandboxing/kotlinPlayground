package hackerrank.array

import java.util.*


/**
 * https://www.hackerrank.com/challenges/crush
 */

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val arraySize = scanner.nextInt()
    val delta = LongArray(arraySize + 1)
    val m = scanner.nextInt()
    var max = 0L
    (0 until m).forEach {
        val a = scanner.nextInt() - 1
        val b = scanner.nextInt() - 1
        val k = scanner.nextInt()
        delta[a] += k.toLong()
        delta[b + 1] -= k.toLong()
    }
    var current = 0L
    delta.forEach { d ->
        current += d
        if (max < current) max = current
    }
    println(max)
    scanner.close()
}