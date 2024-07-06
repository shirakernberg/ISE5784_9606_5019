package primitives;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VectorTests {
    Vector v1         = new Vector(1, 2, 3);
    Vector v1Opposite = new Vector(-1, -2, -3);
    Vector v2         = new Vector(-2, -4, -6);
    Vector v3         = new Vector(0, 3, -2);
    Vector v4         = new Vector(1, 2, 2);

    @Test
    void testConstructor() {
        // =============== Boundary Values Tests ==================
        // Test constructor for invalid input (zero vector)
        assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0),"ERROR: zero vector throws wrong exception");
        assertThrows(IllegalArgumentException.class, () -> new Vector(Double3.ZERO),"ERROR: zero vector throws wrong exception");
    }

    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(3, v4.length(),"ERROR: length() wrong value");
    }

    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(9, v4.lengthSquared(),"ERROR: lengthSquared() wrong value");
    }

    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(-1, -2, -3), v1.add(v2), "ERROR: Vector + Vector does not work correctly");

        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class, () -> v1.add(v1Opposite), "ERROR: Vector + -itself does not throw an exception");
    }

    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(3, 6, 9), v1.subtract(v2), "ERROR: Vector - Vector does not work correctly");

        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class, () -> v1.subtract(v1), "ERROR: Vector - itself throws wrong exception");
    }

    @Test
    void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(0, v1.dotProduct(v3), "ERROR: dotProduct() for orthogonal vectors is not zero");
        assertEquals(-28, v1.dotProduct(v2), "ERROR: dotProduct() wrong value");
    }

    @Test
    void testCrossProduct() {
        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v2));

        // ============ Equivalence Partitions Tests ==============
        Vector vr = v1.crossProduct(v3);
        assertEquals(v1.length() * v3.length(), vr.length(), 0.00001, "ERROR: crossProduct() wrong result length");
        assertEquals(0, vr.dotProduct(v1), 1e-10, "ERROR: crossProduct() result is not orthogonal to its operands");
        assertEquals(0, vr.dotProduct(v3), 1e-10, "ERROR: crossProduct() result is not orthogonal to its operands");
    }

    @Test
    void testNormalize() {
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();

        // ============ Equivalence Partitions Tests ==============
        assertEquals(1, u.length(), "ERROR: the normalized vector is not a unit vector");

        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class, () -> v.crossProduct(u), "ERROR: the normalized vector is not parallel to the original one");
        assertTrue(v.dotProduct(u) > 0, "ERROR: the normalized vector is opposite to the original one");
    }
}
