package cracking

/**
 * Giving a list of people with their birth and death years,
 * implement a method to compute the year with the most number of people alive.
 * You may assume that all people were born between 1900 and 2000 (inclusive).
 * If a person was alive during any portion of that year, they should be included in that year's count.
 * For example, Person(birth = 1908, death = 1909) is included in the counts for both 1908 and 1909.
 */
fun Year.howMany(people: List<Person>): Int {
    val deltas = IntArray(101)
    people.forEach { person ->
        val birthYear = person.birth - 1900
        val deathYear = person.death - 1900

        deltas[birthYear]++
        deltas[deathYear + 1]--
    }

    return (0..(this - 1900)).reduce { acc, index ->
        acc + deltas[index]
    }
}

fun Year.between(a: Year, b: Year) = this in a..b

typealias Year = Int

data class Person(val birth: Year, val death: Year)

fun main(args: Array<String>) {
    val people = listOf(
            Person(1901, 1940),
            Person(1910, 1940),
            Person(1920, 1940),
            Person(1930, 1980),
            Person(1940, 1940),
            Person(1980, 1999),
            Person(1998, 1999)
    )
    (1900 until 2000).forEach {
        println("$it: ${it.howMany(people)}")
    }
}