package random

class MyIntArray(val array: IntArray) {
    var virtualSize = array.size
    val fullSize = array.size

    operator fun get(index: Int) = array[index]
    operator fun set(index: Int, value: Int) {
        array[index] = value
    }

    override fun toString() = array.contentToString()

    val lastNodeIndex: Int
        get() = virtualSize - 1

    fun removeTop() {
        swap(0, lastNodeIndex)
        virtualSize--
    }

    fun heapSort() {
        while (virtualSize > 1) {
            bubbleUp(lastNodeIndex.parentIndex())
            removeTop()
            println(this)
        }
    }

    fun bubbleUp(index: Int) {
        if (index < 0) return

        var smallestChildIndex = index.leftChildIndex()
        if (index.rightChildIndex() < virtualSize &&
                this[index.rightChildIndex()] < this[index.leftChildIndex()]) {
            smallestChildIndex = index.rightChildIndex()
        }
        if (this[index] > this[smallestChildIndex]) {
            swap(index, smallestChildIndex)
        }
        if (index > 0)
            bubbleUp(index - 1)
    }

    fun swap(a: Int, b: Int) {
        this[a] = this[a] xor this[b]
        this[b] = this[a] xor this[b]
        this[a] = this[a] xor this[b]
    }
}

fun Int.parentIndex() = (this - 1) / 2
fun Int.leftChildIndex() = this * 2 + 1
fun Int.rightChildIndex() = this * 2 + 2

fun main(args: Array<String>) {
    val a = MyIntArray(intArrayOf(
            3, 6, 4, 2, 9, 8, 1
    ))
    println(a)
    a.heapSort()
    println(a)
}