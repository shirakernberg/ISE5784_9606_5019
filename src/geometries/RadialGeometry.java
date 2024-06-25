package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * RadialGeometry is an abstract class representing geometric shapes with a radius.
 * It implements the Geometry interface
 */
public abstract class RadialGeometry extends Geometry {
    /** The radius of the geometric shape */
    final protected double radius;

    /**
     * Constructor to initialize RadialGeometry with a given radius.
     * @param radius the radius of the geometric shape
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }
}
