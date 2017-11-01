package random

/**
 * https://www.hackerrank.com/challenges/ctci-coin-change
 */

import java.util.*

/**
 * https://www.youtube.com/watch?v=ZaVM057DuzE
 */
fun makeChangeFunky(coins: IntArray, money: Int): Long {
    val arrays = LongArray(money + 1)
    arrays[0] = 1L
    coins.forEach { coin ->
        (coin..money).forEach { amount ->
            arrays[amount] += arrays[amount - coin]
        }
    }
    return arrays[money]
}

fun makeChange(coins: IntArray, money: Int) =
        makeChange(coins, money, 0, hashMapOf())

fun makeChange(coins: IntArray, money: Int, index: Int, memo: HashMap<String, Long>): Long {
    if (money == 0) return 1
    if (coins.isEmpty()) return 0
    if (index >= coins.size) return 0

    val key = "$money-$index"
    memo[key]?.let { return it }

    val upperBound = money / coins[index]
    var ways = 0L
    (0..upperBound).forEach { i ->
        ways += makeChange(coins, money - i * coins[index], index + 1, memo)
    }

    memo.put(key, ways)
    return ways
}

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val m = scanner.nextInt()
    val coins = IntArray(m)
    for (coins_i in 0 until m) {
        coins[coins_i] = scanner.nextInt()
    }
    println(makeChange(coins, n))
}