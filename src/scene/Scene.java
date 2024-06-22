package scene;

import lighting.AmbientLight;
import primitives.Color;
import geometries.Geometries;

/**
 * Class representing a scene with various attributes like name, background color, ambient light, and geometries.
 */
public class Scene {
    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = AmbientLight.NONE;
    public Geometries geometries = new Geometries();

    /**
     * Constructor for Scene.
     * @param name the name of the scene
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * Setter for the background color.
     * @param background the background color
     * @return the scene
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Setter for the ambient light.
     * @param ambientLight the ambient light
     * @return the scene
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Setter for the geometries.
     * @param geometries the geometries
     * @return the scene
     */
public Scene setGeometries(Geometries geometries) {
    this.geometries = geometries;
    return this;
}

}
