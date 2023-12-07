import utils.*

/**
 * # Day 06
 * **See Also:** [Advent Of Code](http://adventofcode.com)
 *
 * **See Also:** [Template](https://github.com/kotlin-hands-on/advent-of-code-kotlin-template)
 */
fun main() {
    val lines = readInput("Day06")
    val day06 = Day06(lines)

    day06.solvePart1()
    day06.solvePart2()
}

class Day06(private val lines: List<String>) {
    fun solvePart1() {
        val times = lines[0].split(" ")
        val distances = lines[1].split(" ")

        var final = 0

        for (i in (0..3)) {
            var validAmount = 0
            val time = times[i]
            val record = distances[i]

            for (it in (0..time.toInt())) {
                if (calcTotalDist(it, time.toInt()) > record.toInt()) {
                    validAmount++
                }
            }

            if (final != 0) {
                final *= validAmount
            } else final = validAmount
        }

        final.println("Part 1 Total: {val}")
    }

    fun solvePart2() {
        val time = lines[0].filter { char -> char != ' ' }
        val distance = lines[1].filter { char -> char != ' ' }

        var validAmount = 0

        for (it in (0..time.toLong())) {
            if (calcTotalDistL(it, time.toLong()) > distance.toLong()) {
                validAmount++
            }
        }

        validAmount.println("Part 2 Total: {val}")
    }

    private fun calcTotalDist(msDown: Int, msDur: Int): Int {
        val movementTime = msDur - msDown
        return movementTime * msDown
    }

    private fun calcTotalDistL(msDown: Long, msDur: Long): Long {
        val movementTime = msDur - msDown
        return movementTime * msDown
    }
}

