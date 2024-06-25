package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;

public abstract class Intersectable {


    /**
     * The function looks for intersection points between a basic or a
    * composite geometry and a given ray. The function returns null if there are no
    * intersections
    * @param ray the ray to intersect a geometry/geometries
    * @return list of intersection points
    */
    public List<Point> findIntersections(Ray ray) {
    //public final List<Point> findIntersections(Ray ray) {
      //  List<GeoPoint> geoList = findGeoIntersections(ray);
        //return geoList == null ? null //
          //      : geoList.stream().map(gp -> gp.point).toList();
            return null;
    }

    /**
     * helper function
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        /**
         * constructor
         * @param geometry=geometry shape
         * @param point=point
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint geoPoint)) return false;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }

        @Override
        public int hashCode() {
            return Objects.hash(geometry, point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
        /*
        public List<GeoPoint> findGeoIntersections(Ray ray){
            findGeoIntersectionsHelper(ray);
            return null;
        }

        protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        }*/
    }
}
