package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * This class represents a cylinder in 3D space, defined by a height, an axis ray, and a radius.
 * It extends the Tube class
 */
public class Cylinder extends Tube {
    /** The height of the cylinder */
    final private double height;
    /**
     * Constructor to initialize a Cylinder object with a given height, axis ray, and radius.
     *
     * @param height the height of the cylinder
     * @param axis the axis ray of the cylinder
     * @param radius the radius of the cylinder
     */
    public Cylinder(double height,Ray axis, double radius) {
        super(axis, radius);
        this.height = height;
    }
    /*
     * @Override
     *     public Vector getNormal(Point p) {
     *         return null;
     *     }
     */
}
