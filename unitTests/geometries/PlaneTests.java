package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

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
    }

    @Test
    public void testGetNormal(){
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Plane pl = new Plane(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0));
        Point p = new Point(0.5, 0.5, 0);
        assertDoesNotThrow(()-> pl.getNormal(p), "Exception");
        // generate the test result
        Vector result = pl.getNormal(new Point(0, 0, 1));
        // ensure |result| = 1
        assertEquals(1, result.length(), 0.0001, "Plane's normal is not a unit vector");
    }
}