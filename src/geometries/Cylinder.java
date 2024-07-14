package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * This class represents a cylinder in 3D space, defined by a height, an axis ray, and a radius.
 * It extends the Tube class
 */
public class Cylinder extends Tube {
    /** The height of the cylinder */
    final private double height;
    Ray axisRay= getAxis();

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
    public double getHeight() {
        return this.height;
    }

    @Override
    public boolean equals(Object obj) {//checks if equals
        return (obj instanceof Cylinder) &&
                super.equals(obj) && this.height == ((Cylinder) obj).height;
    }

    @Override
    public Vector getNormal(Point point) {
        double distanceBase1 = point.distance(axisRay.getHead());
        Vector axisVec = axisRay.getDirection();

        // the point is P0
        if (isZero(distanceBase1))
            return axisRay.getDirection().scale(-1).normalize();
        Point centerBase2 = axisRay.getHead().add(axisVec.scale(height));
        double distanceBase2 = centerBase2.distance(point);
        // point is on the center of the far side from P0
        if (isZero(distanceBase2))
            return axisVec.normalize();
            // point is on the close side to P0
        else if (distanceBase1 < radius && distanceBase2 > distanceBase1)
            return axisVec.scale(-1).normalize();
            // point is on the far side from P0
        else if (distanceBase2 < radius && distanceBase1 > distanceBase2)
            return axisVec.normalize();
        else {
            double t = axisRay.getDirection().dotProduct(point.subtract(axisRay.getHead()));
            Point O = axisRay.getHead().add(axisRay.getDirection().scale(t));
            Vector v = point.subtract(O);
            return v.normalize();
        }
    }



    /**
     * find intersection points between ray and 3D cylinder
     * @param ray ray towards the sphere
     * @return immutable list containing 0/1/2 intersection points as {@link GeoPoint}s objects
     */

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> result = new LinkedList<>();
        Vector va = axisRay.getDirection();
        Point p1 = axisRay.getHead();
        Point p2 = p1.add(axisRay.getDirection().scale(this.height));

        Plane plane1 = new Plane(p1, axisRay.getDirection()); // get plane of bottom base
        List<GeoPoint> result2 = plane1.findGeoIntersections(ray); // intersections with bottom's plane

        if (result2 != null) {
            // Add all intersections of bottom's plane that are in the base's boundaries
            for (GeoPoint point : result2) {
                if (point.point.equals(p1)) { // to avoid vector ZERO
                    result.add(new GeoPoint(this, point.point));
                }
                // checks that point is inside the base
                else if ((point.point.subtract(p1).dotProduct(point.point.subtract(p1)) < this.radius * this.radius)) {
                    result.add(new GeoPoint(this, point.point));
                }
            }
        }

        List<GeoPoint> result1 = super.findGeoIntersectionsHelper(ray); // get intersections for tube

        if (result1 != null) {
            // Add all intersections of tube that are in the cylinder's boundaries
            for (GeoPoint point : result1) {
                if (va.dotProduct(point.point.subtract(p1)) > 0 && va.dotProduct(point.point.subtract(p2)) < 0) {
                    result.add(new GeoPoint(this, point.point));
                }
            }
        }

        Plane plane2 = new Plane(p2, axisRay.getDirection()); // get plane of top base
        List<GeoPoint> result3 = plane2.findGeoIntersections(ray); // intersections with top's plane

        if (result3 != null) {
            for (GeoPoint point : result3) {
                if (point.point.equals(p2)) { // to avoid vector ZERO
                    result.add(new GeoPoint(this, point.point));
                }
                // Formula that checks that point is inside the base
                else if ((point.point.subtract(p2).dotProduct(point.point.subtract(p2)) < this.radius * this.radius)) {
                    result.add(new GeoPoint(this, point.point));
                }
            }
        }

        if (result.size() > 0) {
            return result;
        }

        return null;
    }

}
