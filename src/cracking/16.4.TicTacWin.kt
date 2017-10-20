package cracking

/**
 * Design an algorithm to figure out if someone has won a game of tic-tac-toe
 */

enum class Slot {
    CROSS, CIRCLE, BLANK
}

typealias Board = Array<Array<Slot>>

fun Board.hasWon(): Boolean =
        arrayOf(Slot.CROSS, Slot.CIRCLE).any { slot ->
            (0..2).any { i ->
                this[i][0] == slot &&
                        this[i][0] == this[i][1] &&
                        this[i][0] == this[i][2] ||
                        this[0][i] == slot &&
                                this[0][i] == this[1][i] &&
                                this[0][i] == this[2][i]
            } ||
                    this[0][0] == slot &&
                            this[0][0] == this[1][1] &&
                            this[0][0] == this[2][2] ||
                    this[2][2] == slot &&
                            this[2][2] == this[1][1] &&
                            this[2][2] == this[0][0]
        }

fun main(args: Array<String>) {
    val board = arrayOf(
            arrayOf(Slot.CIRCLE, Slot.CROSS, Slot.CROSS),
            arrayOf(Slot.CIRCLE, Slot.BLANK, Slot.CROSS),
            arrayOf(Slot.CIRCLE, Slot.CROSS, Slot.BLANK)
    )

    println(board.hasWon())
}