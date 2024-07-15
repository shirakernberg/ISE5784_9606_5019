package renderer;

import java.util.*;
import primitives.*;
import geometries.*;
import primitives.Vector;
import renderer.*;
import lighting.*;
import org.junit.jupiter.api.Test;
import scene.Scene;

import static java.awt.Color.*;

public class TestForProject {

    private Scene scene = new Scene("Bowling Scene");

    public void addPin(Point p) {
        scene.geometries.add(
                new Sphere(new Point(p.getX(), 1.1, p.getZ()), 2).setEmission(new Color(WHITE))
                , new Cylinder(2.2, new Ray(new Point(p.getX(), 0.6, p.getZ()), new Vector(0, 1, 0)), 2).setEmission(new Color(WHITE))
                , new Sphere(new Point(p.getX(), 3.1, p.getZ()), 2).setEmission(new Color(WHITE))
                , new Cylinder(3, new Ray(new Point(p.getX(), 4.6, p.getZ()), new Vector(0, 1, 0)), 1).setEmission(new Color(RED))
                , new Cylinder(0.6, new Ray(new Point(p.getX(), 5.3, p.getZ()), new Vector(0, 1, 0)), 1.01).setEmission(new Color(WHITE))
                , new Sphere(new Point(p.getX(), 7.3, p.getZ()), 1.25).setEmission(new Color(WHITE))
                , new Cylinder(1, new Ray(new Point(p.getX(), 7.6, p.getZ()), new Vector(0, 1, 0)), 1.25).setEmission(new Color(WHITE))
                , new Sphere(new Point(p.getX(), 8.3, p.getZ()), 1.25).setEmission(new Color(WHITE))

        );
    }

