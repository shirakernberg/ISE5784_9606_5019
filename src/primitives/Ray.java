package primitives;

import java.util.Objects;

import static primitives.Util.isZero;

/**
 * This class represents a ray in 3D space defined by a starting point and a direction vector.
 * The direction vector is always normalized.
 */
public class Ray {
    /** The starting point of the ray */
    final private Point head;

    /** The direction vector of the ray. */
    final private Vector direction;

    /**
     * Constructor to initialize a Ray object with a given head point and direction vector.
     * The direction vector is normalized.
     * @param head the starting point of the ray
     * @param direction the direction vector of the ray
     */
    public Ray(Point head, Vector direction) {
        this.head = head;
        this.direction = direction.normalize();
    }

    /**
     * @param t the distance
     * @return the ray points from a given distance
     */
    public Point getPoint(double t){
        if(isZero(t))
            return head;
        return head.add(direction.scale(t));
    }
    /**
     * @return the starting point of the ray.
     */
    public Point getHead() {
        return head;
    }
    /**
     * @return the direction of the ray.
     */
    public Vector getDirection() {
        return direction;
    }

    /**
     * Checks if this ray is equal to another object.
     * @param o the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return head.equals(ray.head) && direction.equals(ray.direction);
    }

    /**
     * Returns a hash code value for the ray.
     * @return hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(head, direction);
    }

    /**
     * Returns a string representation of the ray.
     * @return string representation of the ray
     */
    @Override
    public String toString() {
        return "Ray{" +
                "head=" + head +
                ", direction=" + direction +
                '}';
    }
}

