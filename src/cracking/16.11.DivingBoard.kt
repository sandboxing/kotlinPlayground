package cracking

/**
 * You are building a diving board by placing a bunch of planks of wood end-to-end.
 * There are two types of planks, one of length [shorter], and one of length [longer].
 * You must use exactly [k] planks of wood.
 * Write a method to generate all possible lengths for the diving board.
 */
fun generateLengthsForDivingBoard(k: Int, shorter: Int, longer: Int): Set<Int> {
    if (k < 1) return setOf(0)

    return (0..k).map { i ->
        i * shorter + (k - i) * longer
    }.toSet()
}

fun main(args: Array<String>) {
    println(generateLengthsForDivingBoard(1, 3, 7)) // 2
    println(generateLengthsForDivingBoard(2, 3, 7)) // 3
    println(generateLengthsForDivingBoard(3, 3, 7)) // 4
    println(generateLengthsForDivingBoard(4, 3, 7)) // 5
    println(generateLengthsForDivingBoard(6, 3, 7)) // 7
    println(generateLengthsForDivingBoard(10, 3, 7)) // 11
}