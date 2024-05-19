package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * interface for geometric shapes.
 */
public interface Geometry {
    /**
     * Returns the normal vector to the shape at a given point.
     *
     * @param p the point on the shape where the normal is to be calculated
     * @return the normal vector at the given point
     */
    public Vector getNormal(Point p);
}
