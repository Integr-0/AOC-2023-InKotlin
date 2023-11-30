package utils

import kotlin.math.sqrt

/**
 * A Class to track a Point in 2D space
 */
class Vec2d {
    var x: Double = 0.0
    var y: Double = 0.0

    companion object {
        val ZERO = Vec2d(0, 0)
        val MAX = Vec2d(Double.MAX_VALUE, Double.MAX_VALUE)
        val MIN = Vec2d(Double.MIN_VALUE, Double.MIN_VALUE)
    }

    /**
     * A Class to track a Point in **2D** space
     * @param x X position
     * @param y Y position
     */
    constructor(x: Double, y: Double) {
        this.x = x;
        this.y = y;
    }

    /**
     * A Class to track a Point in **2D** space
     * @param x X position
     * @param y Y position
     */
    constructor(x: Int, y: Int) {
        this.x = x.toDouble();
        this.y = y.toDouble();
    }

    operator fun plus(v: Double) = Vec2d(this.x+v, this.y+v)
    operator fun minus(v: Double) = Vec2d(this.x-v, this.y-v)
    operator fun div(v: Double) = Vec2d(this.x/v, this.y/v)
    operator fun times(v: Double) = Vec2d(this.x*v, this.y*v)
    operator fun rem(v: Double) = Vec2d(this.x%v, this.y%v)

    operator fun plus(v: Vec3d) = Vec2d(this.x+v.x, this.y+v.y)
    operator fun minus(v: Vec3d) = Vec2d(this.x-v.x, this.y-v.y)
    operator fun div(v: Vec3d) = Vec2d(this.x/v.x, this.y/v.y)
    operator fun times(v: Vec3d) = Vec2d(this.x*v.x, this.y*v.y)
    operator fun rem(v: Vec3d) = Vec2d(this.x%v.x, this.y%v.y)

    fun getMagnitude(): Double = sqrt(x*x + y*y)
    fun normalize(): Vec2d = this / getMagnitude()

    override fun toString(): String = "$x, $y"
}