package cracking

/**
 * Given a boolean expression consisting of the symbols (0, 1, &, |, and ^) and
 * a desired boolean expected value expected, implement a function to count the number of ways of parenthesizing the
 * expression such that it evaluates to expected.
 */

fun stringToBool(s: String) = s == "1"

fun countEval(expression: String, expected: Boolean): Int {
    if (expression.isEmpty()) return 0
    if (expression.length == 1) return if (stringToBool(expression) == expected) 1 else 0

    var index = 1
    var ways = 0
    while (index < expression.length) {
        val charAtI = expression[index]
        val left = expression.substring(0, index)
        val right = expression.substring(index + 1)

        val leftFalse = countEval(left, false)
        val leftTrue = countEval(left, true)
        val rightFalse = countEval(right, false)
        val rightTrue = countEval(right, true)
        val total = (leftFalse + leftTrue) * (rightFalse + rightTrue)

        val totalTrue = when (charAtI) {
            '&' -> leftTrue * rightTrue
            '|' -> leftTrue * rightFalse + leftFalse * rightTrue + leftTrue * rightTrue
            '^' -> leftTrue * rightFalse + leftFalse * rightTrue
            else -> throw IllegalStateException("$expression and $expected")
        }

        ways += if (expected) totalTrue else total - totalTrue
        index += 2
    }
    return ways
}

fun main(args: Array<String>) {
    println("Should be two: " + countEval("1^0|0|1", false))
    println("Should be ten: " + countEval("0&0&0&1^1|0", true))
}