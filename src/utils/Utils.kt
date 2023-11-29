package utils

import java.math.BigInteger
import java.security.MessageDigest
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
 * Hash String into MD2
 */
fun String.md2() = BigInteger(1, MessageDigest.getInstance("MD2").digest(toByteArray())).toString(16)

/**
 * Hash String into MD5
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

/**
 * Hash String into SHA1
 */
fun String.sha1() = BigInteger(1, MessageDigest.getInstance("SHA-1").digest(toByteArray())).toString(16)

/**
 * Hash String into SHA224
 */
fun String.sha224() = BigInteger(1, MessageDigest.getInstance("SHA-224").digest(toByteArray())).toString(16)

/**
 * Hash String into SHA256
 */
fun String.sha256() = BigInteger(1, MessageDigest.getInstance("SHA-256").digest(toByteArray())).toString(16)

/**
 * Hash String into SHA384
 */
fun String.sha384() = BigInteger(1, MessageDigest.getInstance("SHA-384").digest(toByteArray())).toString(16)

/**
 * Hash String into SHA512
 */
fun String.sha512() = BigInteger(1, MessageDigest.getInstance("SHA-512").digest(toByteArray())).toString(16)
