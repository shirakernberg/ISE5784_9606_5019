package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * abstract class for geometric shapes.
 */
public abstract class Geometry extends Intersectable {
    Color emission= Color.BLACK;

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
}
