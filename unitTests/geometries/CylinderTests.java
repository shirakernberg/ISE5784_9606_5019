package geometries;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

public class CylinderTests {

    @Test
    public void testGetNormal() {
        Ray axisRay = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        double radius = 1.0;
        double height = 2.0;
        Cylinder cylinder = new Cylinder(height, axisRay, radius);

        // Point on the bottom base center
        Point bottomBaseCenter = new Point(0, 0, 0);
        Vector normal = cylinder.getNormal(bottomBaseCenter);
        assertEquals(new Vector(0, 0, -1), normal, "Bad normal to bottom base center");

        // Point on the top base center
        Point topBaseCenter = new Point(0, 0, 2);
        normal = cylinder.getNormal(topBaseCenter);
        assertEquals(new Vector(0, 0, 1), normal, "Bad normal to top base center");

        // Point on the side surface
        Point sidePoint = new Point(1, 0, 1);
        normal = cylinder.getNormal(sidePoint);
        assertEquals(new Vector(1, 0, 0), normal, "Bad normal to side surface");
    }

    @Test
    void testFindGeoIntersections() {
        Ray axisRay = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        double height = 4;
        double radius = 1;
        Cylinder cy = new Cylinder(height, axisRay, radius);

        // ============ Equivalence Partitions Tests ==============
        //***equivalent to the main ray
        //TC01 from outside the cylinder (2 points)
        List<Intersectable.GeoPoint> result01 = cy.findGeoIntersectionsHelper(
                new Ray(new Point(0, 0.5, -1), new Vector(0, 0, 4)));
        assertNotNull(result01, "Ray should intersect the cylinder");
        assertEquals(2, result01.size(), "Wrong number of intersections");
        result01 = List.of(result01.get(0), result01.get(1));
        Intersectable.GeoPoint p1 = new Intersectable.GeoPoint(cy, new Point(0, 0.5, 0));
        Intersectable.GeoPoint p2 = new Intersectable.GeoPoint(cy, new Point(0, 0.5, 4));
        assertEquals(List.of(p1, p2), result01, "Wrong intersection points");

        //TC02 from inside the cylinder (1 point)
        List<Intersectable.GeoPoint> result02 = cy.findGeoIntersectionsHelper(
                new Ray(new Point(0, 0.5, 1), new Vector(0, 0, 4)));
        assertNotNull(result02, "Ray should intersect the cylinder");
        assertEquals(1, result02.size(), "Wrong number of intersections");
        Intersectable.GeoPoint p3 = new Intersectable.GeoPoint(cy, new Point(0, 0.5, 4));
        assertEquals(List.of(p3), result02, "Wrong intersection points");

        //TC03 from outside the cylinder (0 points)
        assertNull(cy.findGeoIntersectionsHelper(new Ray(new Point(0, 0, 5), new Vector(0, 0, 1))),
                "Ray should not intersect the cylinder");

        // =============== Boundary Values Tests ==================
        //***on the plane base of the cylinder
        //TC04 the ray is inside the plane
        assertNull(cy.findGeoIntersectionsHelper(new Ray(new Point(-2, 0, 0), new Vector(2, 0, 0))),
                "Ray should not intersect the cylinder");

        //TC05 the ray starts on the plane inside (1 point)
        List<Intersectable.GeoPoint> result05 = cy.findGeoIntersectionsHelper(
                new Ray(new Point(0, 0.5, 0), new Vector(0, 0, 4)));
        assertNotNull(result05, "Ray should intersect the cylinder");
        assertEquals(1, result05.size(), "Wrong number of intersections");
        Intersectable.GeoPoint p4 = new Intersectable.GeoPoint(cy, new Point(0, 0.5, 4));
        assertEquals(List.of(p4), result05, "Wrong intersection points");

        //TC06 the ray starts on the plane outside (0 points)
        assertNull(cy.findGeoIntersectionsHelper(new Ray(new Point(0, 0.5, 0), new Vector(0, 1, -1))),
                "Ray should not intersect the cylinder");
    }

}

