package hackerrank.balancedTree

import java.text.DecimalFormat
import java.util.*


/**
 * https://www.hackerrank.com/challenges/median
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
    fun hasParent(index: Int) = index > 0
    fun hasRightChild(index: Int) = index.rightChildIndex() < size

    fun swap(a: Int, b: Int) {
        values[a] = values[a] xor values[b]
        values[b] = values[a] xor values[b]
        values[a] = values[a] xor values[b]
    }

    abstract fun heapifyUp(initIndex: Int = lastIndex)
    abstract fun heapifyDown(initIndex: Int = 0)
    abstract fun heapify(index: Int)

    override fun toString() = values.contentToString()

    fun remove(value: Int) {
        val index = values.indexOf(value)
        values[index] = values[lastIndex]
        size--
        heapify(index)
    }

    fun contains(value: Int) = value in values
}

class MaxHeap(capacity: Int) : Heap(capacity) {
    override fun heapify(index: Int) {
        if (hasParent(index)) {
            if (values[index] < parent(index)) {
                heapifyDown(index)
            } else {
                heapifyUp(index)
            }
        } else {
            heapifyDown()
        }
    }

    override fun heapifyDown(initIndex: Int) {
        var index = initIndex
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

    override fun heapifyUp(initIndex: Int) {
        var index = initIndex
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
    override fun heapify(index: Int) {
        if (hasParent(index)) {
            if (values[index] > parent(index)) {
                heapifyDown(index)
            } else {
                heapifyUp(index)
            }
        } else {
            heapifyDown()
        }
    }

    override fun heapifyDown(initIndex: Int) {
        var index = initIndex
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

    override fun heapifyUp(initIndex: Int) {
        var index = initIndex
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

fun median(minHeap: MinHeap, maxHeap: MaxHeap): Double {
    if (minHeap.size == 0 && maxHeap.size == 0) throw RuntimeException("wrong")

    return when {
        minHeap.size > maxHeap.size -> minHeap.peek().toDouble()
        maxHeap.size > minHeap.size -> maxHeap.peek().toDouble()
        else -> (maxHeap.peek().toDouble() + minHeap.peek().toDouble()) / 2
    }
}

fun main(args: Array<String>) {

    val scanner = Scanner(System.`in`)
    val n: Int
    n = scanner.nextInt()

    val maxHeap = MaxHeap(n)
    val minHeap = MinHeap(n)

    val nf = DecimalFormat("##################################################.##################################################")
    for (i in 0 until n) {
        val cmd: String = scanner.next()
        val value = scanner.nextInt()

        try {
            when (cmd) {
                "r" -> {
                    when {
                        maxHeap.contains(value) -> maxHeap.remove(value)
                        minHeap.contains(value) -> minHeap.remove(value)
                        else -> throw RuntimeException("wrong")
                    }
                }
                "a" -> {
                    if (value < maxHeap.peek()) {
                        maxHeap.add(value)
                    } else {
                        minHeap.add(value)
                    }
                }
            }
            normalize(minHeap, maxHeap)
            println(nf.format(median(minHeap, maxHeap)))
        } catch (e: RuntimeException) {
            println("Wrong!")
        }
    }
}