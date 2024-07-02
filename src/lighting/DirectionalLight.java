package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Class representing a directional light source.
 */
public class DirectionalLight extends Light implements LightSource {
    private final Vector direction;

    /**
     * Constructor for DirectionalLight.
     * @param intensity the intensity of the light
     * @param direction the direction of the light
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    /**
     * Get the intensity of the light at a point.
     * @param p the point
     * @return the intensity of the light
     */
    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    /**
     * Get the direction of the light at a point.
     * @param p the point
     * @return the direction of the light
     */
    @Override
    public Vector getL(Point p) {
        return direction;
    }
}
