package primitives;

import static primitives.Util.alignZero;

public class Vector extends Point {

    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("error received trivial vector");
    }

    public Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("error received trivial vector");
    }

    @Override
    public String toString() {
        return "Vector(" + super.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vector v)) return false;
        return xyz.equals(v.xyz);
    }

    public Vector add(Vector v) {
        return new Vector(xyz.add(v.xyz));
    }

    public Vector scale(double s) {
        return new Vector(xyz.scale(s));
    }

    public double dotProduct(Vector v) {
        return v.xyz.d1 * xyz.d1 + v.xyz.d2 * xyz.d2 + v.xyz.d3 * xyz.d3;
    }
    public Vector crossProduct(Vector v) {
    return new Vector(
            xyz.d2 * v.xyz.d3 - xyz.d3 * v.xyz.d2,
            xyz.d3*v.xyz.d1-xyz.d1*v.xyz.d3,
            xyz.d1*v.xyz.d2-xyz.d2*v.xyz.d1);
    }
    public double lengthSquared(){
        return xyz.d1*xyz.d1 + xyz.d2*xyz.d2+xyz.d3*xyz.d3;
    }
    public double length(){
        return Math.sqrt(lengthSquared());
    }
    public Vector normalize() {
        double len = alignZero(length());
        if (len == 0)
            throw new ArithmeticException("Cannot normalize Vector(0,0,0)");
        return new Vector(xyz.d1/len, xyz.d2/len, xyz.d3/len);
    }
}