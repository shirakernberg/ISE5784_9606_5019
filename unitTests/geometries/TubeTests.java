package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTests {

    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Tube t = new Tube(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 1);
        // ensure there are no exceptions
        assertDoesNotThrow(() -> t.getNormal(new Point(0, 1, 0)), "");
        // generate the test result
        Vector result = t.getNormal(new Point(0, 1, 0));
        // ensure |result| = 1
        assertEquals(1, result.length(), 0.0001, "Tube's normal is not a unit vector");
    // =============== Boundary Values Tests ==================
    // TC02: Check if the same normal is returned for different points on the tube
    Vector normalAtP1 = t.getNormal(new Point(0, 1, 0));
    Vector normalAtP2 = t.getNormal(new Point(0, -1, 0));
    assertTrue(result.equals(normalAtP1) || result.equals(normalAtP1.scale(-1)), "ERROR: different normal vectors for different points on the tube");
}
}
