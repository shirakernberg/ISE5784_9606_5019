package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.Point;
import primitives.Ray;

public class Geometries extends Intersectable {
    //list of geometries
    private LinkedList<Intersectable> geometriesList = null;

    /**
     * Default constructor.
     */
    public Geometries() {

    }

    /**
     * Constructor to initialize a Geometries object with a given list of geometries.
     *
     * @param geometries the geometries to add
     */
    public Geometries(Intersectable... geometries)
    {
        add(geometries);
    }

    /**
     * Add geometries to the list of geometries.
     *
     * @param geometries the geometries to add
     */
    public void add(Intersectable... geometries)
    {
        if(geometriesList == null)
            geometriesList = new LinkedList<Intersectable>();

        for(Intersectable geometry : geometries)
        {
            geometriesList.add(geometry);
        }
    }

    /**
     * Find intersections of a ray with the geometries.
     *
     * @param ray the ray to find intersections with
     * @return a list of intersection points with the geometries
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = null;

        // Iterate over each geometry and find intersections
        for (Intersectable geometry : geometriesList) {
            List<Point> tempIntersections = geometry.findIntersections(ray);
            if (tempIntersections != null) {
                if (intersections == null) {
                    intersections = new LinkedList<>();
                }
                intersections.addAll(tempIntersections);
            }
        }

        // Return null if no intersections found, otherwise return the list of intersections
        return intersections == null ? null : intersections;

    }

}