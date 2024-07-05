package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;
import java.util.*;
import java.util.function.ToDoubleFunction;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTests {
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertDoesNotThrow(() -> new Plane(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0)), "Failed to create a plane");
        // =============== Boundary Values Tests ==================
        // TC02: Check if the exception is thrown when the points are on the same line
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(0, 0, 0), new Point(1, 0, 0), new Point(2, 0, 0)), "Constructed a plane with three points on the same line");
        // TC02: Check if the exception is thrown when the points are the same
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1, 0, 0), new Point(1, 0, 0), new Point(1, 0, 0)), "Constructed a plane with three points on the same line");

    }

    @Test
    public void testGetNormal(){
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Plane pl = new Plane(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0));
        Point p = new Point(0.5, 0.5, 0);
        assertDoesNotThrow(()-> pl.getNormal(p), "should not throw an exception");
        // ensure the point is on the plane
        Vector test= pl.getQ().subtract(p);
        double dotResult = pl.getNormal(p).dotProduct(test);
        assertEquals(0, dotResult,"Point isn't on the plane surface");
        // generate the test result
        Vector result = pl.getNormal(new Point(0, 0, 1));
        // ensure |result| = 1
        assertEquals(1, result.length(), 0.0001, "Plane's normal is not a unit vector");
    }
    @Test
    public void testFindIntersections() {
        Plane pl = new Plane(new Point(1, 0, 0), new Vector(0, 0, 1));

        // ============ Equivalence Partitions Tests ==============
        // TC01:Ray intersects the plane (1 point)
        var result = pl.findIntersections(new Ray(new Point(-2, 0.5, -1), new Vector(1, 1, 1)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(new Point(-1, 1.5, 0)), result, "Ray intersects the plane");

        // TC02:Ray does not intersect the plane (0 points)
        assertNull(pl.findIntersections(new Ray(new Point(3, 1, 0.5), new Vector(1, 1, 1))), "Ray doesnt intersect the plane");

        // =============== Boundary Values Tests ==================

        // **Group: Ray is parallel to the plane
        // TC11: Ray included in the plane (0 points)
        assertNull(pl.findIntersections(new Ray(new Point(3, 1, 0), new Vector(1, 1, 0))), "Ray in the plane");

        // TC12: Ray not included in the plane (0 points)
        assertNull(pl.findIntersections(new Ray(new Point(3, 1, 0.5), new Vector(1, 1, 0))), "Ray not included in the plane");
        // **Group: Ray is orthogonal to the plane
        //TC13: Ray starts before the plane (1 point)
        result = pl.findIntersections(new Ray(new Point(-4, 3, -2), new Vector(0, 0, 1)));
        assertEquals( 1, result.size(),"Wrong number of points");
        assertEquals(List.of(new Point(-4, 3, 0)), result,"Ray is orthogonal to the plane and starts before the plane");

        //TC14: Ray starts in the plane (0 points)
        assertNull(pl.findIntersections(new Ray(new Point(3, 1, 0), new Vector(0, 0, 1))), "Ray is orthogonal to the plane and starts in the plane");

        //TC15: Ray starts after the plane (0 point)
        assertNull(pl.findIntersections(new Ray(new Point(3, 1, 1), new Vector(0, 0, 1))), "Ray is orthogonal to the plane and starts after the plane");
        // **Group: Special cases
        //TC16:Ray is neither orthogonal nor parallel to and begins at the plane (0 options)
        assertNull(pl.findIntersections(new Ray(new Point(3, 1, 0), new Vector(0, 1, 1))), "Ray begins at the plane");
        //TC17:
        assertNull(pl.findIntersections(new Ray(new Point(1, 0, 1), new Vector(0, 1, 2))), "Ray begins at the plane");

    }
}