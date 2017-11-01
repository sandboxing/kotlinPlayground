package random

import java.util.*

/**
 * https://www.hackerrank.com/challenges/ctci-contacts
 */
class Tries : CharNode() {
    fun add(word: String) {
        var currentNode = this as CharNode
        word.forEach { char ->
            currentNode = currentNode.children.getOrPut(char) { CharNode() }.also { it.wordsCountCache++ }
        }
        currentNode.isCompleteWord = true
    }

    fun tail(word: String): CharNode? {
        var currentNode = this as CharNode
        word.forEach { char ->
            if (!currentNode.children.containsKey(char)) return null
            currentNode = currentNode.children.get(char)!!
        }
        return currentNode
    }

    fun count(partial: String): Int {
        return tail(partial)?.wordsCount() ?: 0
    }

    override fun toString(): String {
        return "Tries:\n$children"
    }
}

open class CharNode {
    var children = hashMapOf<Char, CharNode>()
    var isCompleteWord: Boolean = false
    var wordsCountCache = 0

    fun wordsCount(): Int = wordsCountCache

    override fun toString(): String {
        return "CharNode: $isCompleteWord\n$children"
    }
}


fun main(args: Array<String>) {
    val tries = Tries()
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()

    for (i in 0 until n) {
        val cmd = scanner.next()
        val word = scanner.next()
        when (cmd) {
            "add" -> tries.add(word)
            "find" -> println(tries.count(word))
        }
    }
}