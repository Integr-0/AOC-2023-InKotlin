import utils.*

/**
 * # Day 01
 * **See Also:** [Advent Of Code](http://adventofcode.com)
 *
 * **See Also:** [Template](https://github.com/kotlin-hands-on/advent-of-code-kotlin-template)
 */
fun main() {
    val srvc = MultiThreadingService(2)

    srvc.exec {
        Thread.sleep(10);
        println("A")
    }

    srvc.exec {
        println("B")
        Thread.sleep(10000)
    }

    srvc.await(10)
    println("DONE")
}
