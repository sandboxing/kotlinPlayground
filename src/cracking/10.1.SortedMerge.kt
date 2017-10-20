package cracking

/**
 * You are given two sorted arrays, A and B, and A has a large enough buffer at the end to hold B.
 * Write a method to merge B into A in sorted order.
 */

fun mergeAintoB(a: IntArray, b: IntArray) {
    val aSize = a.size
    val bSize = b.size

    for (indexB in 0 until bSize) {
        var indexA = aSize - 1 - bSize + indexB
        while (indexA >= 0 && a[indexA] > b[indexB]) {
            a[indexA + 1] = a[indexA]

            indexA--
        }
        a[indexA + 1] = b[indexB]
    }
}

fun main(args: Array<String>) {
//    val a = intArrayOf(1, 5, 0)
//    val b = intArrayOf(2)
    val a = intArrayOf(1, 5, 7, 11, 12, 13, 15, 20, 0, 0, 0, 0, 0, 0, 0)
    val b = intArrayOf(2, 3, 5, 10, 11, 12, 23)
    mergeAintoB(a, b)
    println(a.contentToString()) // should be [1, 2, 3, 5, 5, 7, 10, 11, 11, 12, 12, 13, 15, 20]
}