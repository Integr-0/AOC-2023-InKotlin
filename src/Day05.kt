import utils.MultiThreadingService
import utils.println
import utils.readInput

/**
 * # Day 05
 * **See Also:** [Advent Of Code](http://adventofcode.com)
 *
 * **See Also:** [Template](https://github.com/kotlin-hands-on/advent-of-code-kotlin-template)
 */
fun main() {
    val lines = readInput("Day05")
    val day05 = Day05(lines)

    day05.solvePart1()
    //TODO: day05.solvePart2() Too Slow Algorithm
}

class Day05(private val lines: List<String>) {
    private val seed2Soil = getRanges(3, 11)
    private val seed2SoilDest = seed2Soil.first
    private val seed2SoilSrc = seed2Soil.second

    private val soil2Fert = getRanges(14, 56)
    private val soil2FertDest = soil2Fert.first
    private val soil2FertSrc = soil2Fert.second

    private val fert2Water = getRanges(59, 104)
    private val fert2WaterDest = fert2Water.first
    private val fert2WaterSrc = fert2Water.second

    private val water2Light = getRanges(107, 146)
    private val water2LightDest = water2Light.first
    private val water2LightSrc = water2Light.second

    private val light2Temp = getRanges(149, 185)
    private val light2TempDest = light2Temp.first
    private val light2TempSrc = light2Temp.second

    private val temp2hum = getRanges(188, 205)
    private val temp2humDest = temp2hum.first
    private val temp2humSrc = temp2hum.second

    private val hum2Loc = getRanges(208, 249)
    private val hum2LocDest = hum2Loc.first
    private val hum2LocSrc = hum2Loc.second
    fun solvePart1() {
        val seeds = lines[0].substring(7).split(" ")

        val allDests: MutableList<Long> = mutableListOf()
        val es = MultiThreadingService(10)
        print("[")

        for (seed in seeds) {
            es.exec {
                allDests += mapSeed(seed)
                print("=")
            }
        }
        print("]")
        println("")
        es.awaitOff(120)
        allDests.min().println("Part 1 Total: {val}")
    }

    fun solvePart2() {
        val seeds = lines[0].substring(7).split(" ")

        val allDests: MutableList<Long> = mutableListOf()
        print("[")

        for (seed in seeds) {
            //print("=")
        }
        seeds.count().println()
        print("]")
        println("")
        //allDests.min().println("Part 2 Total: {val}")
    }

    fun getRanges(minLine: Int, maxLine: Int): Pair<List<LongRange>, List<LongRange>> {
        val destRanges: MutableList<LongRange> = mutableListOf()
        val srcRanges: MutableList<LongRange> = mutableListOf()

        for (i in (minLine..maxLine)) {
            val destStart = lines[i].split(" ")[0].toLong()
            val srcStart = lines[i].split(" ")[1].toLong()
            val len = lines[i].split(" ")[2].toLong()
            destRanges.add((destStart..<destStart+len))
            srcRanges.add((srcStart..<srcStart+len))
        }

        return Pair(destRanges, srcRanges)
    }

    private fun mapSeed(seed: String): Long {
        var nSeed = seed.toLong()

        for (range in seed2SoilSrc) {
            if (range.contains(nSeed)) {
                val mappingIndex = range.indexOf(nSeed)
                nSeed = seed2SoilDest[seed2SoilSrc.indexOf(range)].elementAt(mappingIndex)
                break;
            }
        }
        for (range in soil2FertSrc) {
            if (range.contains(nSeed)) {
                val mappingIndex = range.indexOf(nSeed)
                nSeed = soil2FertDest[soil2FertSrc.indexOf(range)].elementAt(mappingIndex)
                break;
            }
        }
        for (range in fert2WaterSrc) {
            if (range.contains(nSeed)) {
                val mappingIndex = range.indexOf(nSeed)
                nSeed = fert2WaterDest[fert2WaterSrc.indexOf(range)].elementAt(mappingIndex)
                break;
            }
        }
        for (range in water2LightSrc) {
            if (range.contains(nSeed)) {
                val mappingIndex = range.indexOf(nSeed)
                nSeed = water2LightDest[water2LightSrc.indexOf(range)].elementAt(mappingIndex)
                break;
            }
        }
        for (range in light2TempSrc) {
            if (range.contains(nSeed)) {
                val mappingIndex = range.indexOf(nSeed)
                nSeed = light2TempDest[light2TempSrc.indexOf(range)].elementAt(mappingIndex)
                break;
            }
        }
        for (range in temp2humSrc) {
            if (range.contains(nSeed)) {
                val mappingIndex = range.indexOf(nSeed)
                nSeed = temp2humDest[temp2humSrc.indexOf(range)].elementAt(mappingIndex)
                break;
            }
        }
        for (range in hum2LocSrc) {
            if (range.contains(nSeed)) {
                val mappingIndex = range.indexOf(nSeed)
                nSeed = hum2LocDest[hum2LocSrc.indexOf(range)].elementAt(mappingIndex)
                break;
            }
        }


        return nSeed
    }

}