package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Basic ray tracer implementation that calculates the color of rays in a scene.
 */
public class SimpleRayTracer extends RayTracerBase {
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double DELTA = 0.1;
    private static final Double3 INITIAL_K = Double3.ONE;

    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }

    private Color calcColor(GeoPoint geopoint, Ray ray) {
        return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }

    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(intersection, ray, k);
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
    }

    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDirection();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return color;

        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point).normalize();
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sign(nv)
                Double3 ktr = transparency(gp, lightSource, l, n);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    Color Li = lightSource.getIntensity(gp.point).scale(ktr);
                    color = color.add(
                            Li.scale(calcDiffusive(material, nl)),
                            Li.scale(calcSpecular(material, n, l, v, nl))
                    );
                }
            }
        }
        return color;
    }

    private Double3 calcDiffusive(Material material, double nl) {
        return material.getKd().scale(Math.abs(nl));
    }

    private Double3 calcSpecular(Material material, Vector n, Vector l, Vector v, double nl) {
        Vector r = l.subtract(n.scale(nl).scale(2)).normalize();
        double max = Math.max(0, -v.dotProduct(r));
        double maxNs = Math.pow(max, material.getShininess());
        return material.getKs().scale(maxNs);
    }

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

    private Ray constructRefractedRay(GeoPoint gp, Ray ray, Vector n) {
        Vector refractedVector = ray.getDirection(); // from intersection to next geometry
        return new Ray(gp.point, refractedVector, n);
    }

    private Ray constructReflectedRay(GeoPoint gp, Ray ray, Vector n) {
        Vector v = ray.getDirection();
        double nv = alignZero(v.dotProduct(n));
        Vector r = v.subtract(n.scale(2d * nv)).normalize();
        return new Ray(gp.point, r, n);
    }

    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = this.scene.geometries.findGeoIntersections(ray);
        if (intersections == null) return null;
        return ray.findClosestGeoPoint(intersections);
    }

    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Material mat = gp.geometry.getMaterial();
        Vector n = gp.geometry.getNormal(gp.point);
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

    private Color calcGlobalEffect(Ray ray, int level, Double3 kkx, Double3 kx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null) ? scene.background : calcColor(gp, ray, level - 1, kkx).scale(kx);
    }

    private Double3 transparency(GeoPoint geopoint, LightSource light, Vector l, Vector n) {
        Vector backVector = l.scale(-1); // from point to light source
        Ray backRay = new Ray(geopoint.point, backVector, n);
        double lightDistance = light.getDistance(geopoint.point);
        var intersections = this.scene.geometries.findGeoIntersections(backRay);
        if (intersections == null) return Double3.ONE;
        Double3 ktr = Double3.ONE;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
                ktr = ktr.product(gp.geometry.getMaterial().getKt());
                if (ktr.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;
            }
        }
        return ktr;
    }
}
