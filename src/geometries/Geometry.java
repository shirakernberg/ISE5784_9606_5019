package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * interface for geometric shapes
 */
public interface Geometry {
    public Vector getNormal(Point p);
}
