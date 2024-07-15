package renderer;

import java.util.*;
import primitives.*;
import geometries.*;
import primitives.Vector;
import renderer.*;
import lighting.*;
import org.junit.jupiter.api.Test;
import scene.Scene;

import static java.awt.Color.black;
import static java.awt.Color.red;

public class Space{
    private final Scene scene = new Scene("Test scene");

    /** Camera builder of the tests */
    private final Camera.Builder camera = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setLocation(new Point(30, 20, 1000)).setVpDistance(1000)
            .setVpSize(200, 200)
            .setRayTracer(new SimpleRayTracer(scene));

    @Test
    public void space() {
        scene.geometries.add(
                // Earth
                new Sphere(new Point(0, -900, -400), 800d).setEmission(new Color(28, 38, 66))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                // Spaceship
                new Cylinder(140, new Ray(new Point(-100, 100, 0), new Vector(1, 0, 0)), 30)
                        .setEmission(new Color(169, 169, 169))
                        .setMaterial(new Material().setKs(0.8).setShininess(300)), // Gray color
                new Triangle(new Point(80, 100, 0), new Point(40, 130, 0), new Point(40, 70, 0))
                        .setEmission(new Color(red)).setMaterial(new Material().setKs(0.7)), // red color
                new Polygon(new Point(-100, 112, 0), new Point(-100, 88, 0), new Point(-105, 88, 0), new Point(-105, 112, 0))
                        .setEmission(new Color(3, 0, 82)), // Dark Blue color
                new Polygon(new Point(-110, 106, 0), new Point(-110, 94, 0), new Point(-105, 94, 0), new Point(-105, 106, 0))
                        .setEmission(new Color(192, 192, 192)), // Light Gray color
                new Triangle(new Point(-110, 106, 0), new Point(-110, 94, 0), new Point(-140, 100, 0))
                        .setEmission(new Color(255, 255, 0)), // Yellow color
                new Triangle(new Point(-110, 106, 0), new Point(-110, 94, 0), new Point(-125, 112, 0))
                        .setEmission(new Color(255, 255, 0)), // Yellow color
                new Triangle(new Point(-110, 106, 0), new Point(-110, 94, 0), new Point(-125, 88, 0))
                        .setEmission(new Color(255, 255, 0)), // Yellow color
                new Triangle(new Point(-40, 130, 0), new Point(-40, 70, 0), new Point(-85, 50, 0))
                        .setEmission(new Color(red)), // red color
                new Triangle(new Point(-40, 70, 0), new Point(-40, 130, 0), new Point(-85, 150, 0))
                        .setEmission(new Color(red)), // red color
                new Sphere(new Point(-20, 100, 10), 20)
                        .setEmission(new Color(3, 0, 82))
                        .setMaterial(new Material().setKt(0.5).setKd(0.5).setKs(0.5).setShininess(100)), // Blue color with transparency
                new Sphere(new Point(-20, 100, 10), 15)
                        .setEmission(new Color(192, 192, 192))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)), // Light Gray color
                new Cylinder(5, new Ray(new Point(-20, 100, 10), new Vector(1, 0, 0)), 18)
                        .setEmission(new Color(192, 192, 192))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)) // Light Gray color
        );

        scene.lights.add(
                new SpotLight(new Color(20, 44, 27), new Point(0, -100, 0), new Vector(1, 1, 1))
                        .setKl(1E-5).setKq(1.5E-7));
        scene.lights.add(
                new SpotLight(new Color(109, 255, 72), new Point(0, -170, 0), new Vector(1, 1, 1))
                        .setKl(1E-5).setKq(1.5E-7));
        scene.lights.add(
                new SpotLight(new Color(244, 255, 205), new Point(-400, -30, 0), new Vector(1, 1, 1))
                        .setKl(1E-5).setKq(1.5E-6));
        scene.lights.add(
                new SpotLight(new Color(244, 255, 205), new Point(400, -30, 0), new Vector(1, 1, 1))
                        .setKl(1E-5).setKq(1.6E-5));
        scene.lights.add(
                new SpotLight(new Color(13, 138, 29), new Point(1000, -300, 0), new Vector(1, 1, 1))
                        .setKl(1E-5).setKq(1.5E-10));
        scene.lights.add(
                new SpotLight(new Color(13, 138, 29), new Point(-1200, -500, 0), new Vector(1, 1, 1))
                        .setKl(1E-5).setKq(1.5E-10));

        scene.background = new Color(10, 0, 26);

        camera.setLocation(new Point(0, -30, 1000)).setVpDistance(1000)
                .setVpSize(1000, 1000)
                .setImageWriter(new ImageWriter("Space", 1000, 1000))
                .build()
                .renderImage()
                .writeToImage();
    }
}
