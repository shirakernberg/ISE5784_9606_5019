package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;
import java.util.*;
import java.util.function.ToDoubleFunction;

import static org.junit.jupiter.api.Assertions.*;

class SphereTests {

    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Sphere s = new Sphere(new Point(0, 0, 0), 1);
        // ensure point is on the sphere
        double test = s.getCenter().distance(new Point(0, 0, 0));
        assertTrue(test <= s.radius, "the point is not on the sphere surface");
        assertDoesNotThrow(() -> s.getNormal(new Point(0, 1, 0)), "");
        // generate the test result
        Vector result = s.getNormal(new Point(0, 1, 0));
        // ensure |result| = 1
        assertEquals(1, result.length(), 0.0001, "Sphere's normal is not a unit vector");
    }

    private final Point  p001 = new Point(0, 0, 1);
    private final Point  p100 = new Point(1, 0, 0);
    private final Vector v001 = new Vector(0, 0, 1);
    @Test
    public void testFindIntersections() {
        Sphere            sphere      = new Sphere(p100, 1d);

        final Point       gp1         = new Point(0.0651530771650466, 0.355051025721682, 0);
        final Point       gp2         = new Point(1.53484692283495, 0.844948974278318, 0);
        final var         exp         = List.of(gp1, gp2);

        final Vector      v310        = new Vector(3, 1, 0);
        final Vector      v110        = new Vector(1, 1, 0);

        final Point       p01         = new Point(-1, 0, 0);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(p01, v110)), "Ray's line out of sphere");

        final var result1 = sphere.findIntersections(new Ray(p01, v310)).stream().sorted(Comparator
                .comparingDouble(p -> p.distance(p01))).toList();
        // TC02: Ray starts before and crosses the sphere (2 points)
        assertEquals(2, result1.size(), "Wrong number of points");
        assertEquals(exp, result1, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        assertEquals(2, result1.size(), "Wrong number of points");
        assertEquals(List.of(gp1,gp2), result1,"Ray in sphere");

        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(1, 1, 0))), "Ray's line out of sphere");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 point)
        assertEquals(2, result1.size(),"Wrong number of points");
        assertEquals(List.of(gp1,gp2), result1,"Ray in sphere");
        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(0.6, 0.8, 0))), "Ray's line out of sphere");
        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        assertEquals(2, result1.size(),"Wrong number of points");
        assertEquals(List.of(gp1,gp2), result1,"Ray in sphere");

        // TC14: Ray starts at sphere and goes inside (1 point)
        assertEquals( 2, result1.size(),"Wrong number of points");
        assertEquals(List.of(gp1,gp2), result1,"Ray in sphere");
        // TC15: Ray starts inside (1 point)
        assertEquals(List.of(gp1,gp2), result1,"Ray start inside");
        // TC16: Ray starts at the center (1 point)
        assertEquals(List.of(gp1,gp2), result1,"Ray start center");
        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1, 0, 0))), "Ray start at sphere and goes outside");

        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(1, 0, 0))), "Ray start after sphere");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(2, 0, -1), new Vector(0, 0, 1))), "Ray start before tangent point");
        // TC20: Ray starts at the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(0, 0, 1))), "Ray start at tangent point");
        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(2, 0, 1), new Vector(0, 0, 1))), "Ray start after tangent point");
        // **** Group: Special cases
        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(sphere.findIntersections(new Ray(new Point(-2, 0, 0), new Vector(0, 0, 1))), "Ray's line is outside, ray is orthogonal to ray start to sphere's center line");

    }
}

