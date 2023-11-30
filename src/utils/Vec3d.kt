package utils

import kotlin.math.sqrt

/**
 * A Class to track a Point in **3D** space
 */
class Vec3d {
    var x: Double = 0.0
    var y: Double = 0.0
    var z: Double = 0.0

    val ZERO = Vec3d(0, 0, 0)

    /**
     * A Class to track a Point in **3D** space
     * @param x X position
     * @param y Y position
     * @param z Z position
     */
    constructor(x: Double, y: Double, z: Double) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * A Class to track a Point in **3D** space
     * @param x X position
     * @param y Y position
     * @param z Z position
     */
    constructor(x: Int, y: Int, z: Int) {
        this.x = x.toDouble();
        this.y = y.toDouble();
        this.z = z.toDouble();
    }

    operator fun plus(v: Double) = Vec3d(this.x+v, this.y+v, this.z+v)
    operator fun minus(v: Double) = Vec3d(this.x-v, this.y-v, this.z-v)
    operator fun div(v: Double) = Vec3d(this.x/v, this.y/v, this.z/v)
    operator fun times(v: Double) = Vec3d(this.x*v, this.y*v, this.z*v)
    operator fun rem(v: Double) = Vec3d(this.x%v, this.y%v, this.z%v)
    operator fun plus(v: Vec3d) = Vec3d(this.x+v.x, this.y+v.y, this.z+v.z)
    operator fun minus(v: Vec3d) = Vec3d(this.x-v.x, this.y-v.y, this.z-v.z)
    operator fun div(v: Vec3d) = Vec3d(this.x/v.x, this.y/v.y, this.z/v.z)
    operator fun times(v: Vec3d) = Vec3d(this.x*v.x, this.y*v.y, this.z*v.z)
    operator fun rem(v: Vec3d) = Vec3d(this.x%v.x, this.y%v.y, this.z%v.z)

    override fun toString(): String = "$x, $y, $z"

    fun getMagnitude(): Double = sqrt(x*x + y*y + z*z)
    fun normalize(): Vec3d = this / getMagnitude()
}