package renderer;

import geometries.Geometry;
import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * this class represents a simple ray tracer
 */
public class SimpleRayTracer extends RayTracerBase {
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K = Double3.ONE;

    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     *traces the ray to find the color of the closest point
     * @param ray the ray to trace
     * @return color of closest point
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }

    /**
     * calculates the color of the intersection point
     * @param geopoint intersection point
     * @param ray intersecting ray
     * @return color at intersection point
     */
    private Color calcColor(GeoPoint geopoint, Ray ray) {
        return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * helper func to calcColor that executes the calculation
     * @param intersection intersection point of ray and point
     * @param ray intersecting ray
     * @param level of recursion
     * @param k trancperacy/reflection indicator
     * @return color at intersection point
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(intersection, ray, k);
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
    }

    /**
     * calculates the effects on the color
     * @param gp intersection GeoPoint of the geometry with the ray
     * @param ray intersecting ray
     * @param k trancperacy/reflection indicator
     * @return the color at the point with the effects
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
        Geometry geometry = gp.geometry;
        Point point = gp.point;

        Color color = geometry.getEmission();
        Vector v = ray.getDirection();
        Vector n = geometry.getNormal(point);
        double nv = alignZero(n.dotProduct(v));

        if (isZero(nv))
            return color;

        Material material = geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(point).normalize();
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0 && unshaded(gp,l,n,lightSource)) { // sign(nl) == sign(nv)
                Double3 ktr = transparency(gp, lightSource, l, n);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    Color Li = lightSource.getIntensity(point).scale(ktr);
                    color = color.add(
                            Li.scale(calcDiffusive(material, nl)),
                            Li.scale(calcSpecular(material, n, l, v, nl))
                    );
                }
            }
        }
        return color;
    }

    /**
     * calculates the diffusion of the geometry
     * @param material of the geometry
     * @param nl = dot product of the light and the geometry normal
     * @return the calculated diffusion
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.getKd().scale(Math.abs(nl));
    }

    /**
     * calculates the speculation of the geometry
     * @param material of the geometry
     * @param n normal of the geometry
     * @param l= normal of light source direction and the GeoPoint
     * @param v direction on the camera ray
     * @param nl=dot product of the light and the geometry normal
     * @return the calculated speculation
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, Vector v, double nl) {
        Vector r = l.subtract(n.scale(nl).scale(2)).normalize();
        double max = Math.max(0, -v.dotProduct(r));
        double maxNs = Math.pow(max, material.getShininess());
        return material.getKs().scale(maxNs);
    }

    /**
     * checks if the point is shadowed
     * @param gp the point were checking
     * @param l light direction
     * @param n normal at point
     * @param lightSource source of light
     * @return if the point id shaded or not
     */
    private boolean unshaded(GeoPoint gp, Vector l, Vector n, LightSource lightSource) {
        Vector backVector = l.scale(-1); // from point to light source
        Ray backRay = new Ray(gp.point, backVector, n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(backRay);
        if (intersections == null) {
            return true;
        }
        double distanceToLightSource = lightSource.getDistance(gp.point);
        for (GeoPoint intersection : intersections) {
            if (intersection.geometry.getMaterial().getKt().equals(Double3.ZERO)) {
                return true;
            }
            double current = intersection.point.distance(backRay.getHead());
            if (current < distanceToLightSource) {
                return false;
            }
        }
        return true;
    }

    /**
     * creates a new "broken" ray from the intersection point with the light direction
     * @param gp intersection point
     * @param ray original ray
     * @param n normal at point
     * @return new rau from the intersection point with the light direction
     */
    private Ray constructRefractedRay(GeoPoint gp, Ray ray, Vector n) {
        Vector refractedVector = ray.getDirection(); // from intersection to next geometry
        return new Ray(gp.point, refractedVector, n);
    }

    /**
     * creates a new rau from the intersection point by transparency
     * @param gp intersection point
     * @param ray  original ray
     * @param n normal at point
     * @return new ray in rays direction by transparency
     */
    private Ray constructReflectedRay(GeoPoint gp, Ray ray, Vector n) {
        Vector v = ray.getDirection();
        double nv = alignZero(v.dotProduct(n));
        Vector r = v.subtract(n.scale(2d * nv)).normalize();
        return new Ray(gp.point, r, n);
    }

    /**
     * find the closest intersections
     * @param ray the ray to intersect
     * @return the closest intersection points
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = this.scene.geometries.findGeoIntersections(ray);
        if (intersections == null) return null;
        return ray.findClosestGeoPoint(intersections);
    }

    /**
     * returns the color with global effects
     * @param gp point of which we want the color
     * @param ray ray that intersects
     * @param level of recursion
     * @param k trancperacy/reflection indicator
     * @return the color with global effects
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Geometry geometry = gp.geometry;
        Point point = gp.point;
        Material mat = geometry.getMaterial();
        Vector n = geometry.getNormal(point);
        Double3 kr = mat.getKr();
        Double3 kkr = k.product(kr);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K)) {
            Ray reflectedRay = constructReflectedRay(gp, ray, n);
            color = color.add(calcGlobalEffect(reflectedRay, level, kkr, kr));
        }
        Double3 kt = mat.getKt();
        Double3 kkt = k.product(kt);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K)) {
            Ray refractedRay = constructRefractedRay(gp, ray, n);
            color = color.add(calcGlobalEffect(refractedRay, level, kkt, kt));
        }
        return color;
    }

    /**
     * helps calcGlobalEffects calculate
     * @param ray ray that intersects
     * @param level of recursion
     * @param kkx k*kx => intensity indicator
     * @param kx light break indicator
     * @return calculates colors of the intersection points
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 kkx, Double3 kx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null) ? scene.background : calcColor(gp, ray, level - 1, kkx).scale(kx);
    }

    /**
     * calculates the transparency
     * @param geopoint point of intersection
     * @param light light source
     * @param l light direction vector
     * @param n normal of the geometry at point
     * @return calculated transparency
     */
    private Double3 transparency(GeoPoint geopoint, LightSource light, Vector l, Vector n) {
        Geometry geometry = geopoint.geometry;
        Point point = geopoint.point;

        Vector backVector = l.scale(-1); // from point to light source
        Ray backRay = new Ray(point, backVector, n);
        double lightDistance = light.getDistance(point);
        var intersections = this.scene.geometries.findGeoIntersections(backRay);
        if (intersections == null) return Double3.ONE;
        Double3 ktr = Double3.ONE;
        for (GeoPoint gp : intersections) {
            if (alignZero(point.distance(point) - lightDistance) <= 0) {
                ktr = ktr.product(geometry.getMaterial().getKt());
                if (ktr.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;
            }
        }
        return ktr;
    }

}

