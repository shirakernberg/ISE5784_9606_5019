package lighting;

import primitives.Color;


/**
 * Abstract class for different types of light sources in the scene.
 */
abstract class Light {
    /**
     * The intensity of the light source.
     */
    protected Color intensity;

    /**
     * Constructor to initialize the intensity of the light source.
     * @param intensity the intensity of the light
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Get the intensity of the light.
     * @return the intensity as Color
     */
    public Color getIntensity() {
        return intensity;
    }
}
