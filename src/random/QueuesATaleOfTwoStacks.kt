package random

import java.io.*
import java.util.*

/**
 * https://www.hackerrank.com/challenges/ctci-queue-using-two-stacks
 */
class Queue(var head: Node? = null) {
    var last: Node? = null

    fun add(node: Node) {
        if (head == null) {
            head = node
            last = head
            return
        }

        last!!.next = node
        last = node
    }

    fun pop(): Node {
        head.let { h ->
            if (h == null) throw RuntimeException("yo")

            val popped = h
            head = h.next
            return popped
        }
    }

    fun peek(): Node {
        head.let {h ->
            if (h == null) throw RuntimeException("yo")
            return h
        }
    }
}

data class Node(val data: Int, var next: Node? = null)

fun input(): Pair<Int, Int?> = readLine()!!.split(' ').let {
    if (it.size == 1) return Pair(it[0].toInt(), 0)
    return Pair(it[0].toInt(), it[1].toInt())
}

fun main(args: Array<String>) {
    val queue = Queue(null)

    val inputCount: Int = readLine()!!.toInt()
    (0 until inputCount).forEach {
        val (cmd, value) = input()
        when (cmd) {
            1 -> queue.add(Node(value!!))
            2 -> queue.pop()
            3 -> println(queue.peek().data)
        }
    }
}