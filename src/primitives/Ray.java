package primitives;

import java.util.Objects;

public class Ray {
    final private Point head;
    final private Vector direction;

    public Ray(Point head,Vector direction) {
        this.head = head;
        this.direction = direction.normalize();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return head.equals(ray.head) && direction.equals(ray.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, direction);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "head=" + head +
                ", direction=" + direction +
                '}';
    }
}
