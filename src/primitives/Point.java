package primitives;

import java.util.Objects;

/**
 * This class represents a point in a 3D space using three numbers (x, y, z)
 * and provides various methods to operate on these points
 */
public class Point {
    /** The coordinates of the point */
    final protected Double3 xyz;

    /** Zero point (0,0,0) */
    public static final Point ZERO= new Point(0,0,0) ;

    /**
     * Constructor to initialize Point object with given x, y, and z coordinates.
     * @param x x-coordinate value
     * @param y y-coordinate value
     * @param z z-coordinate value
     */
    public Point(double x, double y, double z  ) {
        xyz= new Double3(x,y,z);
    }

    /**
     * Constructor to initialize Point object with a Double3 object.
     * @param xyz Double3 object containing x, y, and z coordinates
     */
    Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * Checks if this point is equal to another object.
     * @param obj = object for comparison
     * @return true/false according to if they are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Point other)
                && this.xyz.equals(other.xyz) ;
    }

    /**
     * Returns a hash code value for the point.
     * @return hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(xyz);
    }

    /**
     * Returns a string representation of the point.
     * @return string representation of the point
     */
    @Override
    public String toString() {
        return "Point(" + xyz + ")";
    }

    /**
     * Calculates the distance between this point and another point.
     * @param p the other point
     * @return the distance between the two points
     */
    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
    }

    /**
     * Calculates the squared distance between this point and another point.
     * @param p the other point
     * @return the squared distance between the two points
     */
    public double distanceSquared(Point p) {
        double x1 = xyz.d1 - p.xyz.d1;
        double y1 = xyz.d2 - p.xyz.d2;
        double z1 = xyz.d3 - p.xyz.d3;
        return x1 * x1 + y1 * y1 + z1 * z1;
    }

    /**
     * Adds a vector to this point and returns a new point.
     * @param v the vector to add
     * @return a new point resulting from the addition
     */
    public Point add(Vector v) {
        return new Point(xyz.add(v.xyz));
    }

    /**
     * Subtracts another point from this point and returns a vector.
     * @param p the point to subtract
     * @return a new vector resulting from the subtraction
     */
    public Vector subtract(Point p) {
        return new Vector(xyz.subtract(p.xyz));
    }

    public double getY() {
        return xyz.d2;
    }

    public double getX() {
        return xyz.d1;
    }
}