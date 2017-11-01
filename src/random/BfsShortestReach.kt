package random

import java.util.*
import kotlin.collections.ArrayList

/**
 * https://www.hackerrank.com/challenges/ctci-bfs-shortest-reach
 */
data class Noda(val id: Int) {
    var neighbors: MutableList<Noda> = mutableListOf()
    var visited = false
    var depth = 0

    fun addEdge(node: Noda) {
        neighbors.add(node)
    }
}

private data class Graph(val size: Int) {
    var nodes = ArrayList<Noda>(size)

    init {
        (0 until size).forEach { i ->
            nodes.add(Noda(i))
        }
    }

    fun addEdge(first: Int, second: Int) {
        val firstNoda = nodes[first]
        val secondNoda = nodes[second]
        firstNoda.addEdge(secondNoda)
        secondNoda.addEdge(firstNoda)
    }

    fun shortestReach(startId: Int): IntArray { // 0 indexed
        val distances = IntArray(size) { -1 }

        val startNoda = nodes[startId]

        val queue: LinkedList<Noda> = LinkedList()
        startNoda.visited = true
        queue.add(startNoda)

        while (queue.isNotEmpty()) {
            val node = queue.pop()
            distances[node.id] = node.depth * 6

            node.neighbors.forEach { neighbor ->
                if (!neighbor.visited) {
                    val newDepth = node.depth + 1
                    neighbor.visited = true
                    neighbor.depth = newDepth
                    queue.add(neighbor)
                }
            }
        }

        return distances
    }
}


fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val queries = scanner.nextInt()

    for (t in 0 until queries) {

        // Create a graph of size n where each edge weight is 6:
        val graph = Graph(scanner.nextInt())
        val m = scanner.nextInt()

        // read and set edges
        for (i in 0 until m) {
            val u = scanner.nextInt() - 1
            val v = scanner.nextInt() - 1

            // add each edge to the graph
            graph.addEdge(u, v)
        }

        // Find shortest reach from node s
        val startId = scanner.nextInt() - 1
        val distances = graph.shortestReach(startId)

        for (i in distances.indices) {
            if (i != startId) {
                print(distances[i])
                print(" ")
            }
        }
        println()
    }

    scanner.close()
}