package hackerrank.tree

/**
 * https://www.hackerrank.com/challenges/swap-nodes-algo
 */

import java.util.*

class SwapNodes {
    data class Node(
            private val id: Int
    ) {
        var depth: Int = -1
        var left: Node? = null
        var right: Node? = null

        fun swap(targetDepth: Int) {
            left?.swap(targetDepth)
            right?.swap(targetDepth)

            if (depth % targetDepth == 0) {
                val tmp = left
                left = right
                right = tmp
            }
        }

        fun inorder() {
            left?.inorder()
            print("$id ")
            right?.inorder()
        }
    }
}

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val nodes = Array(n) { i -> SwapNodes.Node(i + 1) }
    val root = nodes[0]
    root.depth = 1
    nodes.forEach { node ->
        val left = scanner.nextInt() - 1
        val right = scanner.nextInt() - 1
        if (left > 0) {
            node.left = nodes[left]
            node.left?.depth = node.depth + 1
        }
        if (right > 0) {
            node.right = nodes[right]
            node.right?.depth = node.depth + 1
        }
    }
    val kCount = scanner.nextInt()
    (0 until kCount).forEach {
        root.swap(scanner.nextInt())
        root.inorder()
        println()
    }
}