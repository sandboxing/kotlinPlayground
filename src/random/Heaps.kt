package random

import java.util.*

/**
 * https://www.hackerrank.com/challenges/ctci-find-the-running-median
 */

abstract class Heap(capacity: Int) {
    fun Int.parentIndex() = (this - 1) / 2
    fun Int.leftChildIndex() = this * 2 + 1
    fun Int.rightChildIndex() = this * 2 + 2

    val values = IntArray(capacity)
    var size = 0

    val lastIndex: Int
        get() = size - 1

    fun add(value: Int) {
        size++
        values[lastIndex] = value
        heapifyUp()
    }

    fun pop(): Int {
        val value = values[0]
        values[0] = values[lastIndex]
        size--
        heapifyDown()
        return value
    }

    fun peek() = values[0]

    fun parent(index: Int) = values[index.parentIndex()]
    fun leftChild(index: Int) = values[index.leftChildIndex()]
    fun rightChild(index: Int) = values[index.rightChildIndex()]
    fun hasLeftChild(index: Int) = index.leftChildIndex() < size
    fun hasRightChild(index: Int) = index.rightChildIndex() < size

    fun swap(a: Int, b: Int) {
        values[a] = values[a] xor values[b]
        values[b] = values[a] xor values[b]
        values[a] = values[a] xor values[b]
    }

    abstract fun heapifyUp()
    abstract fun heapifyDown()

    override fun toString() = values.contentToString()
}

class MaxHeap(capacity: Int) : Heap(capacity) {
    override fun heapifyDown() {
        var index = 0
        while (hasLeftChild(index)) {
            var biggestChildrenIndex = index.leftChildIndex()
            if (hasRightChild(index) && rightChild(index) > leftChild(index)) {
                biggestChildrenIndex = index.rightChildIndex()
            }
            if (values[index] < values[biggestChildrenIndex]) {
                swap(index, biggestChildrenIndex)
                index = biggestChildrenIndex
            } else {
                break
            }
        }
    }

    override fun heapifyUp() {
        var index = lastIndex
        while (index > 0) {
            if (values[index] > parent(index)) {
                swap(index, index.parentIndex())
                index = index.parentIndex()
            } else {
                break
            }
        }
    }
}

class MinHeap(capacity: Int) : Heap(capacity) {
    override fun heapifyDown() {
        var index = 0
        while (hasLeftChild(index)) {
            var smallestChildrenIndex = index.leftChildIndex()
            if (hasRightChild(index) && rightChild(index) < leftChild(index)) {
                smallestChildrenIndex = index.rightChildIndex()
            }
            if (values[index] > values[smallestChildrenIndex]) {
                swap(index, smallestChildrenIndex)
                index = smallestChildrenIndex
            } else {
                break
            }
        }
    }

    override fun heapifyUp() {
        var index = lastIndex
        while (index > 0) {
            if (values[index] < parent(index)) {
                swap(index, index.parentIndex())
                index = index.parentIndex()
            } else {
                break
            }
        }
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