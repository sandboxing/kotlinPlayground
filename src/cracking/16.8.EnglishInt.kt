package cracking

val words = hashMapOf<Int, String>(
//        1 to "One",
//        2 to "Two",
//        3 to "Two",
//        4 to "Two",
//        5 to "Two",
//        6 to "Two",
//        7 to "Two",
//        8 to "Two",
//        9 to "Two",
)

/**
 * Given any integer, print an English phrase that describes the integer
 * e.g., "One Thousand, Two Hundred Thirty Four"
 */
fun Int.toEnglish(): String {
    // How about something kind of recursive for every 3 digits ?
    // Else is like a separator used on a join
    TODO()
}

fun main(args: Array<String>) {
    println(12345.toEnglish())
}