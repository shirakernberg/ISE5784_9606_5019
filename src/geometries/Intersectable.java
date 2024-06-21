package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

public interface Intersectable {


    /**
     * The function looks for intersection points between a basic or a
    * composite geometry and a given ray. The function returns null if there are no
    * intersections
    * @param ray the ray to intersect a geometry/geometries
    * @return list of intersection points
    */
    public default List<Point> findIntersections(Ray ray) {
    //public final List<Point> findIntersections(Ray ray) {
      //  List<GeoPoint> geoList = findGeoIntersections(ray);
        //return geoList == null ? null //
          //      : geoList.stream().map(gp -> gp.point).toList();
            return null;
    }

}
