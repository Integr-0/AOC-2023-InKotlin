import utils.println
import utils.readInput
import java.lang.NullPointerException

/**
 * # Day 02
 * **See Also:** [Advent Of Code](http://adventofcode.com)
 *
 * **See Also:** [Template](https://github.com/kotlin-hands-on/advent-of-code-kotlin-template)
 */
fun main() {
    val lines = readInput("Day02")
    val day02 = Day02(lines)

    day02.solvePart1()
    day02.solvePart2()
}
class Day02(private val lines: List<String>) {
    fun solvePart1() {
        val startingRedCubes = 12
        val startingGreenCubes = 13
        val startingBlueCubes = 14

        var finalNum = 0

        for (l in lines) {
            val hands = getHands(l)
            var valid = true

            for (hand in hands) {
                for (cube in hand) {

                    when (cube.color) {
                        Color.RED -> {
                            if (cube.amount > startingRedCubes) valid = false

                        }

                        Color.GREEN -> {
                            if (cube.amount > startingGreenCubes) valid = false
                        }

                        Color.BLUE -> {
                            if (cube.amount > startingBlueCubes) valid = false
                        }

                        Color.NONE ->  {
                            throw NullPointerException("Color was NONE")
                        }
                    }
                }
            }

            if (valid) {
                finalNum += lines.indexOf(l)+1
            }
        }

        finalNum.println("Part 1 Total: {val}")
    }

    fun solvePart2() {
        var finalNum = 0

        for (l in lines) {
            var maxRed = 0
            var maxGreen = 0
            var maxBlue = 0

            val hands = getHands(l)
            for (hand in hands) {
                for (cube in hand) {

                    when (cube.color) {
                        Color.RED -> {
                            if (cube.amount > maxRed) maxRed = cube.amount
                        }

                        Color.GREEN -> {
                            if (cube.amount > maxGreen) maxGreen = cube.amount
                        }

                        Color.BLUE -> {
                            if (cube.amount > maxBlue) maxBlue = cube.amount
                        }

                        Color.NONE ->  {
                            throw NullPointerException("Color was NONE")
                        }
                    }
                }
            }

            finalNum += maxRed * maxGreen * maxBlue
        }

        finalNum.println("Part 2 Total: {val}")
    }

    private fun getHands(line: String): List<List<Cube>> {
        val newLine = line.substringAfter(": ")
        val hands = newLine.split("; ")
        val finalHands: MutableList<List<Cube>> = mutableListOf()

        for (h in hands) {
            finalHands += handToCubes(h)
        }
        return finalHands
    }

    private fun handToCubes(hand: String): List<Cube> {
        val cubeStrings = hand.split(", ")
        val cubes: MutableList<Cube> = mutableListOf()

        for (s in cubeStrings) {
            var count = s.substringBefore(" ").toInt()
            var color = colorToColor(s.dropWhile { c: Char ->  c.isDigit()}.removePrefix(" "))
            cubes += Cube(color, count)
        }
        return cubes
    }

    private fun colorToColor(string: String): Color {
        return when (string) {
            "red" -> Color.RED
            "green" -> Color.GREEN
            "blue" -> Color.BLUE
            else -> Color.NONE
        }
    }

    data class Cube(val color: Color, val amount: Int)

    enum class Color {
        RED,
        GREEN,
        BLUE,
        NONE
    }
}
