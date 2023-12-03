import utils.*

/**
 * # Day 01
 * **See Also:** [Advent Of Code](http://adventofcode.com)
 *
 * **See Also:** [Template](https://github.com/kotlin-hands-on/advent-of-code-kotlin-template)
 */
fun main() {
    val lines = readInput("Day01")
    val day01 = Day01(lines)

    day01.solvePart1()
    day01.solvePart2()
}

class Day01(private val lines: List<String>) {
    fun solvePart1() {
        var total = 0

        for (l in lines) {
            val numbers = l.filter { it.isDigit() }

            val first = numbers[0]
            val last = numbers[numbers.length-1]

            total += (first.toString()+last.toString()).toInt()
        }

        total.println("Part 1 Total: {val}")
    }

    fun solvePart2() {
        var total = 0
        val numbers = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "1", "2", "3", "4", "5", "6", "7", "8", "9")

        for (l in lines) {
            val first = l.findAnyOf(numbers)
            val last = l.findLastAnyOf(numbers)

            total += (numFromString(first!!.second)+numFromString(last!!.second)).toInt()
        }

        total.println("Part 2 Total: {val}")
    }

    private fun numFromString(str: String): String {
        return when (str) {
            "one" -> "1"
            "two" -> "2"
            "three" -> "3"
            "four" -> "4"
            "five" -> "5"
            "six" -> "6"
            "seven" -> "7"
            "eight" -> "8"
            "nine" -> "9"
            else -> str
        }
    }
}

