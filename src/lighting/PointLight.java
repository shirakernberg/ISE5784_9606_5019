package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;


/**
 * Class representing a point light source.
 */
public class PointLight extends Light implements LightSource {
    private final Point position;
    private double kC = 1.0;
    private double kL = 0.0;
    private double kQ = 0.0;

    /**
     * Constructor for PointLight.
     * @param intensity the intensity of the light
     * @param position the position of the light
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * Setter for the linear attenuation factor.
     * @param kL the linear attenuation factor
     * @return the current instance (for chaining)
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * Setter for the quadratic attenuation factor.
     * @param kQ the quadratic attenuation factor
     * @return the current instance (for chaining)
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }

    /**
     * Get the intensity of the light at a point.
     * @param p the point
     * @return the intensity of the light
     */
    @Override
    public Color getIntensity(Point p) {
        double d = position.distance(p);
        double attenuation = kC + kL * d + kQ * d * d;
        return getIntensity().reduce(attenuation);
    }

    /**
     * Get the direction of the light at a point.
     * @param p the point
     * @return the direction of the light
     */
    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    /**
     * Get the distance from the light source to a specific point.
     * @param point the point to calculate the distance to
     * @return the distance to the specified point
     */
    @Override
    public double getDistance(Point point) {
        return position.distance(point);
    }
}
