package primitives;

/**
 * Class representing the material properties of a geometric object,
 * used for calculating light reflection and refraction.
 */
public class Material {
    private Double3 kD = Double3.ZERO; // Diffuse coefficient
    private Double3 kS = Double3.ZERO; // Specular coefficient
    private Double3 kT = Double3.ZERO; // Transparency coefficient
    private Double3 kR = Double3.ZERO; // Reflection coefficient
    private int nShininess = 0;        // Shininess level

    /**
     * Default constructor for Material.
     */
    public Material() {
    }

    /**
     * Getter for the diffuse coefficient.
     * @return the diffuse coefficient
     */
    public Double3 getKd() {
        return kD;
    }

    /**
     * Getter for the specular coefficient.
     * @return the specular coefficient
     */
    public Double3 getKs() {
        return kS;
    }

    /**
     * Getter for the transparency coefficient.
     * @return the transparency coefficient
     */
    public Double3 getKt() {
        return kT;
    }

    /**
     * Getter for the reflection coefficient.
     * @return the reflection coefficient
     */
    public Double3 getKr() {
        return kR;
    }

    /**
     * Getter for the shininess level.
     * @return the shininess level
     */
    public int getShininess() {
        return nShininess;
    }

    /**
     * Setter for the diffuse coefficient with Double3 parameter.
     * @param kD the diffuse coefficient
     * @return the current instance of Material (for chaining)
     */
    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Setter for the diffuse coefficient with double parameter.
     * @param kD the diffuse coefficient
     * @return the current instance of Material (for chaining)
     */
    public Material setKd(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * Setter for the specular coefficient with Double3 parameter.
     * @param kS the specular coefficient
     * @return the current instance of Material (for chaining)
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Setter for the specular coefficient with double parameter.
     * @param kS the specular coefficient
     * @return the current instance of Material (for chaining)
     */
    public Material setKs(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * Setter for the transparency coefficient with Double3 parameter.
     * @param kT the transparency coefficient
     * @return the current instance of Material (for chaining)
     */
    public Material setKt(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * Setter for the transparency coefficient with double parameter.
     * @param kT the transparency coefficient
     * @return the current instance of Material (for chaining)
     */
    public Material setKt(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    /**
     * Setter for the reflection coefficient with Double3 parameter.
     * @param kR the reflection coefficient
     * @return the current instance of Material (for chaining)
     */
    public Material setKr(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * Setter for the reflection coefficient with double parameter.
     * @param kR the reflection coefficient
     * @return the current instance of Material (for chaining)
     */
    public Material setKr(double kR) {
        this.kR = new Double3(kR);
        return this;
    }

    /**
     * Setter for the shininess level.
     * @param nShininess the shininess level
     * @return the current instance of Material (for chaining)
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
