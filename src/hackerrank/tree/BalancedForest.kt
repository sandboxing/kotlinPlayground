package hackerrank.tree

/**
 * https://www.hackerrank.com/challenges/balanced-forest
 */

import java.util.*
import kotlin.collections.ArrayList

class BalancedForest {
    data class Node(
            val coin: Int,
            val children: MutableList<Node> = mutableListOf(),
            var parent: Node? = null
    ) {
        fun totalCoin(): Long = coin.toLong() + children.map { it.totalCoin() }.sum()
    }
}

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val q = scanner.nextInt()
    (0 until q).forEach {
        val n = scanner.nextInt()
        val nodes = ArrayList<BalancedForest.Node>(n)
        (0 until n).forEach {
            nodes.add(BalancedForest.Node(scanner.nextInt()))
        }
        (0 until n - 1).forEach {
            val x = scanner.nextInt() - 1
            val y = scanner.nextInt() - 1
            nodes[x].children.add(nodes[y])
            nodes[y].parent = nodes[x]
        }
        println(nodes[0].totalCoin())

        splitNodes(nodes[0])
    }
    scanner.close()
}

fun splitNodes(node: BalancedForest.Node) {
    val topTotal = node.totalCoin()
    node.children.forEach { child ->
        if (child.totalCoin() < topTotal / 2) {
            // try to split parent with left child
        } else {
            // try to split child
        }
    }
}
