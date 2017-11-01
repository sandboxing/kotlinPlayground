package random

/**
 * Please write a ready to ship function (in Kotlin or Java) that determines
 * if a string starts with an upper-case letter A-Z.
 */
fun String.startsWithUpperCaseLetter(): Boolean {
    // check null for static calls from Java
    if (isNullOrBlank()) return false

    return get(0) in 'A'..'Z'
}

fun main(args: Array<String>) {
    println("${"".startsWithUpperCaseLetter()} should be false")
    println("${"Anything".startsWithUpperCaseLetter()} should be true")
    println("${"ZOOM".startsWithUpperCaseLetter()} should be true")
    println("${"日本語".startsWithUpperCaseLetter()} should be false")
    println("${"Évian".startsWithUpperCaseLetter()} should be false but depends on the spec I guess")
    println("${"nope".startsWithUpperCaseLetter()} should be false")
}