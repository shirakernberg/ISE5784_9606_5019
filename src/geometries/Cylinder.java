package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube{
    final private double height;
    public Cylinder(double height,Ray axis, double radius) {
        super(axis, radius);
        this.height = height;
    }
    /*
     * @Override
     *     public Vector getNormal(Point p) {
     *         return null;
     *     }
     */
}
