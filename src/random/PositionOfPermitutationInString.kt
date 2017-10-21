package random

import cracking.sort

/**
 * position of all permutations of string [short] in string
 */
fun String.positionsOfPermutationsOf(short: String): List<Int> {
    val sortedShort = short.sort()
    return this.asSequence()
            .mapIndexed { index, c ->
                when {
                    index > this.length - short.length -> -1
                    this.substring(index, index + short.length).sort() == sortedShort -> index
                    else -> -1
                }
            }.filter { it != -1 }
            .toList()
}


fun main(args: Array<String>) {
    println("cbabadcbbabbcbabaabccbabc".positionsOfPermutationsOf("abbc"))
}

// S log S + T S log S + T
// => (1 + T) S log S + T
// => T S log S
// not good enough. O(T) exists.