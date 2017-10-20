package random

data class MyLinkedList<T>(val head: MyNode<T>)

data class MyNode<T>(
        val data: T,
        var next: MyNode<T>? = null
)

fun <T> MyLinkedList<T>.reverse(): MyLinkedList<T> {
    var previous: MyNode<T>? = null
    var current: MyNode<T>? = head
    while (current != null) {
        val next = current.next
        current.next = previous

        previous = current
        current = next
    }

    return MyLinkedList(previous!!)
}

fun main(args: Array<String>) {
    val head = MyNode(1)

    var current = head
    for (i in 2..10) {
        MyNode(i).let {
            current.next = it
            current = it
        }
    }

    val myLinkedList = MyLinkedList(head)

    println(myLinkedList)
    println(myLinkedList.reverse())
}