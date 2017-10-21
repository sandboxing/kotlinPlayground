package cracking

/**
 * Write an algorithm which computes the number of trailing zeros in [n] factorial
 */
fun countTrailingZerosFor(n: Int): Int {
    if (n < 9) return 0

    var count = 0
    var bottom = 5
    while (n / bottom > 0) {
        count += n / bottom
        bottom *= 5
    }
    return count
}

fun main(args: Array<String>) {
    println(countTrailingZerosFor(26))
}