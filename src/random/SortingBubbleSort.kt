package random

import java.util.*

/**
 * https://www.hackerrank.com/challenges/ctci-bubble-sort/problem
 */
fun bubbleSort(a: IntArray, n: Int): Int {
    // Track number of elements swapped during a single array traversal
    var numberOfSwaps = 0
    for (i in 0 until n) {

        for (j in 0 until n - 1) {
            // Swap adjacent elements if they are in decreasing order
            if (a[j] > a[j + 1]) {
                swap(a, j, j + 1)
                numberOfSwaps++
            }
        }

        // If no elements were swapped during a traversal, array is sorted
        if (numberOfSwaps == 0) {
            break
        }
    }
    return numberOfSwaps
}

private fun swap(array: IntArray, i: Int, j: Int) {
    array[i] = array[i] - array[j]
    array[j] = array[j] + array[i]
    array[i] = array[j] - array[i]
}

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val a = IntArray(n)
    for (a_i in 0 until n) {
        a[a_i] = scanner.nextInt()
    }
    val swaps = bubbleSort(a, n)
    println("Array is sorted in $swaps swaps.")
    println("First Element: ${a.first()}")
    println("Last Element: ${a.last()}")
}