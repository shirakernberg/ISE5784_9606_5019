package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;


/**
 * Interface for different light sources in the scene.
 */
public interface LightSource {

    /**
     * Get the intensity of the light at a specific point.
     * @param p the point at which the intensity is being calculated
     * @return the color intensity at the specified point
     */
    public Color getIntensity(Point p);

    /**
     * Get the direction of the light at a specific point.
     * @param p the point at which the direction is being calculated
     * @return the vector direction of the light at the specified point
     */
    public Vector getL(Point p);

    /**
     * Get the distance from the light source to a specific point.
     * @param point the point to calculate the distance to
     * @return the distance to the specified point
     */
    public double getDistance(Point point);
}
