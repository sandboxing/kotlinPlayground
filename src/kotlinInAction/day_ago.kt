package kotlinInAction

import java.time.LocalDate
import java.time.Period

class Day(val count: Int)

val Int.days: Period
    get() = Period.ofDays(this)

val Period.ago: LocalDate
    get() = LocalDate.now() - this

val Period.fromNow: LocalDate
    get() = LocalDate.now() + this

fun main(args: Array<String>) {
    println(3.days.ago)
    println(3.days.fromNow)
}