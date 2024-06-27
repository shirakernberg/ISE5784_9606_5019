package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * Class representing ambient light for scenes.
 */
public class AmbientLight {
    private final Color intensity;

    /**
     * Constructor for AmbientLight.
     * @param I_A the intensity of the ambient light
     * @param k_A the attenuation coefficient
     */
    public AmbientLight(Color I_A, Double3 k_A) {
        this.intensity = I_A.scale(k_A);
    }

    /**
     * Constructor for AmbientLight.
     * @param I_A the intensity of the ambient light
     * @param k_A the attenuation coefficient
     */
    public AmbientLight(Color I_A, double k_A) {
        this.intensity = I_A.scale(k_A);
    }

    /**
     * NONE represents no ambient light, which is black.
     */
    public static AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /**
     * Getter for the intensity.
     * @return the intensity of the ambient light
     */
    public Color getIntensity() {
        return intensity;
    }
}