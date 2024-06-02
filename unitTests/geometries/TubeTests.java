package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTests {

    @Test
    void testGetNormal() {
        Point p = new Point(0, 1, 0);
        Tube t = new Tube(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 1);
        // TC02: The point is on the tube's surface
        double PointTest= t.getAxis().getHead().distance(p);
        assertTrue(PointTest <= t.radius, "the point is not on the tube surface");
        // ============ Equivalence Partitions Tests ==============
        // ensure there are no exceptions
        assertDoesNotThrow(() -> t.getNormal(new Point(0, 1, 0)), "");
        // generate the test result
        Vector result = t.getNormal(p);
        // ensure |result| = 1
        assertEquals(1, result.length(), 0.0001, "Tube's normal is not a unit vector");
        // =============== Boundary Values Tests ==================
       Vector test= t.getAxis().getHead().subtract(p);
        double dotResult = t.getNormal(p).dotProduct(test);
        assertTrue(dotResult!=0, "the points are parallel");
    }
}
