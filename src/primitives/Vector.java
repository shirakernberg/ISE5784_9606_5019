package primitives;

/**
 * Class Vector is the basic class representing a vector of Euclidean geometry
 * in Cartesian 3-Dimensional coordinate system.

 */
public class Vector extends Point {
    /**
     * X axis unit vector
     */
    public static final Vector X = new Vector(1, 0, 0);
    /**
     * Y axis unit vector
     */
    public static final Vector Y = new Vector(0, 1, 0);
    /**
     * Z axis unit vector
     */
    public static final Vector Z = new Vector(0, 0, 1);
    /**
     * X axis opposite unit vector
     */
    public static final Vector MINUS_X = new Vector(-1, 0, 0);
    /**
     * Y axis opposite unit vector
     */
    public static final Vector MINUS_Y = new Vector(0, -1, 0);
    /**
     * Z axis opposite unit vector
     */
    public static final Vector MINUS_Z = new Vector(0, 0, -1);

    /**
     * Vector constructor by 3 coordinate values
     *
     * @param x coordinate value
     * @param y coordinate value
     * @param z coordinate value
     * @throws IllegalArgumentException if all the coordinates are zero (or close to
     *                                  zero)
     */
    public Vector(double x, double y, double z) {
        this(new Double3(x, y, z));
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Zero vector is not allowed");
    }

    /**
     * Vector constructor by coordinate values triad
     *
     * @param coords - coordinate values' triad
     */
    public Vector(Double3 coords) {
        super(coords);
        if (coords.equals(Double3.ZERO))
            throw new IllegalArgumentException("Zero vector is not allowed");
    }

    /**
     * Operation of addition of this vector (v&#x20D7;&#x2081;) and another vector
     * (v&#x20D7;&#x2082;)
     *
     * @param v other vector
     * @return new vector: v&#x20D7;&#x2081;+v&#x20D7;&#x2082;
     */
    @Override
    public Vector add(Vector v) {
        return new Vector(xyz.add(v.xyz));
    }

    /**
     * Calculates multiplying of this vector by a scalar <i>a</i>
     *
     * @param scalar the scalar <i>a</i>
     * @return new vector: <i>a</i>&sdot;v&#x20D7;
     */
    public Vector scale(double scalar) {
        return new Vector(xyz.scale(scalar));
    }

    /**
     * Calculates scalar vector multiplication (dot-Product) of this
     * (v&#x20D7;&#x2081;) and another (v&#x20D7;&#x2082;) vectors
     *
     * @param v other vector
     * @return number: v&#x20D7;&#x2081;&sdot;v&#x20D7;&#x2082;
     */
    public double dotProduct(Vector v) {
        return xyz.d1 * v.xyz.d1 + xyz.d2 * v.xyz.d2 + xyz.d3 * v.xyz.d3;
    }

    /**
     * Vector multiplication (cross-Product) of this (v&#x20D7;&#x2081;) and
     * another
     * (v&#x20D7;&#x2082;) vectors
     *
     * @param v other vector
     * @return number: v&#x20D7;&#x2081;&#10799;v&#x20D7;&#x2082;
     */
    public Vector crossProduct(Vector v) {
        return new Vector(//
                xyz.d2 * v.xyz.d3 - v.xyz.d2 * xyz.d3, //
                xyz.d3 * v.xyz.d1 - v.xyz.d3 * xyz.d1, //
                xyz.d1 * v.xyz.d2 - v.xyz.d1 * xyz.d2);
    }

    /**
     * Calculate squared vector length
     *
     * @return |v&#x20D7;|&#xB2;
     */
    public double lengthSquared() {
        return dotProduct(this);
    }

    /**
     * Calculate vector length
     *
     * @return |v&#x20D7;|
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Create unit vector of the same direction (normalized vector)
     *
     * @return new normalized vector
     */
    public Vector normalize() {
        return scale(1 / length());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj instanceof Vector && super.equals(obj);
    }

    @Override
    public String toString() {
        return "v" + super.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
