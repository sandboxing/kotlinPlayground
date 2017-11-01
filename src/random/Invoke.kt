package random

import java.util.*

data class Employee(
        val name: String,
        val age: Int
) {
    init {
        println("constructor")
    }

    companion object Factory {
        operator fun invoke(name: String, age: Int): Employee {
            println("invoke")
            return Employee(name, age)
        }
    }
}

fun main(args: Array<String>) {
    println(Employee("Benoit", 32))

    println(Employee.invoke("Benoit", 32))

    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val a = IntArray(n)
    for (a_i in 0 until n) {
        a[a_i] = scanner.nextInt()
    }
}