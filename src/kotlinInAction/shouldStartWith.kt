package kotlinInAction

object start
object end

infix fun String.should(s: start) = StartWrapper(this)
infix fun String.should(e: end) = EndWrapper(this)

class StartWrapper(private val string: String) {
    infix fun with(expected: String) {
        if (!string.startsWith(expected)) throw AssertionError("String '$string' does not start with $expected")
    }
}
class EndWrapper(private val string: String) {
    infix fun with(expected: String) {
        if (!string.endsWith(expected)) throw AssertionError("String '$string' does not end with $expected")
    }
}

fun main(args: Array<String>) {
    "kinton" should end with "ton"
}