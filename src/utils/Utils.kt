package utils

import java.math.BigInteger
import java.security.MessageDigest
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 * @param name The Filename to Read from (Format: src/inputs/[name].txt)
 * @return All Found Lines in a List of Strings
 */
fun readInput(name: String) = Path("src/inputs/$name.txt").readLines()

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

/**
 * Hash String into **MD2**
 * @return the Hashed string
 */
fun String.md2() = BigInteger(1, MessageDigest.getInstance("MD2").digest(toByteArray())).toString(16)

/**
 * Hash String into **MD5**
 * @return the Hashed string
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

/**
 * Hash String into **SHA1**
 * @return the Hashed string
 */
fun String.sha1() = BigInteger(1, MessageDigest.getInstance("SHA-1").digest(toByteArray())).toString(16)

/**
 * Hash String into **SHA224**
 * @return the Hashed string
 */
fun String.sha224() = BigInteger(1, MessageDigest.getInstance("SHA-224").digest(toByteArray())).toString(16)

/**
 * Hash String into **SHA256**
 * @return the Hashed string
 */
fun String.sha256() = BigInteger(1, MessageDigest.getInstance("SHA-256").digest(toByteArray())).toString(16)

/**
 * Hash String into **SHA384**
 * @return the Hashed string
 */
fun String.sha384() = BigInteger(1, MessageDigest.getInstance("SHA-384").digest(toByteArray())).toString(16)

/**
 * Hash String into **SHA512**
 * @return the Hashed string
 */
fun String.sha512() = BigInteger(1, MessageDigest.getInstance("SHA-512").digest(toByteArray())).toString(16)

/**
 * Class for easier **Multithreading**
 * @param threads amount of threads to use for execution
 */
class MultiThreadingService(threads: Int) {
    val service = Executors.newFixedThreadPool(threads)

    /**
     * Executes the given **Runnable** in one of the **threads specified during the creation**
     * @param runnable the Runnable to execute
     */
    fun exec(runnable: Runnable) {
        service.execute(runnable)
    }

    /**
     * Waits for all threads to **finish** before **shutting down**
     * @param maxWait the maximum time to wait
     */
    fun await(maxWait: Int) {
        service.shutdown()
        service.awaitTermination(maxWait.toLong(), TimeUnit.SECONDS)
    }

    /**
     * Forces all threads to **stop execution immediately**
     */
    fun forceShutdown() {
        service.shutdownNow()
    }
}