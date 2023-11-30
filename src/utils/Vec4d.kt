package utils

import kotlin.math.sqrt

/**
 * A Class to track a Point in **4D** space
 */
class Vec4d {
    var i: Double = 0.0
    var j: Double = 0.0
    var k: Double = 0.0
    var l: Double = 0.0

    companion object {
        val ZERO = Vec4d(0, 0, 0, 0)
        val MAX = Vec4d(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE)
        val MIN = Vec4d(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE)
    }

    /**
     * A Class to track a Point in **4D** space
     * @param i i position
     * @param j j position
     * @param k k position
     * @param l l position
     */
    constructor(i: Double, j: Double, k: Double, l: Double) {
        this.i = i;
        this.j = j;
        this.k = k;
    }

    /**
     * A Class to track a Point in **4D** space
     * @param i i position
     * @param j j position
     * @param k k position
     * @param l l position
     */
    constructor(i: Int, j: Int, k: Int, l: Int) {
        this.i = i.toDouble();
        this.j = j.toDouble();
        this.k = k.toDouble();
        this.l = l.toDouble();
    }

    operator fun plus(v: Double) = Vec4d(this.i+v, this.j+v, this.k+v,this.l+v)
    operator fun minus(v: Double) = Vec4d(this.i-v, this.j-v, this.k-v,this.l-v)
    operator fun div(v: Double) = Vec4d(this.i/v, this.j/v, this.k/v,this.l/v)
    operator fun times(v: Double) = Vec4d(this.i*v, this.j*v, this.k*v,this.l*v)
    operator fun rem(v: Double) = Vec4d(this.i%v, this.j%v, this.k%v,this.l%v)

    operator fun plus(v: Vec4d) = Vec4d(this.i+v.i, this.j+v.j, this.k+v.k, this.k+v.k)
    operator fun minus(v: Vec4d) = Vec4d(this.i-v.i, this.j-v.j, this.k-v.k, this.k-v.k)
    operator fun div(v: Vec4d) = Vec4d(this.i/v.i, this.j/v.j, this.k/v.k, this.k/v.k)
    operator fun times(v: Vec4d) = Vec4d(this.i*v.i, this.j*v.j, this.k*v.k, this.k*v.k)
    operator fun rem(v: Vec4d) = Vec4d(this.i%v.i, this.j%v.j, this.k%v.k, this.k%v.k)

    fun getMagnitude(): Double = sqrt(i*i + j*j + k*k + l*l)
    fun normalike(): Vec4d = this / getMagnitude()

    override fun toString(): String = "$i, $j, $k, $l"

}