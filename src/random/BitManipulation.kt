package random

fun Int.setBit(index: Int) = this or (1 shl index)

fun Int.getBit(index: Int) = this shr index and 1

fun Int.clearBit(index: Int) = this and (1 shl index).inv()

fun Int.updateBit(index: Int, bitIsOne: Boolean): Int {
    return if (bitIsOne) {
        setBit(index)
    } else {
        clearBit(index)
    }
}

fun main(args: Array<String>) {
    println(1.setBit(3)) // should be 1001 (9)
    println(4.setBit(3)) // should be 1100 (12)
    println(5.setBit(1)) // should be 111 (7)
    println(15.setBit(4)) // should be 11111 (31)


    println(5.getBit(0)) // 1
    println(5.getBit(1)) // 0
    println(5.getBit(2)) // 1
    println(5.getBit(3)) // 0


    println(13.clearBit(0)) // 12
    println(13.clearBit(1)) // 13
    println(13.clearBit(2)) // 9
    println(13.clearBit(3)) // 5
    println(13.clearBit(4)) // 13
}