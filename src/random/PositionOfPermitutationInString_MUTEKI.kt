package random

/**
 * position of all permutations of string [short] in string
 */
fun String.positionsOfPermutationsOfGod(short: String): List<Int> {
    val allSlots: List<ArrayList<Char>> =
            short.toList().let { shortCharArray ->
                (1..(this.length - 1)).map { ArrayList(shortCharArray) }
            }
    this.forEachIndexed { index, c ->
        if (!short.contains(c)) return@forEachIndexed
        allSlots[index].let { it.removeAt(it.indexOf(c)) }
    }
    return allSlots.asSequence()
            .filter { it.isEmpty() }
            .mapIndexed { index, _ -> index }
            .toList()
}

fun main(args: Array<String>) {
    println("cbabadcbbabbcbabaabccbabc".positionsOfPermutationsOfGod("abbc"))
}

// T (is it TS because I'm copying the string over? does not affect the result) + T S + T
// T (S + 2)
// T S
// not good enough. O(T) exists... ???