package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * This class represents a plane in 3D space.
 * It is defined by a point on the plane and a normal vector.
 */
public class Plane implements Geometry {
    /** A point on the plane. */
    final private Point q;

    /** The normal vector of the plane */
    final private Vector normal;

    /**
     * Constructor to initialize a Plane object with a given point and normal vector.
     * The normal vector is normalized.
     *
     * @param q the point on the plane
     * @param normal the normal vector of the plane
     */
    public Plane(Point q, Vector normal) {
        this.q = q;
        this.normal = normal.normalize();
    }

    /**
     * Constructor to initialize a Plane object with three points on the plane.
     *
     * @param p1 the first point on the plane
     * @param p2 the second point on the plane
     * @param p3 the third point on the plane
     */
    public Plane(Point p1, Point p2, Point p3) {
        q=p1;
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        this.normal = v1.crossProduct(v2).normalize();
    }

    /**
     * Returns the normal vector of the plane.
     *
     * @return the normal vector
     */
    public Vector getNormal(){//////////
        return normal;
    }

    /**
     * Returns the normal vector of the plane at a given point.
     * This method normalizes the normal vector.
     *
     * @param p the point on the plane
     * @return the normalized normal vector at the given point
     */
    public Vector getNormal(Point p){ //////////
        return normal.normalize();
    }

}
