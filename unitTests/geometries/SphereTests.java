package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTests {

    @Test
     public void testGetNormal(){
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
}

