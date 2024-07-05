package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTests {

    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============

        Triangle tri = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        Vector right = new Vector(sqrt3, sqrt3, sqrt3);
        Vector nor = tri.getNormal(new Point(0, 0, 1));
        assertTrue(nor.equals(right) || nor.equals(right.scale(-1)), "vector isn't normal");
    }

    @Test
    public void testFindIntersections() {
        Triangle triangle = new Triangle(new Point(-1, 3, 0), new Point(-1, 0, 0), new Point(2, 0, 0));

        // ============ Equivalence Partitions Tests ==============
        // TC01:Ray inside triangle (1 point)
        var result = triangle.findIntersections(new Ray(new Point(-1, 8, -5), new Vector(1, -7, 5)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(new Point(0, 1, 0)), result, "Ray intersects the triangle");

        //TC02:Ray outside against edge (0 points)
        assertNull(triangle.findIntersections(new Ray(new Point(-3, -3, -1), new Vector(1, 5, 1))), "Ray not included in the triangle");

        //TC03:Ray outside against vertex (0 points)
        assertNull(triangle.findIntersections(new Ray(new Point(-3, -3, -1), new Vector(1, 2, 1))), "Ray not included in the triangle");
        // =============== Boundary Values Tests ==================

        //TC11:Ray on edge (0 points)
        assertNull(triangle.findIntersections(new Ray(new Point(-2, -3, -1), new Vector(1, 4, 1))), "Ray not included in the triangle");

        //TC12:Ray in vertex (0 points)
        assertNull(triangle.findIntersections(new Ray(new Point(-2, -3, -1), new Vector(1, 6, 1))), "Ray not included in the triangle");

        //TC13: Ray on edge's continuation (0 points)
        assertNull(triangle.findIntersections(new Ray(new Point(-2, -3, -1), new Vector(-1, 8, 1))), "Ray not included in the triangle");
    }
}

