import utils.println
import utils.readInput

/**
 * # Day 04
 * **See Also:** [Advent Of Code](http://adventofcode.com)
 *
 * **See Also:** [Template](https://github.com/kotlin-hands-on/advent-of-code-kotlin-template)
 */
fun main() {
    val lines = readInput("Day04")
    val day04 = Day04(lines)

    day04.solvePart1()
    day04.solvePart2()
}

class Day04(private val lines: List<String>) {
    fun solvePart1() {
        var finalSum = 0
        for (l in lines) {
            val newLine = l.substringAfter(": ")
            val subLines = newLine.split(" | ")

            val winningNumbers: MutableList<Int> = mutableListOf()
            val currentNumbers: MutableList<Int> = mutableListOf()

            val newSub0: MutableList<String> = mutableListOf()
            val newSub1: MutableList<String> = mutableListOf()

            if (subLines[0][0] == ' ') {
                subLines[0].substring(1).split("  ").forEach { newSub0 += it }
            } else {
                subLines[0].split("  ").forEach { newSub0 += it }
            }

            if (subLines[1][0] == ' ') {
                subLines[1].substring(1).split("  ").forEach { newSub1 += it }
            } else {
                subLines[1].split("  ").forEach { newSub1 += it }
            }

            for (s in newSub0) {
                s.split(" ").forEach { winningNumbers += it.toInt() }
            }

            for (s in newSub1) {
                s.split(" ").forEach { currentNumbers += it.toInt() }
            }

            var sum = 0

            for (num in currentNumbers) {
                if (winningNumbers.contains(num)) {
                    if (sum != 0) {
                        sum *= 2
                    } else {
                        sum = 1
                    }
                }
            }

            finalSum += sum
        }

        finalSum.println("Part 1 Total: {val}")
    }

    fun solvePart2() {
        val allCards: MutableList<Int> = mutableListOf()
        lines.forEach { _ -> allCards.add(1) }

        print("[")

        var cardCount = 0
        var progIterator = 0
        for (l in lines) {
            val evaluated = evalCard(l)
            for (i in (0..<allCards[lines.indexOf(l)])) {
                cardCount++
                for (k in (lines.indexOf(l)+1..lines.indexOf(l)+evaluated)) {
                    allCards[k]++
                }
            }

            if (progIterator == 0) {
                print("=")
                progIterator = 2
            } else {
                progIterator--
            }
        }

        print("]")
        println("")
        cardCount.println("Part 2 Total: {val}")
    }

    private fun evalCard(line: String): Int {
        val newLine = line.substringAfter(": ")
        val subLines = newLine.split(" | ")

        val winningNumbers: MutableList<Int> = mutableListOf()
        val currentNumbers: MutableList<Int> = mutableListOf()

        val newSub0: MutableList<String> = mutableListOf()
        val newSub1: MutableList<String> = mutableListOf()

        if (subLines[0][0] == ' ') {
            subLines[0].substring(1).split("  ").forEach { newSub0 += it }
        } else {
            subLines[0].split("  ").forEach { newSub0 += it }
        }

        if (subLines[1][0] == ' ') {
            subLines[1].substring(1).split("  ").forEach { newSub1 += it }
        } else {
            subLines[1].split("  ").forEach { newSub1 += it }
        }

        for (s in newSub0) {
            s.split(" ").forEach { winningNumbers += it.toInt() }
        }

        for (s in newSub1) {
            s.split(" ").forEach { currentNumbers += it.toInt() }
        }

        var winningCount = 0

        for (num in currentNumbers) {
            if (winningNumbers.contains(num)) {
                winningCount++
            }
        }

        return winningCount
    }
}