package cracking

/**
 * Write methods to implement the multiply, subtract, and divide operations for integers.
 * The results of all of theses are integers. Use only the add operator.
 */
fun Int.multiply(b: Int): Int {
    val other = if (b > 0) b else b.negate()
    val result = (0..other).reduce { acc, i ->
        acc + this
    }
    return if (b < 0) result.negate() else result
}

fun Int.subtract(b: Int): Int {
    return this + b.negate()
}

// this / b = r => r * b = this => add b n times before exceeding this and return n
fun Int.divide(b: Int): Int {
    return 1
}

fun Int.negate(): Int {
    return this.inv() + 1 // ~x = -x - 1
}

fun main(args: Array<String>) {
    println(1.negate())
    println(12.negate())
    println((-12).negate())
    println(12.subtract(3))
    println((-12).subtract(3))
    println((-12).subtract(-3))
    println(3.multiply(4))
    println(3.multiply(-4))
    println((-2).multiply(3))
    println((-2).multiply(-3))
}