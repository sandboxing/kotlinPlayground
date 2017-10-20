package cracking

/** Buffer
 *
 *
 */

fun addParentheses(list: ArrayList<String>, str: CharArray, left: Int, right: Int, index: Int) {
    if (left < 0 || right < left) throw IllegalStateException("No going to happen left:$left right:$right")

    if (left == 0 && right == 0) {
        list.add(str.joinToString(""))
        return
    }

    if (left > 0) {
        str[index] = '('
        addParentheses(list, str, left - 1, right, index + 1)
    }

    if (left < right) {
        str[index] = ')'
        addParentheses(list, str, left, right - 1, index + 1)
    }
}

fun printAllPairs(n: Int) {
    val str = CharArray(2 * n)
    val list: ArrayList<String> = ArrayList()
    addParentheses(list, str, n, n, 0)
    list.forEach { println(it) }
}

fun main(args: Array<String>) {
    printAllPairs(5)
}