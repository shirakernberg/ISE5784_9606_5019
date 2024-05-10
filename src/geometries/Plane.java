package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
    final private Point q;
    final private Vector normal;

    public Plane(Point q, Vector normal) {
        this.q = q;
        this.normal = normal.normalize();
    }
    public Plane(Point p1, Point p2, Point p3) {///////////
        q=p1;
        this.normal=null;
    }
    public Vector getNormal(){//////////
        return normal;
    }
    public Vector getNormal(Point p){ //////////
        return normal.normalize();
    }
}
