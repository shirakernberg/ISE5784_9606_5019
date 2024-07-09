package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * abstract class for geometric shapes.
 */
public abstract class Geometry extends Intersectable {
    Color emission= Color.BLACK;

    Material material = new Material();

    /**
     * getter for emission
     * @return emission
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * setter for emission
     * @param emission=emission
     * @return emission
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * Returns the normal vector to the shape at a given point.
     * @param p the point on the shape where the normal is to be calculated
     * @return the normal vector at the given point
     */
    public abstract Vector getNormal(Point p);


    /**
     * getter for material
     * @return material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * setter for material
     * @param material=material
     * @return material
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

}


