package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * Abstract class representing a base ray tracer for rendering scenes.
 */
public abstract class RayTracerBase {
    protected Scene scene;

    /**
     * Constructor for RayTracerBase.
     * @param scene the scene to trace rays in
     */
    protected RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * Abstract method to trace a ray and return the color of the closest intersection.
     * @param ray the ray to trace
     * @return the color of the closest intersection
     */
    public abstract Color traceRay(Ray ray);
}

