package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Tube class represents a tube in 3D space.
 * It is defined by an axis ray and a radius.
 *
 * @version 1.0
 */
public class Tube extends RadialGeometry {
    /** The axis ray of the tube */
    final private Ray axis;

    /**
     * Constructor to initialize a Tube object with a given axis ray and radius.
     *
     * @param axis the axis ray of the tube
     * @param radius the radius of the tube
     */
    public Tube(Ray axis, double radius) {
        super(radius);
        this.axis = axis;
    }

    /**
     * Returns the normal vector to the tube at a given point.
     *
     * @param p the point on the tube
     * @return the normal vector at the given point
     */
    @Override
    public Vector getNormal(Point p) {
        // Implementation to calculate the normal vector
        return null;
    }
}
