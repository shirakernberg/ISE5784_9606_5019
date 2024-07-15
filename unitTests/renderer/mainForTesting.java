package renderer;

import primitives.*;
import geometries.*;
import scene.Scene;
import lighting.*;
import renderer.*;

import static java.awt.Color.*;

public class mainForTesting {
    public static void main(String[] args) {

        Scene scene = new Scene("Test scene")
                .setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        Camera camera = Camera.getBuilder()
                .setLocation(new Point(30, 20, 1000))
                .setDirection(new Vector(0, 0, -1), new Vector(0, -1, 0))
                .setVpSize(500, 500)
                .setVpDistance(1000)
                .setImageWriter(new ImageWriter("output_image", 500, 500))
                .setRayTracer(new SimpleRayTracer(scene))
                .build();

        // Walls

//                new Plane(new Point(0, 0, -500), new Vector(0, 0, 1))
//                        .setEmission(new Color(java.awt.Color.WHITE))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
//                new Plane(new Point(-250, 0, 0), new Vector(1, 0, 0))
//                        .setEmission(new Color(java.awt.Color.RED))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
//                new Plane(new Point(250, 0, 0), new Vector(-1, 0, 0))
//                        .setEmission(new Color(java.awt.Color.GREEN))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
//                new Plane(new Point(0, -250, 0), new Vector(0, 1, 0))
//                        .setEmission(new Color(java.awt.Color.WHITE))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
//                new Plane(new Point(0, 250, 0), new Vector(0, -1, 0))
//                        .setEmission(new Color(java.awt.Color.WHITE))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
//                new Plane(new Point(0, 0, 1000), new Vector(0, 0, -1))
//                        .setEmission(new Color(java.awt.Color.WHITE))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100))
        scene.geometries.add(
                new Sphere(new Point(0,0,0),20d).setEmission(new Color(GREEN)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(0,20,0),20d).setEmission(new Color(GREEN)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(0,40,0),20d).setEmission(new Color(GREEN)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(40,50,0),25d).setEmission(new Color(PINK)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30))
        ,       new Sphere(new Point(-40,50,0),25d).setEmission(new Color(PINK)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(40,70,0),29d).setEmission(new Color(BLUE)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30))
                ,new Sphere(new Point(-40,70,0),29d).setEmission(new Color(BLUE)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30))


        );

//        // Box
//        scene.geometries.add(
//                new Polygon(new Point(-100, -100, -200), new Point(-100, 100, -200), new Point(100, 100, -200), new Point(100, -100, -200))
//                        .setEmission(new Color(java.awt.Color.GRAY))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
//                new Polygon(new Point(-100, -100, -300), new Point(-100, 100, -300), new Point(100, 100, -300), new Point(100, -100, -300))
//                        .setEmission(new Color(java.awt.Color.GRAY))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
//                new Polygon(new Point(-100, 100, -300), new Point(-100, 100, -200), new Point(100, 100, -200), new Point(100, 100, -300))
//                        .setEmission(new Color(java.awt.Color.GRAY))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
//                new Polygon(new Point(-100, -100, -300), new Point(-100, -100, -200), new Point(100, -100, -200), new Point(100, -100, -300))
//                        .setEmission(new Color(java.awt.Color.GRAY))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
//                new Polygon(new Point(-100, -100, -300), new Point(-100, -100, -200), new Point(-100, 100, -200), new Point(-100, 100, -300))
//                        .setEmission(new Color(java.awt.Color.GRAY))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
//                new Polygon(new Point(100, -100, -300), new Point(100, -100, -200), new Point(100, 100, -200), new Point(100, 100, -300))
//                        .setEmission(new Color(java.awt.Color.GRAY))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30))
       // );

//        // Sphere
//        scene.geometries.add(
//                new Sphere(new Point(50, -50, -200), 50)
//                        .setEmission(new Color(java.awt.Color.BLUE))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKt(0.3))
//        );

        // Lights
        scene.lights.add(
                new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1))
                        .setKl(4E-5).setKq(2E-7)
        );

        camera.renderImage();
        camera.writeToImage();
    }
}