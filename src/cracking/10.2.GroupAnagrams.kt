package cracking

/**
 * Write a method to cracking.sort an array of strings so that all the anagrams are next to each other.
 */

fun String.sort() = chars().sorted().toArray().contentToString()

fun groupAnagrams(strings: Array<String>): List<String> {
    val map = hashMapOf<String, MutableList<String>>()

    for (string in strings) {
        val key = string.sort()
        val list: MutableList<String>? = map[key]
        list?.let { list.add(string) } ?: map.put(key, mutableListOf(string))
    }

    return map.values.flatten()
}

fun main(args: Array<String>) {
    val strings = arrayOf("abba", "baab", "toto", "efo", "foe", "baba")
    println(groupAnagrams(strings))
}