package primitives;

import static primitives.Util.alignZero;

public class Vector extends Point {
    /**
     * 3 parameters ctor for vector
     * @param x=x coordinate of the vector
     * @param y=y coordinate of the vector
     * @param z=z coordinate of the vector
     */
    public Vector(double x, double y, double z) {
        this(new Double3(x,y,z));
    }

    /**
     * one double3 parameter ctor for vector
     * @param xyz=the double3 value of the vector
     */
    Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("ZERO  vector is not valid");
    }

    @Override
    public String toString() {
        return "Vector(" + super.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vector v)) return false;
        return xyz.equals(v.xyz);
    }

    /**
     * adds one vector to the other
     * @param v=the vector we wish to add
     * @return a new vector of the addition
     */
    public Vector add(Vector v) {
        return new Vector(xyz.add(v.xyz));
    }

    /**
     * multiplies a vector by a scalar
     * @param s= the scalar
     * @return a new vector after the multiplication
     */
    public Vector scale(double s) {
        return new Vector(xyz.scale(s));
    }

    /**
     * Dot Product multiplication between 2 vectors
     * @param v= the vector we will multiply with
     * @return the scalar/number resulting from the multiplication
     */
    public double dotProduct(Vector v) {
        return v.xyz.d1 * xyz.d1 + v.xyz.d2 * xyz.d2 + v.xyz.d3 * xyz.d3;
    }

    /**
     * cross product multiplication between 2 vectors
     * @param v= the second vector
     * @return the resulting vector
     */
    public Vector crossProduct(Vector v) {
        return new Vector(
                xyz.d2 * v.xyz.d3 - xyz.d3 * v.xyz.d2,
                xyz.d3 * v.xyz.d1 - xyz.d1 * v.xyz.d3,
                xyz.d1 * v.xyz.d2 - xyz.d2 * v.xyz.d1);
    }

    /**
     * calculates the length squared
     * @return the squared length
     */
    public double lengthSquared() {
        return xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;
    }

    /**
     * applies a squared root to the length
     * @return the length after the root
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * function normalizes the vector
     * @return normalized vector
     */
    public Vector normalize() {
        double len = alignZero(length());
        if (len == 0)
            throw new ArithmeticException("Cannot normalize Vector(0,0,0)");
        return new Vector(xyz.d1 / len, xyz.d2 / len, xyz.d3 / len);
    }
}
