package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry{
    final private Ray axis;

    public Tube(Ray axis,double radius) {
        super(radius);
        this.axis = axis;
    }
    public Vector getNormal(Point p) {
        return null;
    }
}
