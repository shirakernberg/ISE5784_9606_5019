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
     * @param builder the builder object containing the parameters
     */
    private SpotLight(Builder builder) {
        super(builder);
        this.direction = builder.direction.normalize();
    }

    /**
     * Builder class for SpotLight.
     */
    public static class Builder extends PointLight.Builder {
        private final Vector direction;

        /**
         * Constructor for Builder.
         * @param intensity the intensity of the light
         * @param position the position of the light
         * @param direction the direction of the light
         */
        public Builder(Color intensity, Point position, Vector direction) {
            super(intensity, position);
            this.direction = direction;
        }

        @Override
        public SpotLight build() {
            return new SpotLight(this);
        }
    }

    @Override
    public Color getIntensity(Point p) {
        Color pointIntensity = super.getIntensity(p);
        double projection = direction.dotProduct(getL(p));
        return pointIntensity.scale(Math.max(0, projection));
    }

    @Override
    public Vector getL(Point p) {
        return super.getL(p);
    }
}
