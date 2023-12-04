import utils.println
import utils.readInput

/**
 * # Day 03
 * **See Also:** [Advent Of Code](http://adventofcode.com)
 *
 * **See Also:** [Template](https://github.com/kotlin-hands-on/advent-of-code-kotlin-template)
 */
fun main() {
    val lines = readInput("Day03")
    val day03 = Day03(lines)

    day03.solvePart1()
    day03.solvePart2()
}

class Day03(private val lines: List<String>) {
    fun solvePart1() {
        var sum = 0
        for (l in (0..<lines.count())) {
            val line = lines[l]
            for (i in (0..<line.count())) {
                val c = line[i]
                if (c.isSymbol()) {
                    checkAdjacent(i, l).forEach {
                        sum += it
                    }
                }
            }
        }
        sum.println("Part 1 Total: {val}")
    }

    fun solvePart2() {
        var sum = 0
        for (l in (0..<lines.count())) {
            val line = lines[l]
            for (i in (0..<line.count())) {
                val c = line[i]
                if (c.isGear()) {
                    val numbers = checkAdjacent(i, l)
                    if (numbers.count() >= 2) {
                        var finalRatio = 1
                        numbers.forEach { finalRatio *= it }
                        sum += finalRatio
                    }
                }
            }
        }
        sum.println("Part 2 Total: {val}")
    }

    private fun Char.isSymbol(): Boolean = !this.isDigit() && this != '.'
    private fun Char.isGear(): Boolean = this == '*'
    private fun charFromXY(x: Int, y: Int): Char = lines[y][x]

    private fun checkAdjacent(x: Int, y: Int): List<Int> {
        val returnObj: MutableList<Int> = mutableListOf()
        if (charFromXY(x-1,y).isDigit()) {
            returnObj += findNumber(x-1,y)
        }
        if (charFromXY(x+1,y).isDigit()) {
            returnObj += findNumber(x+1,y)
        }

        if (charFromXY(x,y+1).isDigit()) {
            returnObj += findNumber(x,y+1)
        } else {
            if (charFromXY(x-1,y+1).isDigit()) {
                returnObj += findNumber(x-1,y+1)
            }
            if (charFromXY(x+1,y+1).isDigit()) {
                returnObj += findNumber(x+1,y+1)
            }
        }

        if (charFromXY(x,y-1).isDigit()) {
            returnObj += findNumber(x,y-1)
        } else {
            if (charFromXY(x-1,y-1).isDigit()) {
                returnObj += findNumber(x-1,y-1)
            }
            if (charFromXY(x+1,y-1).isDigit()) {
                returnObj += findNumber(x+1,y-1)
            }
        }

        return returnObj
    }

    private fun findNumber(x: Int, y: Int): Int {
        if (charFromXY(x-1, y).isDigit()) {
            return if (charFromXY(x-2, y).isDigit()) {
                (charFromXY(x-2, y).toString()+charFromXY(x-1, y).toString()+charFromXY(x, y).toString()).toInt()
            } else if (charFromXY(x+1, y).isDigit()) {
                (charFromXY(x-1, y).toString()+charFromXY(x, y).toString()+charFromXY(x+1, y).toString()).toInt()
            } else {
                (charFromXY(x-1, y).toString()+charFromXY(x, y).toString()).toInt()
            }
        }

        else if (charFromXY(x+1, y).isDigit()) {
            return if (charFromXY(x+2, y).isDigit()) {
                (charFromXY(x, y).toString()+charFromXY(x+1, y).toString()+charFromXY(x+2, y).toString()).toInt()
            } else {
                (charFromXY(x, y).toString()+charFromXY(x+1, y).toString()).toInt()
            }
        }

        else {
            return charFromXY(x, y).toString().toInt()
        }
    }
}