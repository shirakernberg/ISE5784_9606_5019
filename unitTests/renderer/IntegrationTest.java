package renderer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.*;

import java.util.List;

/**
 * Integration tests for Camera rays and geometric bodies intersections.
 */
public class IntegrationTest {
    private Camera.Builder cameraBuilder = Camera.getBuilder()
            .setLocation(Point.ZERO)
            .setDirection(new Vector(0, 0, -1), new Vector(0, -1, 0))
            .setVpDistance(10);

    @Test
    public void testSphereIntersections() throws CloneNotSupportedException {
        Camera camera = cameraBuilder.setVpSize(3, 3).build();
        Sphere sphere = new Sphere(new Point(0, 0, -20), 5);

        System.out.println("Testing Sphere with radius 5 at (0, 0, -20)");
        int intersections = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Ray ray = camera.constructRay(3, 3, j, i);
                System.out.println("Constructed Ray: " + ray);
                List<Point> points = sphere.findIntersections(ray);
                if (points != null) {
                    intersections += points.size();
                    System.out.println("Ray: " + ray + ", Intersections: " + points.size());
                    for (Point p : points) {
                        System.out.println("Intersection Point: " + p);
                    }
                } else {
                    System.out.println("Ray: " + ray + ", Intersections: 0");
                }
            }
        }
        System.out.println("Total Intersections: " + intersections);
        assertEquals(18, intersections, "Wrong number of intersections for Sphere small radius");
    }

    @Test
    public void testPlaneIntersections() throws CloneNotSupportedException {
        Camera camera = cameraBuilder.setVpSize(3, 3).build();
        Plane plane = new Plane(new Point(0, 0, -20), new Vector(0, 0, 1));

        System.out.println("Testing Plane at (0, 0, -20) with normal (0, 0, 1)");
        int intersections = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Ray ray = camera.constructRay(3, 3, j, i);
                System.out.println("Constructed Ray: " + ray);
                List<Point> points = plane.findIntersections(ray);
                if (points != null) {
                    intersections += points.size();
                    System.out.println("Ray: " + ray + ", Intersections: " + points.size());
                    for (Point p : points) {
                        System.out.println("Intersection Point: " + p);
                    }
                } else {
                    System.out.println("Ray: " + ray + ", Intersections: 0");
                }
            }
        }
        System.out.println("Total Intersections: " + intersections);
        assertEquals(9, intersections, "Wrong number of intersections for Plane");
    }

    @Test
    public void testTriangleIntersections() throws CloneNotSupportedException {
        Camera camera = cameraBuilder.setVpSize(3, 3).build();
        Triangle triangle = new Triangle(new Point(0, 1, -20), new Point(-1, -1, -20), new Point(1, -1, -20));

        System.out.println("Testing Triangle with vertices (0, 1, -20), (-1, -1, -20), (1, -1, -20)");
        int intersections = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Ray ray = camera.constructRay(3, 3, j, i);
                System.out.println("Constructed Ray: " + ray);
                List<Point> points = triangle.findIntersections(ray);
                if (points != null) {
                    intersections += points.size();
                    System.out.println("Ray: " + ray + ", Intersections: " + points.size());
                    for (Point p : points) {
                        System.out.println("Intersection Point: " + p);
                    }
                } else {
                    System.out.println("Ray: " + ray + ", Intersections: 0");
                }
            }
        }
        System.out.println("Total Intersections: " + intersections);
        assertEquals(1, intersections, "Wrong number of intersections for Triangle");
    }
}
