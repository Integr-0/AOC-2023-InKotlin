import utils.*
/**
 * # Day 09
 * **See Also:** [Advent Of Code](http://adventofcode.com)
 *
 * **See Also:** [Template](https://github.com/kotlin-hands-on/advent-of-code-kotlin-template)
 */
fun main() {
    val lines = readInput("Day09")
    val day09 = Day09(lines)

    day09.solvePart1()
    day09.solvePart2()
}

class Day09(private val lines: List<String>) {
    fun solvePart1() {
        var total = 0

        for (l in lines) {
            val allNumbers: MutableList<Int> = mutableListOf()
            l.split(" ").forEach {allNumbers += it.toInt()}

            val numberLists: MutableList<List<Int>> = mutableListOf()
            numberLists += allNumbers

            while (!numberLists.last().isZero()) {
                numberLists += getDiffs(numberLists.last())
            }

            val predictedValues: MutableList<Int> = mutableListOf(0)
            for (i in (1..<numberLists.count())) {
                predictedValues += predictedValues.last() + numberLists[i-1].last()
            }

            total += predictedValues.last()
        }

        total.println("Part 1 Total: {val}")
    }

    fun solvePart2() {
        var total = 0

        for (l in lines) {
            val allNumbers: MutableList<Int> = mutableListOf()
            l.split(" ").forEach {allNumbers += it.toInt()}

            var numberLists: MutableList<List<Int>> = mutableListOf()
            numberLists += allNumbers

            while (!numberLists.last().isZero()) {
                numberLists += getDiffs(numberLists.last())
            }

            numberLists = numberLists.reversed().toMutableList()

            val predictedValues: MutableList<Int> = mutableListOf(0)
            for (i in (0..<numberLists.count())) {
                predictedValues += numberLists[i].first() - predictedValues.last()
            }

            total += predictedValues.last()
        }

        total.println("Part 2 Total: {val}")
    }

    private fun getDiffs(allNumbers: List<Int>): List<Int> {
        val outList: MutableList<Int> = mutableListOf()
        for (index in (1..<allNumbers.count())) {
            val i: Int = allNumbers[index]
            val diff = i - allNumbers[index-1]
            outList += diff
        }
        return outList
    }

    private fun List<Int>.isZero(): Boolean {
        this.forEach {
            if (it != 0) {
                return false
            }
        }
        return true
    }
}

