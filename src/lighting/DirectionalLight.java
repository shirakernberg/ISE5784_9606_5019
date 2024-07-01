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
     * @param builder the builder object containing the parameters
     */
    private DirectionalLight(Builder builder) {
        super(builder.intensity);
        this.direction = builder.direction;
    }

    /**
     * Builder class for DirectionalLight.
     */
    public static class Builder {
        private final Color intensity;
        private final Vector direction;

        /**
         * Constructor for Builder.
         * @param intensity the intensity of the light
         * @param direction the direction of the light
         */
        public Builder(Color intensity, Vector direction) {
            this.intensity = intensity;
            this.direction = direction.normalize();
        }

        public DirectionalLight build() {
            return new DirectionalLight(this);
        }
    }

    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }
}
