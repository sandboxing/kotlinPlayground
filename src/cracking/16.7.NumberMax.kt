package cracking

/**
 * Write a method that finds the maximum of two numbers.
 * You should not use if-else or any other comparison operator
 */
fun numberMax(a: Int, b: Int): Int {
    // return sortedSetOf(a, b).last() // funny guy, +5 points

    // if b > a then k == 1
    val k: Int = (a - b) shr 31 and 0x1

    return a * (k xor 1) + b * k
}

fun main(args: Array<String>) {
    println(numberMax(1, 3))
    println(numberMax(1, -3))
    println(numberMax(-1, -3))
    println(numberMax(0, 0))
    println(numberMax(1000, -1000))
}