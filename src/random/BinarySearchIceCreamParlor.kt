package random

import java.util.*


/**
 * Binary Search: Ice Cream Parlorhttps://www.hackerrank.com/challenges/ctci-ice-cream-parlor
 */

data class IceCream(val price: Int, val index: Int) : Comparable<IceCream> {
    override fun compareTo(other: IceCream): Int = price.compareTo(other.price)
}

fun binarySearch(first: Int, last: Int, arr: Array<IceCream?>, search: Int): Int {
    if (last < first) return -1
    if (first == last) {
        return arr[first]?.let { ice ->
            if (ice.price == search) ice.index else -1
        } ?: -1
    }
    val middle = (last + first) / 2
    var result = binarySearch(first, middle, arr, search)
    if (result == -1) {
        result = binarySearch(middle + 1, last, arr, search)
    }
    return result
}

fun main(args: Array<String>) {
    val t: Int
    var iceCount: Int
    var money: Int

    val scanner = Scanner(System.`in`)
    t = scanner.nextInt()
    for (test in 0 until t) {

        money = scanner.nextInt()
        iceCount = scanner.nextInt()
        val arr = arrayOfNulls<IceCream>(iceCount)

        for (i in 0 until iceCount)
            arr[i] = IceCream(scanner.nextInt(), i + 1)

        Arrays.sort(arr)
        for (i in 0 until iceCount - 1) {
            val search = money - arr[i]!!.price
            if (search >= arr[i]!!.price) {
                val index = binarySearch(i + 1, iceCount - 1, arr, search)
                if (index != -1) {
                    println(Math.min(arr[i]!!.index, index).toString()
                            + " "
                            + Math.max(arr[i]!!.index, index))
                    break
                }
            }
        }
    }
}