package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Class Triangle is the basic class representing a triangle
 */
public class Triangle extends Polygon {
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        //directions between the head of the ray and the vertices of the triangle
        Vector v1 = vertices.get(0).subtract(ray.getHead());
        Vector v2 = vertices.get(1).subtract(ray.getHead());
        Vector v3 = vertices.get(2).subtract(ray.getHead());
        //normal vectors of the triangle
        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();
        //dot products between the direction of the ray and the normal vectors of the triangle
        //check if one of them is 0
        double t1 = ray.getDirection().dotProduct(n1);
        if(isZero(t1))
            return null;
        double t2 = ray.getDirection().dotProduct(n2);
        if(isZero(t2))
            return null;
        double t3 = ray.getDirection().dotProduct(n3);
        if(isZero(t3))
            return null;
        //if the dot products are all positive or all negative then the ray intersects the triangle
        if(t1 >0 && t2 > 0 && t3 > 0 || t1<0 && t2 < 0 && t3 < 0) {
            Plane p = new Plane(vertices.get(0), vertices.get(1), vertices.get(2));
            List<Point> planeIntersections = p.findIntersections(ray);

            //if there are no intersections with the plane
            if (planeIntersections == null) {
                return null;
            }

            //arrange the intersection points by the distance from the head of the ray
            return List.of(new GeoPoint(this,planeIntersections.getFirst()));
        }
        return null;
    }


}
