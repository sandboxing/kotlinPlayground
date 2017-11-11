package hackerrank.array

import java.util.*

/**
 * https://www.hackerrank.com/challenges/array-left-rotation
 */

fun leftRotation(array: IntArray, rotationCount: Int): IntArray {
    val tmp = array.slice(0 until rotationCount).toIntArray()
    System.arraycopy(array, rotationCount, array, 0, array.size - rotationCount)
    System.arraycopy(tmp, 0, array, array.size - rotationCount, tmp.size)
    return array
}

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val d = scanner.nextInt()
    val a = IntArray(n)
    for (a_i in 0 until n) {
        a[a_i] = scanner.nextInt()
    }
    val result = leftRotation(a, d)
    for (i in result.indices) {
        print(result[i].toString() + if (i != result.size - 1) " " else "")
    }
    println("")


    scanner.close()
}