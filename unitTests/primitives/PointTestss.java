package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTestss {
    Point  p1         = new Point(1, 2, 3);
    Point  p2         = new Point(2, 4, 6);
    Point  p3         = new Point(2, 4, 5);
    Vector v1         = new Vector(1, 2, 3);
    Vector v2         = new Vector(-2, -4, -6);
    Vector v1Opposite = new Vector(-1, -2, -3);

    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(0, p1.distance(p1), "ERROR: point distance to itself is not zero");
        assertEquals(0,p1.distance(p3) - 3,"ERROR: distance between points to itself is wrong") ;
        assertEquals(0,p3.distance(p1) - 3,"ERROR: distance between points to itself is wrong");
    }

    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(0, p1.distanceSquared(p1),"ERROR: point squared distance to itself is not zero");
        assertEquals(0, p1.distanceSquared(p3) - 9, "ERROR: squared distance between points is wrong");
        assertEquals(0,p3.distanceSquared(p1) - 9, "ERROR: squared distance between points is wrong");
    }

    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(p2,p1.add(v1),"ERROR: (point + vector) = other point does not work correctly");
        assertEquals(Point.ZERO, p1.add(v1Opposite), "ERROR: (point + vector) = center of coordinates does not work correctly");
    }

    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(v1,p2.subtract(p1),  "ERROR: (point2 - point1) does not work correctly");

        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1), "ERROR: (point - itself) throws wrong exception" );
    }
}




