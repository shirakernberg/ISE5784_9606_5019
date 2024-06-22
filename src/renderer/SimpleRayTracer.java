package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

/**
 * Simple implementation of the RayTracerBase class for tracing rays in a scene.
 */
public class SimpleRayTracer extends RayTracerBase {

    /**
     * Constructor for SimpleRayTracer.
     * @param scene the scene to trace rays in
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * Traces a ray and returns the color of the closest intersection.
     * @param ray the ray to trace
     * @return the color of the closest intersection, or the background color if no intersection is found
     */
    @Override
    public Color traceRay(Ray ray) {
        // Find the closest intersection point to the ray's start
        Point closestPoint = ray.findClosestPoint(scene.geometries.findIntersections(ray));
        if (closestPoint == null) {
            // If no intersection is found, return the background color of the scene
            return scene.background;
        }
        // Calculate and return the color of the closest intersection point
        return calcColor(closestPoint);
    }


    /**
     * Calculates the color at a given point.
     * @param point the point to calculate the color for
     * @return the color at the given point
     */
    private Color calcColor(Point point) {
        // Calculate the color at the given point (currently using only ambient light)
        return scene.ambientLight.getIntensity();
    }
}
