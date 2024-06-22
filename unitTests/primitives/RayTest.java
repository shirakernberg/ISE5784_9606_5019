package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void testGetPoint() {
        Ray ray = new Ray(new Point(1, 2, 3), new Vector(0, 0, 1));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Positive distance
        assertEquals(new Point(1, 2, 4), ray.getPoint(1), "Wrong point for positive distance");

        // TC02: Negative distance
        assertEquals(new Point(1, 2, 2), ray.getPoint(-1), "Wrong point for negative distance");

        // =============== Boundary Values Tests ==================

        // TC03: Zero distance
        assertEquals(new Point(1, 2, 3), ray.getPoint(0), "Wrong point for zero distance");
    }

    @Test
    public void testFindClosestPoint() {
        Ray ray = new Ray(new Point(0, 0, 0), new Vector(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: The middle point is the closest to the head of the ray
        List<Point> points = List.of(new Point(1, 1, 1), new Point(0.5, 0, 0), new Point(2, 0, 0));
        assertEquals(new Point(0.5, 0, 0), ray.findClosestPoint(points), "Wrong closest point");

        // =============== Boundary Values Tests ==================

        // TC02: The list is empty
        assertNull(ray.findClosestPoint(List.of()), "Empty list should return null");

        // TC03: The first point is the closest
        points = List.of(new Point(0.5, 0, 0), new Point(1, 1, 1), new Point(2, 0, 0));
        assertEquals(new Point(0.5, 0, 0), ray.findClosestPoint(points), "Wrong closest point");

        // TC04: The last point is the closest
        points = List.of(new Point(1, 1, 1), new Point(2, 0, 0), new Point(0.5, 0, 0));
        assertEquals(new Point(0.5, 0, 0), ray.findClosestPoint(points), "Wrong closest point");
    }
}