import utils.*
import java.lang.NullPointerException

/**
 * # Day 08
 * **See Also:** [Advent Of Code](http://adventofcode.com)
 *
 * **See Also:** [Template](https://github.com/kotlin-hands-on/advent-of-code-kotlin-template)
 */
fun main() {
    val lines = readInput("Day08")
    val day08 = Day08(lines)

    day08.solvePart1()
    day08.solvePart2() // TODO: Too slow to see Results
}

class Day08(private val lines: List<String>) {
    fun solvePart1() {
        val instructions = lines[0]
        var iterationCounter = 0
        var zzzFound = false;
        var currentName = "AAA"

        while (!zzzFound) {
            for (i in instructions) {
                if (currentName == "ZZZ") {
                    zzzFound = true
                    break
                }

                val currLine = findLine(currentName)
                currentName = if (i == 'R') {
                    currLine.getRight()
                } else {
                    currLine.getLeft()
                }
                iterationCounter++
            }
        }

        iterationCounter.println("Part 1 Total: {val}")
    }

    fun solvePart2() {
        val srv = MultiThreadingService(1)
        val instructions = lines[0]
        var iterationCounter = 0
        var goalReached = false;
        val currentNames: MutableList<String> = findStartingPoses()

        while (!goalReached) {
            for (i in instructions) {
                if (currentNames.endWithZ()) {
                    goalReached = true
                    break
                }

                for (n in currentNames) {
                    val currLine = findLine(n)
                    currentNames[currentNames.indexOf(n)] = if (i == 'R') {
                        currLine.getRight()
                    } else {
                        currLine.getLeft()
                    }
                }
                iterationCounter++
                srv.exec {
                    iterationCounter.println()
                }
            }
        }

        iterationCounter.println("Part 2 Total: {val}")
    }

    private fun findStartingPoses(): MutableList<String> {
        val list: MutableList<String> = mutableListOf()
        for (l in lines) {
            if (l.getName().endsWith("A")) {
                list += l.getName()
            }
        }
        return list
    }

    private fun List<String>.endWithZ(): Boolean {
        for (i in (0..<this.count())) {
            if (!this[i].endsWith("Z")) {
                return false
            }
        }
        return true
    }

    private fun String.getLeft(): String {
        return this
            .substringAfter("(")
            .split(", ")[0]
    }

    private fun String.getRight(): String {
        return this
            .substringAfter("(")
            .split(", ")[1]
            .removeSuffix(")")
    }

    private fun String.getName(): String {
        return this.substringBefore(" ")
    }

    private fun findLine(name: String): String {
        for (l in lines) {
            if (l.getName() == name) {
                return l
            }
        }
        throw NullPointerException("Name not Found!")
    }
}

