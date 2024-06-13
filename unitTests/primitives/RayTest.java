package primitives;

import org.junit.jupiter.api.Test;

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
}