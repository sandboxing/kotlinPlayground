package random

import java.util.*


abstract class Party {
    override fun toString(): String = this::class.simpleName ?: "class.simpleName empty"
}

object Pig : Party()
object Fox : Party()
object Rice : Party()

enum class Shore {
    ORIGIN, DESTINATION
}

data class Move(
        val from: Shore,
        val to: Shore,
        val party: Party?
)

fun List<Party>.isFull() = this.size == 3 && contains(Pig) && contains(Fox) && contains(Rice)

var moves: List<Move> = emptyList()

data class State(
        val farmerLocation: Shore,
        val partiesOnOrigin: List<Party>,
        val partiesOnDestination: List<Party>
)

fun State.isComplete() =
        farmerLocation == Shore.DESTINATION &&
                partiesOnOrigin.isEmpty() &&
                partiesOnDestination.isFull()

fun State.isValid(): Boolean {
    when (farmerLocation) {
        Shore.DESTINATION -> {
            if (partiesOnOrigin.contains(Pig) && partiesOnOrigin.contains(Rice) ||
                    partiesOnOrigin.contains(Fox) && partiesOnOrigin.contains(Pig)) {
                return false
            }
        }
        Shore.ORIGIN -> {
            if (partiesOnDestination.contains(Pig) && partiesOnDestination.contains(Rice) ||
                    partiesOnDestination.contains(Fox) && partiesOnDestination.contains(Pig)) {
                return false
            }
        }
    }
    return true
}

fun State.applyMove(move: Move): State {
    moves += move
    return this.copy(
            farmerLocation = move.to,
            partiesOnOrigin = if (move.party != null) {
                if (move.from == Shore.ORIGIN) {
                    partiesOnOrigin - move.party
                } else {
                    partiesOnOrigin + move.party
                }
            } else partiesOnOrigin,
            partiesOnDestination = if (move.party != null) {
                if (move.from == Shore.ORIGIN) {
                    partiesOnDestination + move.party
                } else {
                    partiesOnDestination - move.party
                }
            } else partiesOnDestination)
}


val moveHistory = mutableListOf<State>()
val correctMoves = Stack<Move>()

fun willFindWay(state: State): Boolean {
    if (moveHistory.contains(state)) {
        return false
    } else {
        moveHistory.add(state)
    }
    if (!state.isValid()) {
        return false
    }
    if (state.isComplete()) {
        return true
    }

    when (state.farmerLocation) {
        Shore.DESTINATION -> {
            state.partiesOnDestination.forEach { party ->
                val move = Move(Shore.DESTINATION, Shore.ORIGIN, party)
                val newState = state.applyMove(move)
                val result = willFindWay(newState)
                if (result) {
                    correctMoves.push(move)
                    return true
                }
            }
            val move = Move(Shore.DESTINATION, Shore.ORIGIN, null)
            val newState = state.applyMove(move)
            val result = willFindWay(newState)
            if (result) {
                correctMoves.push(move)
                return true
            }
        }
        Shore.ORIGIN -> {
            state.partiesOnOrigin.forEach { party ->
                val move = Move(Shore.ORIGIN, Shore.DESTINATION, party)
                val newState = state.applyMove(move)
                val result = willFindWay(newState)
                if (result) {
                    correctMoves.push(move)
                    return true
                }
            }
            val move = Move(Shore.DESTINATION, Shore.ORIGIN, null)
            val newState = state.applyMove(move)
            val result = willFindWay(newState)
            if (result) {
                correctMoves.push(move)
                return true
            }
        }
    }
    return false
}

fun main(args: Array<String>) {
    willFindWay(
            State(farmerLocation = Shore.ORIGIN,
                    partiesOnOrigin = listOf(Fox, Pig, Rice),
                    partiesOnDestination = emptyList()))
    println(correctMoves)
}