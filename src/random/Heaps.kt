package random

import java.util.*

/**
 * https://www.hackerrank.com/challenges/ctci-find-the-running-median
 */
class MinHeap(capacity: Int) {
    var size = 0
    val array = IntArray(capacity)

    private fun parentIndex(index: Int) = (index - 1) / 2
    private fun leftChildIndex(index: Int) = index * 2 + 1
    private fun rightChildIndex(index: Int) = index * 2 + 2
    private fun hasLeftChild(index: Int) = leftChildIndex(index) <= size - 1
    private fun hasRightChild(index: Int) = rightChildIndex(index) <= size - 1
    private fun leftChildValue(index: Int) = array[leftChildIndex(index)]
    private fun rightChildValue(index: Int) = array[rightChildIndex(index)]
    private fun hasParent(index: Int) = parentIndex(index) >= 0

    fun add(value: Int) {
        array[size] = value
        size++
        heapifyUp()
    }

    fun pop(): Int {
        val value = array[0]
        size--
        array[0] = array[size]
        heapifyDown()
        return value
    }

    private fun heapifyDown() {
        var index = 0
        while (hasLeftChild(index)) {
            val smallerChildIndex =
                    if (hasRightChild(index) && leftChildValue(index) > rightChildValue(index))
                        rightChildIndex(index)
                    else
                        leftChildIndex(index)

            if (array[index] < array[smallerChildIndex]) return

            swap(index, smallerChildIndex)
            index = smallerChildIndex
        }
    }

    private fun heapifyUp() {
        var index = size - 1
        while (hasParent(index) && array[index] < array[parentIndex(index)]) {
            swap(index, parentIndex(index))
            index = parentIndex(index)
        }
    }

    fun peek() = array[0]

    private fun swap(i: Int, j: Int) {
        val tmp = array[i]
        array[i] = array[j]
        array[j] = tmp
    }
}

class MaxHeap(capacity: Int) {
    var size = 0
    val array = IntArray(capacity)

    private fun parentIndex(index: Int) = (index - 1) / 2
    private fun leftChildIndex(index: Int) = index * 2 + 1
    private fun rightChildIndex(index: Int) = index * 2 + 2
    private fun hasLeftChild(index: Int) = leftChildIndex(index) <= size - 1
    private fun hasRightChild(index: Int) = rightChildIndex(index) <= size - 1
    private fun leftChildValue(index: Int) = array[leftChildIndex(index)]
    private fun rightChildValue(index: Int) = array[rightChildIndex(index)]
    private fun hasParent(index: Int) = parentIndex(index) >= 0

    fun add(value: Int) {
        array[size] = value
        size++
        heapifyUp()
    }

    fun pop(): Int {
        val value = array[0]
        size--
        array[0] = array[size]
        heapifyDown()
        return value
    }

    private fun heapifyDown() {
        var index = 0
        while (hasLeftChild(index)) {
            val biggerChildIndex =
                    if (hasRightChild(index) && leftChildValue(index) < rightChildValue(index))
                        rightChildIndex(index)
                    else
                        leftChildIndex(index)

            if (array[index] > array[biggerChildIndex]) return

            swap(index, biggerChildIndex)
            index = biggerChildIndex
        }
    }

    private fun heapifyUp() {
        var index = size - 1
        while (hasParent(index) && array[index] > array[parentIndex(index)]) {
            swap(index, parentIndex(index))
            index = parentIndex(index)
        }
    }

    fun peek() = array[0]

    private fun swap(i: Int, j: Int) {
        val tmp = array[i]
        array[i] = array[j]
        array[j] = tmp
    }
}

fun normalize(minHeap: MinHeap, maxHeap: MaxHeap) {
    when {
        minHeap.size - 1 > maxHeap.size -> maxHeap.add(minHeap.pop())
        maxHeap.size - 1 > minHeap.size -> minHeap.add(maxHeap.pop())
    }
}

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()

    val minHeap = MinHeap(n / 2 + 1)
    val maxHeap = MaxHeap(n / 2 + 1)

    for (a_i in 0 until n) {
        val value = scanner.nextInt()

        when {
            value > minHeap.peek() -> minHeap.add(value)
            value < maxHeap.peek() -> maxHeap.add(value)
            minHeap.size < maxHeap.size -> minHeap.add(value)
            else -> maxHeap.add(value)
        }
        normalize(minHeap, maxHeap)

        val med = when {
            minHeap.size == maxHeap.size -> (minHeap.peek() + maxHeap.peek()) / 2.0
            minHeap.size > maxHeap.size -> minHeap.peek().toDouble()
            else -> maxHeap.peek().toDouble()
        }
        println("%.1f".format(med))
    }
}