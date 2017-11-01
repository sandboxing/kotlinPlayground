package cracking

/**
 * Giving a list of people with their birth and death years,
 * implement a method to compute the year with the most number of people alive.
 * You may assume that all people were born between 1900 and 2000 (inclusive).
 * If a person was alive during any portion of that year, they should be included in that year's count.
 * For example, Person(birth = 1908, death = 1909) is included in the counts for both 1908 and 1909.
 */
private fun Int.howMany(): Int {
    // b: preprocess into a 100 sized hashtable and yolo
    return peopleRegistry[this - 1900]
}

val peopleRegistry = IntArray(100)


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

    people.forEach { person ->
        (person.birth..person.death).forEach { year ->
            peopleRegistry[year - 1900] += 1
        }
    }
    (1900 until 2000).forEach {
        println("$it: ${it.howMany()}")
    }
}