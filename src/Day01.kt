import utils.*

/**
 * # Day 01
 * **See Also:** [Advent Of Code](http://adventofcode.com)
 *
 * **See Also:** [Template](https://github.com/kotlin-hands-on/advent-of-code-kotlin-template)
 */
fun main() {
    val service = MultiThreadingService(2)

    service.exec {
        Thread.sleep(10)
        println("A")
    }

    service.exec {
        println("B")
        Thread.sleep(10000)
    }

    service.await(10)
    println("DONE")
}
