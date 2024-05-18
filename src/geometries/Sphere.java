package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Sphere class represents a sphere in 3D space.
 * It is defined by a center point and a radius.
 */
public class Sphere extends RadialGeometry {
    /** The center point of the sphere */
    final private Point center;

    /**
     * Constructor to initialize a Sphere object with a given center point and radius.
     *
     * @param center the center point of the sphere
     * @param radius the radius of the sphere
     */
    public Sphere(Point center, double radius) {
        super(radius);
        this.center = center;
    }

    /**
     * Returns the normal vector to the sphere at a given point.
     *
     * @param p the point on the sphere
     * @return the normal vector at the given point
     */
    @Override
    public Vector getNormal(Point p) {
        // Implementation to calculate the normal vector
        return null;
    }
}
