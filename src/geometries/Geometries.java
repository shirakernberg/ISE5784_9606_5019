package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.Point;
import primitives.Ray;
/**
 * This class represents a collection of geometries in 3D space.
 * It extends the Intersectable class.
 */
public class Geometries extends Intersectable {
    //list of geometries
    private LinkedList<Intersectable> geometriesList = new LinkedList<>();

    /**
     * Deשfault constructor.
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
     * @param ray the ray to find intersections with
     * @return a list of intersection points with the geometries
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray){
        List<GeoPoint> intersections = null;

        // Iterate over each geometry and find intersections
        for (Intersectable geometry : geometriesList) {
            List<GeoPoint> tempIntersections = geometry.findGeoIntersectionsHelper(ray);
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