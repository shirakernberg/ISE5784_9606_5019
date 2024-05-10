package primitives;

import java.util.Objects;



public class Point {
    final protected Double3 xyz;
    public static final Point ZERO= new Point(0,0,0) ;

    public Point(double x, double y, double z  ) {
        xyz= new Double3(x,y,z);
    }

    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * @param obj = object for comparison
     * @return true/false according to if they are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Point other)
                && this.xyz.equals(other.xyz) ;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(xyz);
    }

    @Override
    public String toString() {
        return "Point(" + xyz +")";
    }
    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
    }
    public double distanceSquared(Point p) {
        double x1=xyz.d1-p.xyz.d1;
        double y1=xyz.d1-p.xyz.d2;
        double z1=xyz.d3-p.xyz.d3;
        return x1*x1+y1*y1+z1*z1;
    }
    public Point add(Vector v){
        xyz.add(v.xyz);
        return new Point(xyz);
    }
    public Vector subtract(Point p){
        xyz.subtract(p.xyz);
        return new Vector(xyz);
    }
}
