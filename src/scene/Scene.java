package scene;

import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;
import geometries.Geometries;

import java.util.LinkedList;
import java.util.List;

import static primitives.Color.BLUE;

/**
 * Class representing a scene with various attributes like name, background color, ambient light, geometries, and lights.
 */
public class Scene {
    public String name;
    public Color background = Color.BLUE;
    public AmbientLight ambientLight = new AmbientLight(BLUE,0.5);
    public Geometries geometries = new Geometries();
    public List<LightSource> lights = new LinkedList<>();

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

    /**
     * Setter for the lights.
     * @param lights the list of lights
     * @return the scene
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
}
