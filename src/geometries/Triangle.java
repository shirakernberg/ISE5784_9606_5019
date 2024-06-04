package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class Triangle is the basic class representing a triangle
 */
public class Triangle extends Polygon {
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }
    public List<Point> findIntersections(Ray ray){

        return null;}


}
