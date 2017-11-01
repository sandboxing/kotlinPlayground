package random

import java.util.*


/**
 * https://www.hackerrank.com/challenges/ctci-merge-sort
 */

var inversions = 0L

fun countInversions(arr: IntArray): Long {
    inversions = 0
    mergeSort(arr)
    return inversions
}

private fun merge(array: IntArray, leftStart: Int, rightEnd: Int) {
    val size = rightEnd - leftStart + 1
    val tmp = IntArray(size)
    var overallIndex = 0
    var leftIndex = leftStart
    val leftEnd = (leftStart + rightEnd) / 2
    val rightStart = leftEnd + 1
    var rightIndex = rightStart
    while (leftIndex <= leftEnd && rightIndex <= rightEnd) {
        if (array[leftIndex] <= array[rightIndex]) {
            tmp[overallIndex] = array[leftIndex]
            leftIndex++
        } else {
            tmp[overallIndex] = array[rightIndex]
            inversions += rightIndex - (leftStart + overallIndex)
            rightIndex++
        }
        overallIndex++
    }
    System.arraycopy(array, leftIndex, tmp, overallIndex, leftEnd - leftIndex + 1)
    System.arraycopy(array, rightIndex, tmp, overallIndex, rightEnd - rightIndex + 1)
    System.arraycopy(tmp, 0, array, leftStart, size)
}

private fun mergeSort(array: IntArray, left: Int, right: Int) {
    if (left >= right) return

    val middle = (left + right) / 2
    mergeSort(array, left, middle)
    mergeSort(array, middle + 1, right)
    merge(array, left, right)
}

private fun mergeSort(array: IntArray) {
    mergeSort(array, 0, array.size - 1)
}

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val t = scanner.nextInt()
    (0 until t).forEach {
        val n = scanner.nextInt()
        val arr = IntArray(n)
        for (arr_i in 0 until n) {
            arr[arr_i] = scanner.nextInt()
        }
        println(countInversions(arr))
    }
    scanner.close()
}