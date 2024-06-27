package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;
import java.util.List;

/**
 * Sphere class represents a sphere in 3D space.
 * It is defined by a center point and a radius.
 */
public class Sphere extends RadialGeometry {
    /**
     * The center point of the sphere
     */
    final private Point center;

    /**
     * @return the center point of the sphere
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Constructor to initialize a Sphere object with a given center point and radius.
     * @param center the center point of the sphere
     * @param radius the radius of the sphere
     */
    public Sphere(Point center, double radius) {
        super(radius);
        this.center = center;
    }

    /**
     * Returns the normal vector to the sphere at a given point.
     * @param p the point on the sphere
     * @return the normal vector at the given point.
     */
    @Override
    public Vector getNormal(Point p) {
        Vector n = p.subtract(this.center);
        return n.normalize();
    }

    /**
     * Find intersections of a ray with the sphere.
     * @param ray the ray to find intersections with
     * @return a list of intersection points with the sphere
     */
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Vector u;
        //if the ray starts at the center of the sphere
        try {
            u = center.subtract(ray.getHead());
        } catch (IllegalArgumentException e) {
            return null;
        }

        double tm = alignZero(ray.getDirection().dotProduct(u));
        double d = alignZero(Math.sqrt(u.lengthSquared() - tm * tm));
        //if distance from the ray to the center is greater than the radius
        if (d >= radius)
            return null;

        double th = alignZero(Math.sqrt(radius * radius - d * d));
        double t1 = tm - th;
        double t2 = tm + th;

        //if there are 2 intersection points
        if(t1 > 0 && t2 > 0) {
            return List.of(new GeoPoint(this,ray.getPoint(t1)),new GeoPoint(this,ray.getPoint(t2)));
        }
        //if there is only 1 intersection point
        else if(t1 > 0) {
            return List.of(new GeoPoint(this,ray.getPoint(t1)));
        }
        else if(t2 > 0) {
            return List.of(new GeoPoint(this,ray.getPoint(t2)));
        }

        //no intersection points
        return null;
    }
}
