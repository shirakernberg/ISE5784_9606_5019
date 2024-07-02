package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Class representing a spotlight source.
 */
public class SpotLight extends PointLight {
    private final Vector direction;

    /**
     * Constructor for SpotLight.
     * @param intensity the intensity of the light
     * @param position the position of the light
     * @param direction the direction of the light
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     * Get the intensity of the light at a point.
     * @param p the point
     * @return the intensity of the light
     */
    @Override
    public Color getIntensity(Point p) {
        Color pointIntensity = super.getIntensity(p);
        double projection = direction.dotProduct(getL(p));
        return pointIntensity.scale(Math.max(0, projection));
    }

    /**
     * Get the direction of the light at a point.
     * @param p the point
     * @return the direction of the light
     */
    @Override
    public Vector getL(Point p) {
        return super.getL(p);
    }
}
