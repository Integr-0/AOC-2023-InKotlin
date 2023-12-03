import utils.println
import utils.readInput
import java.lang.NullPointerException

/**
 * # Day 03
 * **See Also:** [Advent Of Code](http://adventofcode.com)
 *
 * **See Also:** [Template](https://github.com/kotlin-hands-on/advent-of-code-kotlin-template)
 */
fun main() {
    val lines = readInput("Day03")

    Day03().solvePart1(lines)
    Day03().solvePart2(lines)
}

class Day03 {
    fun solvePart1(lines: List<String>) {
        var sum = 0
        for (l in (0..<lines.count())) {
            var line = lines[l]
            for (i in (0..<line.count())) {
                var c = line[i]
                if (c.isSymbol()) {
                    checkAdjacent(i, l, lines).forEach {
                        sum += it
                    }
                }
            }
        }
        sum.println("Part 1 Total: {val}")
    }

    fun solvePart2(lines: List<String>) {
        var sum = 0
        for (l in (0..<lines.count())) {
            var line = lines[l]
            for (i in (0..<line.count())) {
                var c = line[i]
                if (c.isGear()) {
                    var nums = checkAdjacent(i, l, lines)
                    if (nums.count() >= 2) {
                        var finalRatio = 1;
                        nums.forEach { finalRatio *= it }
                        sum += finalRatio
                    }
                }
            }
        }
        sum.println("Part 2 Total: {val}")
    }

    fun Char.isSymbol(): Boolean {
        return !this.isDigit() && this != '.'
    }

    fun Char.isGear(): Boolean {
        return this == '*'
    }

    fun charFromXY(x: Int, y: Int, lines: List<String>): Char {
        return lines[y][x]
    }

    fun checkAdjacent(x: Int, y: Int, lines: List<String>): List<Int> {
        var returnObj: MutableList<Int> = mutableListOf()
        if (charFromXY(x-1,y, lines).isDigit()) {
            returnObj += findNumber(x-1,y, lines)
        }
        if (charFromXY(x+1,y, lines).isDigit()) {
            returnObj += findNumber(x+1,y, lines)
        }

        if (charFromXY(x,y+1, lines).isDigit()) {
            returnObj += findNumber(x,y+1, lines)
        } else {
            if (charFromXY(x-1,y+1, lines).isDigit()) {
                returnObj += findNumber(x-1,y+1, lines)
            }
            if (charFromXY(x+1,y+1, lines).isDigit()) {
                returnObj += findNumber(x+1,y+1, lines)
            }
        }

        if (charFromXY(x,y-1, lines).isDigit()) {
            returnObj += findNumber(x,y-1, lines)
        } else {
            if (charFromXY(x-1,y-1, lines).isDigit()) {
                returnObj += findNumber(x-1,y-1, lines)
            }
            if (charFromXY(x+1,y-1, lines).isDigit()) {
                returnObj += findNumber(x+1,y-1, lines)
            }
        }

        return returnObj
    }

    fun findNumber(x: Int, y: Int, lines: List<String>): Int {
        if (charFromXY(x-1, y, lines).isDigit()) {
            return if (charFromXY(x-2, y, lines).isDigit()) {
                (charFromXY(x-2, y, lines).toString()+charFromXY(x-1, y, lines).toString()+charFromXY(x, y, lines).toString()).toInt()
            } else if (charFromXY(x+1, y, lines).isDigit()) {
                (charFromXY(x-1, y, lines).toString()+charFromXY(x, y, lines).toString()+charFromXY(x+1, y, lines).toString()).toInt()
            } else {
                (charFromXY(x-1, y, lines).toString()+charFromXY(x, y, lines).toString()).toInt()
            }
        }

        else if (charFromXY(x+1, y, lines).isDigit()) {
            return if (charFromXY(x+2, y, lines).isDigit()) {
                (charFromXY(x, y, lines).toString()+charFromXY(x+1, y, lines).toString()+charFromXY(x+2, y, lines).toString()).toInt()
            } else {
                (charFromXY(x, y, lines).toString()+charFromXY(x+1, y, lines).toString()).toInt()
            }
        }

        else {
            return charFromXY(x, y, lines).toString().toInt()
        }
    }
}