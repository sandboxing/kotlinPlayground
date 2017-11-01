package random

/**
 * TODO(benoit) broken
 * position of all permutations of string [short] in string
 */
fun String.positionsOfPermutationsOfGod(short: String): List<Int> {
    val thisLength = this.length
    val shortLength = short.length
    val allSlots: List<ArrayList<Char>> =
            short.toList().let { shortCharArray ->
                (0..(thisLength - shortLength)).map { ArrayList(shortCharArray) }
            }
    this.forEachIndexed { index, c ->
        if (!short.contains(c)) return@forEachIndexed
        val start = Math.max(0, index - shortLength + 1)
        val end = Math.min(index, thisLength - shortLength)
        (start..end).forEach {
            allSlots[it].let { slots ->
                slots.indexOf(c).let {
                    if (it > -1) slots.removeAt(it)
                }
            }
        }
    }
    return allSlots.asSequence()
            .mapIndexed { index, list -> Pair(index, list) }
            .filter { it.second.isEmpty() }
            .map { it.first }
            .toList()
}

fun main(args: Array<String>) {
    println("cbabadcbbabbcbabaabccbabc".positionsOfPermutationsOfGod("abbc"))
//    println("cbab".positionsOfPermutationsOfGod("abbc"))
}

// (T - S) S + T (S + S (S)) + S
// ???