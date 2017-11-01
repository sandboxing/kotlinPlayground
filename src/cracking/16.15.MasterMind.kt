package cracking

/**
 * The Game of Master Mind is played as follows.
 * The computer has four slots, and each slot will contain a ball that is red (R), yellow (Y), green (G) or blue (B).
 * For example, the computer might have RGGB (Slot #1 is red, Slots #2 and #3 are green, Slot #4 is blue).
 *
 * You, the user, are trying to guess the solution. You might, for example, guess YRGB.
 *
 * When you guess the correct color for the correct slot, you get a "hit".
 * If you guess a color that exists but is in the wrong slot, you get a "pseudo-hit".
 * Note that a slot that is a hit can never count as a pseudo-hit.
 *
 * For example, if the actual solution is RGBY and you guess GGRR, you have one hit and one pseudo-hit.
 *
 * Write a method that, given a guess and a solution, returns the number of hits and pseudo-hits.
 */
fun Solution.process(guess: Guess): Pair<Int, Int> {
    val solutionValues = hashMapOf<MMSlot, Int>()
    val guessValues = hashMapOf<MMSlot, Int>()
    var hit = 0
    (0..(size - 1)).forEach { index ->
        if (this[index] == guess[index]) {
            hit++
        } else {
            this[index].let { soluceSlot ->
                solutionValues.put(soluceSlot, solutionValues.getOrDefault(soluceSlot, 0) + 1)
            }
            guess[index].let { guessSlot ->
                guessValues.put(guessSlot, solutionValues.getOrDefault(guessSlot, 0) + 1)
            }
        }
    }
    var pseudoHit = 0
    guessValues.forEach { slot, count ->
        pseudoHit += Math.min(count, solutionValues[slot] ?: 0)
    }

    return Pair(hit, pseudoHit)
}

typealias Solution = Array<MMSlot>
typealias Guess = Array<MMSlot>

sealed class MMSlot {
    object red : MMSlot()
    object yellow : MMSlot()
    object green : MMSlot()
    object blue : MMSlot()
}

fun main(args: Array<String>) {
    println(arrayOf(MMSlot.red, MMSlot.green, MMSlot.blue, MMSlot.yellow)
            .process(arrayOf(MMSlot.green, MMSlot.green, MMSlot.red, MMSlot.red)))
    println(arrayOf(MMSlot.red, MMSlot.green, MMSlot.blue, MMSlot.yellow)
            .process(arrayOf(MMSlot.red, MMSlot.green, MMSlot.red, MMSlot.yellow)))
    println(arrayOf(MMSlot.red, MMSlot.green, MMSlot.blue, MMSlot.yellow)
            .process(arrayOf(MMSlot.green, MMSlot.red, MMSlot.yellow, MMSlot.blue)))
    println(arrayOf(MMSlot.red, MMSlot.green, MMSlot.green, MMSlot.green)
            .process(arrayOf(MMSlot.green, MMSlot.red, MMSlot.red, MMSlot.red)))
    println(arrayOf(MMSlot.red, MMSlot.green, MMSlot.green, MMSlot.red)
            .process(arrayOf(MMSlot.red, MMSlot.red, MMSlot.green, MMSlot.green)))
}