package hackerrank.tree

import java.util.*

/**
 * https://www.hackerrank.com/challenges/array-pairs
 */

fun main(args: Array<String>) {

    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()

    val a = LongArray(n)
    for (i in 0 until n) {
        a[i] = scanner.nextInt().toLong()
    }
    scanner.close()

    println(countPair(a))
}

fun countPair(a: LongArray): Long {
    var count = 0L

    var localMax: Long
    var ai: Long
    for (i in 0 until a.size - 1) {
        localMax = 0L
        ai = a[i]
        for (j in i + 1 until a.size) {
            a[j].let { aj ->
                if (localMax < aj) localMax = aj
                if (ai * aj <= localMax) count++
            }
        }
    }

    return count
}
