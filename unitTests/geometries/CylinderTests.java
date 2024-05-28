package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTests {

    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Cylinder c = new Cylinder(1, new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 1);
        // ensure there are no exceptions
        assertDoesNotThrow(() -> c.getNormal(new Point(0, 1, 0)), "");
        // generate the test result
        Vector result = c.getNormal(new Point(0, 1, 0));
        // ensure |result| = 1
        assertEquals(1, result.length(), 0.0001, "Cylinder's normal is not a unit vector");
        // =============== Boundary Values Tests ==================
        // TC02: Check if the same normal is returned for different points on the cylinder
        Vector normalAtP1 = c.getNormal(new Point(0, 1, 0));
        Vector normalAtP2 = c.getNormal(new Point(0, -1, 0));
        assertTrue(result.equals(normalAtP1) || result.equals(normalAtP1.scale(-1)), "ERROR: different normal vectors for different points on the cylinder");
        assertTrue(result.equals(normalAtP2) || result.equals(normalAtP2.scale(-1)), "ERROR: different normal vectors for different points on the cylinder");
    }
}