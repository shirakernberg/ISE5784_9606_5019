package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Tube class represents a tube in 3D space.
 * It is defined by an axis ray and a radius.
 * @version 1.0
 */
public class Tube extends RadialGeometry {
    /** The axis ray of the tube */
    final private Ray axis;

    /**
     * Constructor to initialize a Tube object with a given axis ray and radius.
     * @param axis the axis ray of the tube
     * @param radius the radius of the tube
     */
    public Tube(Ray axis, double radius) {
        super(radius);
        this.axis = axis;
    }

    /**
     * @return  the axis ray of the tube.
     */
    public Ray getAxis() {
        return axis;
    }
/**
     * Returns the normal vector to the tube at a given point.
     * @param p the point on the tube
     * @return the normal vector at the given point.
     */
    @Override
    public Vector getNormal(Point p) {
        Point p0 = axis.getHead();
        Vector v = axis.getDirection();
        Vector p0_p = p.subtract(p0);
        double t = v.dotProduct(p0_p);
        if (t==0) {
            return p0_p;
        }
        Point o = p0.add(v.scale(t));
        Vector n = p.subtract(o);
        return n.normalize();
    }

    /**
     * @param ray
     * @return
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        return List.of();
    }
}
