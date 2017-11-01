package random

import java.util.*

/**
 * https://www.hackerrank.com/challenges/ctci-recursive-staircase
 */

fun pathCount(top: Int, memo: IntArray): Int {
    if (top < 0) return 0
    if (top == 0) return 1

    if (memo[top] == 0)
        memo[top] = pathCount(top - 1, memo) + pathCount(top - 2, memo) + pathCount(top - 3, memo)

    return memo[top]
}

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val s = scanner.nextInt()
    (0 until s).forEach {
        val n = scanner.nextInt()
        val memo = IntArray(n + 1)
        println(pathCount(n, memo))
    }
}