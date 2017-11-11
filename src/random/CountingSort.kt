package random

fun IntArray.countingSort(): IntArray {
    val counts = IntArray(10)
    this.forEach { v ->
        counts[v]++
    }
    (1 until counts.size).forEach { i ->
        counts[i] += counts[i - 1]
    }
    val result = IntArray(this.size)
    this.forEach { v ->
        result[--counts[v]] = v
    }
    return result
}

fun main(args: Array<String>) {
    println(intArrayOf(
            3, 6, 4, 1, 9, 8, 5, 0, 3, 2, 9
    ).countingSort().contentToString())
}