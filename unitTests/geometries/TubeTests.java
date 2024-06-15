package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TubeTests {

    @Test
    void testGetNormal() {
        Point p = new Point(0, 1, 0);
        Tube t = new Tube(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 1);
        // TC02: The point is on the tube's surface
        double PointTest = t.getAxis().getHead().distance(p);
        assertTrue(PointTest <= t.radius, "The point is not on the tube surface.");
        // ============ Equivalence Partitions Tests ==============
        // ensure there are no exceptions
        assertDoesNotThrow(() -> t.getNormal(new Point(0, 1, 0)), "getNormal threw an exception.");
        // generate the test result
        Vector result = t.getNormal(p);
        // ensure |result| = 1
        assertEquals(1, result.length(), 0.0001, "Tube's normal is not a unit vector.");
        // =============== Boundary Values Tests ==================
        Vector test = t.getAxis().getHead().subtract(p);
        double dotResult = t.getNormal(p).dotProduct(test);
        assertTrue(dotResult != 0, "The points are parallel.");
    }

    @Test
    public void testFindIntersectionsRay() {
        Tube tb1 = new Tube(new Ray(new Point(1, 0, 0), new Vector(0, 1, 0)), 1d);
        Vector vAxis = new Vector(0, 0, 1);
        Tube tube2 = new Tube(new Ray(new Point(1, 1, 1), new Vector(0, 0, 1)), 1d);
        Ray ray;
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the tube
        ray = new Ray(new Point(-2, 0, 0), new Vector(0, 0, 1));
        assertNull(tb1.findIntersections(ray), "Expected no intersections but found some.");

        // TC02: Ray crosses the tube (2 points)
        ray = new Ray(new Point(0, 0, 0), new Vector(2, 1, 1));
        List<Point> result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected intersections but found none.");
        assertEquals(2, result.size(), "Expected 2 intersection points.");
        if (result.get(0).getY() > result.get(1).getY())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(new Point(0.4, 0.2, 0.2), new Point(2, 1, 1)), result, "Intersection points are incorrect.");

        // TC03: Ray starts within the tube and crosses the tube (1 point)
        ray    = new Ray(new Point(1, 0.5, 0.5), new Vector(2, 1, 1));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected an intersection but found none.");
        assertEquals(1, result.size(), "Expected 1 intersection point.");
        assertEquals(List.of(new Point(2, 1, 1)), result, "Intersection point is incorrect.");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line is parallel to the axis (0 points) *****************
        // TC11: Ray is inside the tube (0 points)
        ray = new Ray(new Point(0.5, 0.5, 0.5), vAxis);
        assertNull(tube2.findIntersections(ray), "Expected no intersections but found some.");

        // TC12: Ray is outside the tube
        ray = new Ray(new Point(0.5, -0.5, 0.5), vAxis);
        assertNull(tube2.findIntersections(ray), "Expected no intersections but found some.");

        // TC13: Ray is at the tube surface
        ray = new Ray(new Point(2, 1, 0.5), vAxis);
        assertNull(tube2.findIntersections(ray), "Expected no intersections but found some.");

        // TC14: Ray is inside the tube and starts against axis head
        ray = new Ray(new Point(0.5, 0.5, 1), vAxis);
        assertNull(tube2.findIntersections(ray), "Expected no intersections but found some.");

        // TC15: Ray is outside the tube and starts against axis head
        ray = new Ray(new Point(0.5, -0.5, 1), vAxis);
        assertNull(tube2.findIntersections(ray), "Expected no intersections but found some.");

        // TC16: Ray is at the tube surface and starts against axis head
        ray = new Ray(new Point(2, 1, 1), vAxis);
        assertNull(tube2.findIntersections(ray), "Expected no intersections but found some.");

        // TC17: Ray is inside the tube and starts at axis head
        ray = new Ray(new Point(1, 1, 1), vAxis);
        assertNull(tube2.findIntersections(ray), "Expected no intersections but found some.");

        // **** Group: Ray is orthogonal but does not begin against the axis head
        // *****************
        // TC21: Ray starts outside and the line is outside (0 points)
        ray = new Ray(new Point(0, 2, 2), new Vector(1, 1, 0));
        assertNull(tube2.findIntersections(ray), "Expected no intersections but found some.");

        // TC22: The line is tangent and the ray starts before the tube (0 points)
        ray = new Ray(new Point(0, 2, 2), new Vector(1, 0, 0));
        assertNull(tube2.findIntersections(ray), "Expected no intersections but found some.");

        // TC23: The line is tangent and the ray starts at the tube (0 points)
        ray = new Ray(new Point(1, 2, 2), new Vector(1, 0, 0));
        assertNull(tube2.findIntersections(ray), "Expected no intersections but found some.");

        // TC24: The line is tangent and the ray starts after the tube (0 points)
        ray = new Ray(new Point(2, 2, 2), new Vector(1, 0, 0));
        assertNull(tube2.findIntersections(ray), "Expected no intersections but found some.");

        // TC25: Ray starts before (2 points)
        ray    = new Ray(new Point(0, 0, 2), new Vector(2, 1, 0));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected intersections but found none.");
        assertEquals(2, result.size(), "Expected 2 intersection points.");
        if (result.get(0).getY() > result.get(1).getY())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(new Point(0.4, 0.2, 2), new Point(2, 1, 2)), result, "Intersection points are incorrect.");

        // TC26: Ray starts at the surface and goes inside (1 point)
        ray    = new Ray(new Point(0.4, 0.2, 2), new Vector(2, 1, 0));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected an intersection but found none.");
        assertEquals(1, result.size(), "Expected 1 intersection point.");
        assertEquals(List.of(new Point(2, 1, 2)), result, "Intersection point is incorrect.");

        // TC27: Ray starts inside (1 point)
        ray    = new Ray(new Point(1, 0.5, 2), new Vector(2, 1, 0));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected an intersection but found none.");
        assertEquals(1, result.size(), "Expected 1 intersection point.");
        assertEquals(List.of(new Point(2, 1, 2)), result, "Intersection point is incorrect.");

        // TC28: Ray starts at the surface and goes outside (0 points)
        ray    = new Ray(new Point(2, 1, 2), new Vector(2, 1, 0));
        result = tube2.findIntersections(ray);
        assertNull(result, "Expected no intersections but found some.");

        // TC29: Ray starts after
        ray    = new Ray(new Point(4, 2, 2), new Vector(2, 1, 0));
        result = tube2.findIntersections(ray);
        assertNull(result, "Expected no intersections but found some.");

        // TC30: Ray starts before and crosses the axis (2 points)
        ray    = new Ray(new Point(1, -1, 2), new Vector(0, 1, 0));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected intersections but found none.");
        assertEquals(2, result.size(), "Expected 2 intersection points.");
        if (result.get(0).getY() > result.get(1).getY())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(new Point(1, 0, 2), new Point(1, 2, 2)), result, "Intersection points are incorrect.");

        // TC31: Ray starts at the surface and goes inside and crosses the axis
        ray    = new Ray(new Point(1, 0, 2), new Vector(0, 1, 0));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected an intersection but found none.");
        assertEquals(1, result.size(), "Expected 1 intersection point.");
        assertEquals(List.of(new Point(1, 2, 2)), result, "Intersection point is incorrect.");

        // TC32: Ray starts inside and the line crosses the axis (1 point)
        ray    = new Ray(new Point(1, 0.5, 2), new Vector(0, 1, 0));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected an intersection but found none.");
        assertEquals(1, result.size(), "Expected 1 intersection point.");
        assertEquals(List.of(new Point(1, 2, 2)), result, "Intersection point is incorrect.");

        // TC33: Ray starts at the surface and goes outside and the line crosses the
        // axis (0 points)
        ray    = new Ray(new Point(1, 2, 2), new Vector(0, 1, 0));
        result = tube2.findIntersections(ray);
        assertNull(result, "Expected no intersections but found some.");

        // TC34: Ray starts after and crosses the axis (0 points)
        ray    = new Ray(new Point(1, 3, 2), new Vector(0, 1, 0));
        result = tube2.findIntersections(ray);
        assertNull(result, "Expected no intersections but found some.");

        // TC35: Ray starts at the axis
        ray    = new Ray(new Point(1, 1, 2), new Vector(0, 1, 0));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected an intersection but found none.");
        assertEquals(1, result.size(), "Expected 1 intersection point.");
        assertEquals(List.of(new Point(1, 2, 2)), result, "Intersection point is incorrect.");

        // **** Group: Ray is orthogonal to axis and begins against the axis head
        // *****************
        // TC41: Ray starts outside and the line is outside
        ray = new Ray(new Point(0, 2, 1), new Vector(1, 1, 0));
        assertNull(tube2.findIntersections(ray), "Expected no intersections but found some.");

        // TC42: The line is tangent and the ray starts before the tube
        ray = new Ray(new Point(0, 2, 1), new Vector(1, 0, 0));
        assertNull(tube2.findIntersections(ray), "Expected no intersections but found some.");

        // TC43: The line is tangent and the ray starts at the tube
        ray = new Ray(new Point(1, 2, 1), new Vector(1, 0, 0));
        assertNull(tube2.findIntersections(ray), "Expected no intersections but found some.");

        // TC44: The line is tangent and the ray starts after the tube
        ray = new Ray(new Point(2, 2, 2), new Vector(1, 0, 0));
        assertNull(tube2.findIntersections(ray), "Expected no intersections but found some.");

        // TC45: Ray starts before
        ray    = new Ray(new Point(0, 0, 1), new Vector(2, 1, 0));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected intersections but found none.");
        assertEquals(2, result.size(), "Expected 2 intersection points.");
        if (result.get(0).getY() > result.get(1).getY())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(new Point(0.4, 0.2, 1), new Point(2, 1, 1)), result, "Intersection points are incorrect.");

        // TC46: Ray starts at the surface and goes inside
        ray    = new Ray(new Point(0.4, 0.2, 1), new Vector(2, 1, 0));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected an intersection but found none.");
        assertEquals(1, result.size(), "Expected 1 intersection point.");
        assertEquals(List.of(new Point(2, 1, 1)), result, "Intersection point is incorrect.");

        // TC47: Ray starts inside
        ray    = new Ray(new Point(1, 0.5, 1), new Vector(2, 1, 0));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected an intersection but found none.");
        assertEquals(1, result.size(), "Expected 1 intersection point.");
        assertEquals(List.of(new Point(2, 1, 1)), result, "Intersection point is incorrect.");

        // TC48: Ray starts at the surface and goes outside
        ray    = new Ray(new Point(2, 1, 1), new Vector(2, 1, 0));
        result = tube2.findIntersections(ray);
        assertNull(result, "Expected no intersections but found some.");

        // TC49: Ray starts after
        ray    = new Ray(new Point(4, 2, 1), new Vector(2, 1, 0));
        result = tube2.findIntersections(ray);
        assertNull(result, "Expected no intersections but found some.");

        // TC50: Ray starts before and goes through the axis head
        ray    = new Ray(new Point(1, -1, 1), new Vector(0, 1, 0));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected intersections but found none.");
        assertEquals(2, result.size(), "Expected 2 intersection points.");
        if (result.get(0).getY() > result.get(1).getY())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(new Point(1, 0, 1), new Point(1, 2, 1)), result, "Intersection points are incorrect.");

        // TC51: Ray starts at the surface and goes inside and goes through the axis
        // head
        ray    = new Ray(new Point(1, 0, 1), new Vector(0, 1, 0));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected an intersection but found none.");
        assertEquals(1, result.size(), "Expected 1 intersection point.");
        assertEquals(List.of(new Point(1, 2, 1)), result, "Intersection point is incorrect.");

        // TC52: Ray starts inside and the line goes through the axis head
        ray    = new Ray(new Point(1, 0.5, 1), new Vector(0, 1, 0));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected an intersection but found none.");
        assertEquals(1, result.size(), "Expected 1 intersection point.");
        assertEquals(List.of(new Point(1, 2, 1)), result, "Intersection point is incorrect.");

        // TC53: Ray starts at the surface and the line goes outside and goes through
        // the axis head
        ray    = new Ray(new Point(1, 2, 1), new Vector(0, 1, 0));
        result = tube2.findIntersections(ray);
        assertNull(result, "Expected no intersections but found some.");

        // TC54: Ray starts after and the line goes through the axis head
        ray    = new Ray(new Point(1, 3, 1), new Vector(0, 1, 0));
        result = tube2.findIntersections(ray);
        assertNull(result, "Expected no intersections but found some.");

        // TC55: Ray starts at the axis head
        ray    = new Ray(new Point(1, 1, 1), new Vector(0, 1, 0));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected an intersection but found none.");
        assertEquals(1, result.size(), "Expected 1 intersection point.");
        assertEquals(List.of(new Point(1, 2, 1)), result, "Intersection point is incorrect.");

        // **** Group: Ray's line is neither parallel nor orthogonal to the axis and
        // *****************
        // begins against axis head
        Point p0 = new Point(0, 2, 1);
        // TC61: Ray's line is outside the tube
        ray    = new Ray(p0, new Vector(1, 1, 1));
        result = tube2.findIntersections(ray);
        assertNull(result, "Expected no intersections but found some.");

        // TC62: Ray's line crosses the tube and begins before
        ray    = new Ray(p0, new Vector(2, -1, 1));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected intersections but found none.");
        assertEquals(2, result.size(), "Expected 2 intersection points.");
        if (result.get(0).getY() > result.get(1).getY())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(new Point(2, 1, 2), new Point(0.4, 1.8, 1.2)), result, "Intersection points are incorrect.");

        // TC63: Ray's line crosses the tube and begins at surface and goes inside
        ray    = new Ray(new Point(0.4, 1.8, 1), new Vector(2, -1, 1));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected an intersection but found none.");
        assertEquals(1, result.size(), "Expected 1 intersection point.");
        assertEquals(List.of(new Point(2, 1, 1.8)), result, "Intersection point is incorrect.");

        // TC64: Ray's line crosses the tube and begins inside
        ray    = new Ray(new Point(1, 1.5, 1), new Vector(2, -1, 1));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected an intersection but found none.");
        assertEquals(1, result.size(), "Expected 1 intersection point.");
        assertEquals(List.of(new Point(2, 1, 1.5)), result, "Intersection point is incorrect.");

        // TC65: Ray's line crosses the tube and begins at the axis head
        ray    = new Ray(new Point(1, 1, 1), new Vector(0, 1, 1));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected an intersection but found none.");
        assertEquals(1, result.size(), "Expected 1 intersection point.");
        assertEquals(List.of(new Point(1, 2, 2)), result, "Intersection point is incorrect.");

        // TC66: Ray's line crosses the tube and begins at surface and goes outside
        ray    = new Ray(new Point(2, 1, 1), new Vector(2, -1, 1));
        result = tube2.findIntersections(ray);
        assertNull(result, "Expected no intersections but found some.");

        // TC67: Ray's line is tangent and begins before
        ray    = new Ray(p0, new Vector(0, 2, 1));
        result = tube2.findIntersections(ray);
        assertNull(result, "Expected no intersections but found some.");

        // TC68: Ray's line is tangent and begins at the tube surface
        ray    = new Ray(new Point(1, 2, 1), new Vector(1, 0, 1));
        result = tube2.findIntersections(ray);
        assertNull(result, "Expected no intersections but found some.");

        // TC69: Ray's line is tangent and begins after
        ray    = new Ray(new Point(2, 2, 1), new Vector(1, 0, 1));
        result = tube2.findIntersections(ray);
        assertNull(result, "Expected no intersections but found some.");

        // **** Group: Ray's line is neither parallel nor orthogonal to the axis and
        // *****************
        // does not begin against axis head
        double sqrt2      = Math.sqrt(2);
        double denomSqrt2 = 1 / sqrt2;
        double value1     = 1 - denomSqrt2;
        double value2     = 1 + denomSqrt2;

        // TC71: Ray crosses the tube and the axis
        ray    = new Ray(new Point(0, 0, 2), new Vector(1, 1, 1));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected intersections but found none.");
        assertEquals(2, result.size(), "Expected 2 intersection points.");
        if (result.get(0).getY() > result.get(1).getY())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(new Point(value1, value1, 2 + value1), new Point(value2, value2, 2 + value2)), result,
                "Intersection points are incorrect.");

        // TC72: Ray crosses the tube and the axis head
        ray    = new Ray(new Point(0, 0, 0), new Vector(1, 1, 1));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected intersections but found none.");
        assertEquals(2, result.size(), "Expected 2 intersection points.");
        if (result.get(0).getY() > result.get(1).getY())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(new Point(value1, value1, value1), new Point(value2, value2, value2)), result,
                "Intersection points are incorrect.");

        // TC73: Ray begins at the surface and goes inside
        ray    = new Ray(new Point(value1, value1, 2 + value1), new Vector(1, 0, 1));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected an intersection but found none.");
        assertEquals(1, result.size(), "Expected 1 intersection point.");
        assertEquals(List.of(new Point(value2, value1, 2 + value2)), result, "Intersection point is incorrect.");

        // TC74: Ray begins at the surface and goes inside crossing the axis
        ray    = new Ray(new Point(value1, value1, 2 + value1), new Vector(1, 1, 1));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected an intersection but found none.");
        assertEquals(1, result.size(), "Expected 1 intersection point.");
        assertEquals(List.of(new Point(value2, value2, 2 + value2)), result, "Intersection point is incorrect.");

        // TC75: Ray begins at the surface and goes inside crossing the axis head
        ray    = new Ray(new Point(value1, value1, value1), new Vector(1, 1, 1));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected an intersection but found none.");
        assertEquals(1, result.size(), "Expected 1 intersection point.");
        assertEquals(List.of(new Point(value2, value2, value2)), result, "Intersection point is incorrect.");

        // TC76: Ray begins inside and the line crosses the axis
        ray    = new Ray(new Point(0.5, 0.5, 2.5), new Vector(1, 1, 1));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected an intersection but found none.");
        assertEquals(1, result.size(), "Expected 1 intersection point.");
        assertEquals(List.of(new Point(value2, value2, 2 + value2)), result, "Intersection point is incorrect.");

        // TC77: Ray begins inside and the line crosses the axis head
        ray    = new Ray(new Point(0.5, 0.5, 0.5), new Vector(1, 1, 1));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected an intersection but found none.");
        assertEquals(1, result.size(), "Expected 1 intersection point.");
        assertEquals(List.of(new Point(value2, value2, value2)), result, "Intersection point is incorrect.");

        // TC78: Ray begins at the axis
        ray    = new Ray(new Point(1, 1, 3), new Vector(1, 1, 1));
        result = tube2.findIntersections(ray);
        assertNotNull(result, "Expected an intersection but found none.");
        assertEquals(1, result.size(), "Expected 1 intersection point.");
        assertEquals(List.of(new Point(value2, value2, 2 + value2)), result, "Intersection point is incorrect.");

        // TC79: Ray begins at the surface and goes outside
        ray    = new Ray(new Point(2, 1, 2), new Vector(2, 1, 1));
        result = tube2.findIntersections(ray);
        assertNull(result, "Expected no intersections but found some.");

        // TC80: Ray begins at the surface and goes outside and the line crosses the
        // axis
        ray    = new Ray(new Point(value2, value2, 2 + value2), new Vector(1, 1, 1));
        result = tube2.findIntersections(ray);
        assertNull(result, "Expected no intersections but found some.");

        // TC81: Ray begins at the surface and goes outside and the line crosses the
        // axis head
        ray    = new Ray(new Point(value2, value2, value2), new Vector(1, 1, 1));
        result = tube2.findIntersections(ray);
        assertNull(result, "Expected no intersections but found some.");
    }
}
