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
     * @param builder the builder object containing the parameters
     */
    PointLight(Builder builder) {
        super(builder.intensity);
        this.position = builder.position;
        this.kC = builder.kC;
        this.kL = builder.kL;
        this.kQ = builder.kQ;
    }

    /**
     * Builder class for PointLight.
     */
    public static class Builder {
        private final Color intensity;
        private final Point position;
        private double kC = 1.0;
        private double kL = 0.0;
        private double kQ = 0.0;

        /**
         * Constructor for Builder.
         * @param intensity the intensity of the light
         * @param position the position of the light
         */
        public Builder(Color intensity, Point position) {
            this.intensity = intensity;
            this.position = position;
        }

        public Builder setKC(double kC) {
            this.kC = kC;
            return this;
        }

        public Builder setKL(double kL) {
            this.kL = kL;
            return this;
        }

        public Builder setKQ(double kQ) {
            this.kQ = kQ;
            return this;
        }

        public PointLight build() {
            return new PointLight(this);
        }
    }

    @Override
    public Color getIntensity(Point p) {
        double d = position.distance(p);
        double attenuation = kC + kL * d + kQ * d * d;
        return getIntensity().reduce((int) attenuation);
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }
}