    @Test
    public void Bowling() {
        // Building the camera with the builder
        Camera.Builder cameraBuilder = Camera.getBuilder()
                .setLocation(new Point(-60, 20, 10))
                .setDirection(new Vector(70, -20, -10), new Vector(1, 3.5, 0))
                .setVpSize(150, 150)
                .setVpDistance(100)
                .setRayTracer(new SimpleRayTracer(scene));

        // Setting up the scene
        scene.setBackground(new Color(173, 216, 230));
        scene.geometries.add(new Plane(new Point(10, -0.5, 0), new Vector(0, 1, 0)).setEmission(new Color(0, 100, 0)) //
                .setMaterial(new Material()));

        scene.geometries.add(
                new Polygon(
                        new Point(60, 0, -20),
                        new Point(60, 0, 20),
                        new Point(-60, 0, 20),
                        new Point(-60, 0, -20)
                ).setMaterial(new Material().setKs(0.7).setKd(0.1).setKr(0.05).setShininess(19))
        );


        addPin(new Point(10 + 3 * Math.sqrt(27), 0, -9));
        addPin(new Point(10 + 3 * Math.sqrt(27), 0, -3));
        addPin(new Point(10 + 3 * Math.sqrt(27), 0, 3));
        addPin(new Point(10 + 3 * Math.sqrt(27), 0, 9));

        addPin(new Point(10 + 2 * Math.sqrt(27), 0, -6));
        addPin(new Point(10 + 2 * Math.sqrt(27), 0, 0));
        addPin(new Point(10 + 2 * Math.sqrt(27), 0, 6));

        addPin(new Point(10 + Math.sqrt(27), 0, -3));
        addPin(new Point(10 + Math.sqrt(27), 0, 3));

        addPin(new Point(10, 0, 0));

        scene.geometries.add(
                new Sphere(new Point(-40, 4, 0), 4)
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300))
                        .setEmission(new Color(RED)),
                new Sphere(new Point(-40, 4.504, 0), 3.5).setEmission(new Color(128, 0, 0)),
                new Sphere(new Point(-40.115, 4.486, 0.06), 3.5).setEmission(new Color(128, 0, 0)),
                new Sphere(new Point(-40.115, 4.486, -0.06), 3.5).setEmission(new Color(128, 0, 0))
        );

        scene.lights.add(new SpotLight(new Color(0, 510, 0), new Point(0, 0, 0), new Vector(1, 0, 0)) //
                .setKl(1).setKq(1));

        scene.lights.add(new PointLight(new Color(WHITE), new Point(-60, 20, -20)).setKl(0.001).setKq(0.0002));
        scene.lights.add(new PointLight(new Color(BLUE), new Point(-40, 20, 20)).setKl(0.0005).setKq(0.0001));

        // Rendering the image
        cameraBuilder.setImageWriter(new ImageWriter("Bowling", 1500, 1500))
                .build()
                .renderImage()
                .writeToImage();
    }


    private final Scene scene1 = new Scene("Snowman Scene").setBackground(new Color(173, 216, 230));

    @Test
    public void createSnowman() {
        // Building the camera with the builder
        Camera.Builder camera = Camera.getBuilder()
                .setLocation(new Point(0, 0, 1000))
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpSize(300, 300)
                .setVpDistance(1000)
                .setRayTracer(new SimpleRayTracer(scene1));

        // Adding the ground
        scene1.geometries.add(
                new Plane(new Point(0, -100, 0), new Vector(0, 1, 0))
                        .setEmission(new Color(139, 69, 19))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100))
        );

        // Adding the snowman
        scene1.geometries.add(
                // Bottom sphere
                new Sphere(new Point(0, -50, 0), 50)
                        .setEmission(new Color(255, 255, 255))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                // Middle sphere
                new Sphere(new Point(0, 20, 0), 30)
                        .setEmission(new Color(255, 255, 255))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                // Head sphere
                new Sphere(new Point(0, 65, 0), 20)
                        .setEmission(new Color(255, 255, 255))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                // Eyes
                new Sphere(new Point(-7, 70, 18), 2)
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Sphere(new Point(7, 70, 18), 2)
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                // Nose
                new Triangle(new Point(0, 63, 20), new Point(0, 60, 20), new Point(10, 60, 20))
                        .setEmission(new Color(255, 69, 0))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                // Buttons
                new Sphere(new Point(0, 25, 28), 2)
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Sphere(new Point(0, 20, 28), 2)
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Sphere(new Point(0, 15, 28), 2)
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                // Hat
                new Cylinder(10, new Ray(new Point(0, 80, 0), new Vector(0, 1, 0)), 20)
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Cylinder(20, new Ray(new Point(0, 90, 0), new Vector(0, 1, 0)), 15)
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                // Arms
                new Cylinder(50, new Ray(new Point(-45, 10, 0), new Vector(1, 1, 0)), 2)
                        .setEmission(new Color(139, 69, 19))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Cylinder(50, new Ray(new Point(45, 10, 0), new Vector(-1, 1, 0)), 2)
                        .setEmission(new Color(139, 69, 19))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100))
        );

        scene1.lights.add(new PointLight(new Color(255, 255, 255), new Point(50, 50, 50)).setKl(0.001).setKq(0.0001));
        scene1.lights.add(new PointLight(new Color(255, 255, 255), new Point(-30, 0, 30)).setKq(0.0001).setKl(0.0001));
        scene1.lights.add(new DirectionalLight(new Color(255, 255, 255), new Vector(1, 1, 0)));

        // Rendering the image
        camera.setImageWriter(new ImageWriter("snowman", 1000, 1000))
                .build()
                .renderImage()
                .writeToImage();
    }






    private final Scene scene2 = new Scene("spaceship scene").setBackground(new Color(13, 38, 122));

    @Test
    public void createSpaceship() {
        // Building the camera with the builder
        Camera.Builder camera2 = Camera.getBuilder()
                .setLocation(new Point(30, 20, 1000))
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpSize(200, 200)
                .setVpDistance(1000)
                .setRayTracer(new SimpleRayTracer(scene2));

        // Adding the spaceship to the scene
        scene2.geometries.add(
                new Cylinder(35, new Ray(new Point(-15, 50, 0), new Vector(1, 0, 0)), 7.5)
                        .setEmission(new Color(169, 169, 169))
                        .setMaterial(new Material().setKs(0.8).setShininess(300)), // Gray color
                new Triangle(new Point(30, 50, 0), new Point(20, 57, 0), new Point(20, 43, 0))
                        .setEmission(new Color(3, 0, 82)).setMaterial(new Material().setKs(0.7)), // Dark Blue color
                new Polygon(new Point(-15, 56, 0), new Point(-15, 44, 0), new Point(-17.5, 44, 0), new Point(-17.5, 56, 0))
                        .setEmission(new Color(3, 0, 82)), // Dark Blue color
                new Polygon(new Point(-20, 53, 0), new Point(-20, 47, 0), new Point(-17.5, 47, 0), new Point(-17.5, 53, 0))
                        .setEmission(new Color(192, 192, 192)), // Light Gray color
                new Triangle(new Point(-20, 53, 0), new Point(-20, 47, 0), new Point(-35, 50, 0))
                        .setEmission(new Color(255, 255, 0)), // Yellow color
                new Triangle(new Point(-20, 53, 0), new Point(-20, 47, 0), new Point(-27.5, 56, 0))
                        .setEmission(new Color(255, 255, 0)), // Yellow color
                new Triangle(new Point(-20, 53, 0), new Point(-20, 47, 0), new Point(-27.5, 44, 0))
                        .setEmission(new Color(255, 255, 0)), // Yellow color
                new Triangle(new Point(0, 57, 0), new Point(0, 43, 0), new Point(-17.5, 33, 0))
                        .setEmission(new Color(3, 0, 82)), // Dark Blue color
                new Triangle(new Point(0, 43, 0), new Point(0, 57, 0), new Point(-17.5, 67, 0))
                        .setEmission(new Color(3, 0, 82)), // Dark Blue color
                new Sphere(new Point(10, 50, 5), 5.5)
                        .setEmission(new Color(3, 0, 82))
                        .setMaterial(new Material().setKt(0.5).setKd(0.5).setKs(0.5).setShininess(100)), // Blue color with transparency
                new Sphere(new Point(10, 50, 5), 4.5)
                        .setEmission(new Color(192, 192, 192))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)), // Light Gray color
                new Cylinder(1.5, new Ray(new Point(10, 50, 5), new Vector(1, 0, 0)), 6)
                        .setEmission(new Color(192, 192, 192))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)) // Light Gray color
        );


        // Rendering the image
        camera2.setImageWriter(new ImageWriter("spaceship", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }






}
