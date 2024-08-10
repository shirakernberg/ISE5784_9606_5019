package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

/**
 * Tube class represents a tube in 3D space.
 * It is defined by an axis ray and a radius.
 * @version 1.0
 */
public class Tube extends RadialGeometry {
    /** The axis ray of the tube */
    final private Ray axis;

    /**
     * Constructor to initialize a Tube object with a given axis ray and radius.
     * @param axis the axis ray of the tube
     * @param radius the radius of the tube
     */
    public Tube(Ray axis, double radius) {
        super(radius);
        this.axis = axis;
    }

    /**
     * @return  the axis ray of the tube.
     */
    public Ray getAxis() {
        return axis;
    }
    /**
     * Returns the normal vector to the tube at a given point.
     * @param p the point on the tube
     * @return the normal vector at the given point.
     */
    @Override
    public Vector getNormal(Point p) {
        Point p0 = axis.getHead();
        Vector v = axis.getDirection();
        Vector p0_p = p.subtract(p0);
        double t = v.dotProduct(p0_p);
        if (t==0) {
            return p0_p;
        }
        Point o = axis.getPoint(t);
        Vector n = p.subtract(o);
        return n.normalize();
    }

    /**
     * @param ray=rsy of tube
     * @return intersections for tube
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point p0 = ray.getHead();
        Vector v = ray.getDirection();
        Point pa = this.axis.getHead();
        Vector va = this.axis.getDirection();
        double a, b, c; //coefficients for quadratic equation ax^2 + bx + c

        // a = (v-(v,va)va)^2
        // b = 2(v-(v,va)va,delP-(delP,va)va)
        // c = (delP-(delP,va)va)^2 - r^2
        // delP = p0-pa
        //(v,u) = v dot product u = vu = v*u
        //Step 1 - calculates a:
        Vector vecA = v;
        try {
            double vva = v.dotProduct(va); //(v,va)
            if (!Util.isZero(vva)) vecA = v.subtract(va.scale(vva)); //v-(v,va)va
            a = vecA.lengthSquared(); //(v-(v,va)va)^2
        } catch (IllegalArgumentException e) {
            return null; //if a=0 there are no intersections because Ray is parallel to axisRay
        }
        //Step 2 - calculates deltaP (delP), b, c:
        try {
            Vector deltaP = p0.subtract(pa); //p0-pa
            Vector deltaPMinusDeltaPVaVa = deltaP;
            double deltaPVa = deltaP.dotProduct(va); //(delP,va)va)
            if (!Util.isZero(deltaPVa)) deltaPMinusDeltaPVaVa = deltaP.subtract(va.scale(deltaPVa)); //(delP-(delP,va)va)
            b = 2 * (vecA.dotProduct(deltaPMinusDeltaPVaVa)); //2(v-(v,va)va,delP-(delP,va)va)
            c = deltaPMinusDeltaPVaVa.lengthSquared() - radius*radius; //(delP-(delP,va)va)^2 - r^2
        } catch (IllegalArgumentException e) {
            b = 0; //if delP = 0, or (delP-(delP,va)va = (0, 0, 0)
            c = -1 * radius*radius;
        }

        double discriminant=b*b-4*a*c;

        if(Util.alignZero(discriminant)<=0)
            return null;
        double t1=(-1*b+Math.sqrt(discriminant))/(2*a);
        double t2=(-1*b-Math.sqrt(discriminant))/(2*a);

        if(t1==t2)
            if(Util.alignZero(t1)>0)
                return List.of(new GeoPoint(this,ray.getPoint(t1)));
            else
                return null;
        else  if (t1 > 0 && t2 > 0) {
            Point p1=ray.getPoint((-1*b+Math.sqrt(discriminant))/(2*a));
            Point p2=ray.getPoint((-1*b-Math.sqrt(discriminant))/(2*a));
            return p1.getX()<p2.getX()?List.of(new GeoPoint(this,p1),new GeoPoint(this,p2)):List.of(new GeoPoint(this,p2),new GeoPoint(this,p1));}
        if (Util.alignZero(t1) > 0) return List.of(new GeoPoint(this,ray.getPoint(t1)));
        if (Util.alignZero(t2)>0) return List.of(new GeoPoint(this,ray.getPoint(t2)));


        return null;

    }
}

