package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static primitives.Util.alignZero;


/**
 * Class representing a spotlight source
 */
public class SpotLight extends PointLight {
    private final Vector direction;
    public double narrowBeam;
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
        double cos = alignZero(direction.dotProduct(getL(p)));
        return narrowBeam!= 1?
                super.getIntensity(p).scale(Math.pow(Math.max(0,cos), narrowBeam)) :
                super.getIntensity(p).scale(Math.max(0, cos));
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


    /**
     * Get the distance from the light source to a specific point.
     * @param narrowBeam the point to calculate the distance to
     * return the distance to the specified point (infinity for directional light)
     */
    public SpotLight setNarrowBeam(double narrowBeam) {
        this.narrowBeam = narrowBeam;
        return this;
    }
}
