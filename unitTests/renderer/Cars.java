package renderer;

import primitives.*;
import geometries.*;
import lighting.*;
import org.junit.jupiter.api.Test;
import scene.Scene;

import static java.awt.Color.*;

public class Cars {

    private final Scene scene = new Scene("Car Scene").setBackground(new Color(173, 216, 230));
    @Test
    public void createCarScene() {

        // Building the camera with the builder
        Camera.Builder camera = Camera.getBuilder()
                .setLocation(new Point(0, 40, 100))  // Lowering the camera
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpSize(150, 150)
                .setVpDistance(100)
                .setRayTracer(new SimpleRayTracer(scene));

        // Adding the road
        scene.geometries.add(
                new Polygon(new Point(-50, 0, -200), new Point(50, 0, -200), new Point(50, 0, 200), new Point(-50, 0, 200))
                        .setEmission(new Color(DARK_GRAY))
        );

        // Adding the white lines on the road
        for (int i = -200; i < 200; i += 20) {
            scene.geometries.add(
                    new Polygon(new Point(-2, 0.01, i), new Point(2, 0.01, i), new Point(2, 0.01, i + 10), new Point(-2, 0.01, i + 10))
                            .setEmission(new Color(WHITE))
            );
        }

        // Adding the grass
        scene.geometries.add(
                new Plane(new Point(0,  -0.5, 0), new Vector(0, 1, 0))
                        .setEmission(new Color(0, 255, 0))
        );

        // Adding guardrails
        for (int i = -200; i < 200; i += 20) {
            scene.geometries.add(
                    new Cylinder(8, new Ray(new Point(-45, 2, i), new Vector(0, 1, 0)), 1)
                            .setEmission(new Color(GRAY)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                    new Cylinder(8, new Ray(new Point(45, 2, i), new Vector(0, 1, 0)), 1)
                            .setEmission(new Color(GRAY)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100))
            );
        }

        // Adding horizontal rail on top of the guardrails
        scene.geometries.add(
                new Polygon(new Point(-45, 8, -200), new Point(-45, 8, 200), new Point(-45, 10, 200), new Point(-45, 10, -200))
                        .setEmission(new Color(GRAY)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Polygon(new Point(45, 8, -200), new Point(45, 8, 200), new Point(45, 10, 200), new Point(45, 10, -200))
                        .setEmission(new Color(GRAY)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100))
        );


        // Adding the car
        scene.geometries.add(

                // Car body
                new Polygon(new Point(-20 - 10, 10, 1), new Point(14 - 19, 10, 1), new Point(8 - 19, 6, 1), new Point(-26 - 10, 6, 1)) // nose top
                        .setEmission(new Color(255, 215, 0)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Polygon(new Point(8 - 19, 6, 1), new Point(-26 - 10, 6, 1), new Point(-26 - 10, 0, 1), new Point(8 - 19, 0, 1)) // nose front
                        .setEmission(new Color(255, 215, 0)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Polygon(new Point(8 - 19, 0, 1), new Point(8 - 19, 6, 1), new Point(14 - 19, 10, 1), new Point(14 - 19, 3, 1)) // nose side front
                        .setEmission(new Color(255, 215, 0)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Polygon(new Point(14 - 19, 3, 1), new Point(14 - 19, 10, 1), new Point(20 - 19, 13, 1), new Point(20 - 19, 6, 1)) // nose side back
                        .setEmission(new Color(255, 215, 0)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Polygon(new Point(-26 - 10, 0, 1), new Point(20 - 19, 0, 1), new Point(20 - 19, -18, 1), new Point(-26 - 10, -18, 1)) // bottom
                        .setEmission(new Color(255, 215, 0)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),

                // Car roof
                new Polygon(new Point(-20 - 10, 10, 1), new Point(14 - 19, 10, 1), new Point(13 - 19, 16, 1), new Point(-16 - 10, 16, 1)) // Roof front
                        .setEmission(new Color(255, 215, 0)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Polygon(new Point(-10 - 10, 18, 1), new Point(16 - 19, 18, 1), new Point(13 - 19, 16, 1), new Point(-16 - 10, 16, 1)) // Roof top
                        .setEmission(new Color(255, 215, 0)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Polygon(new Point(14 - 19, 10, 1), new Point(13 - 19, 16, 1), new Point(16 - 19, 18, 1), new Point(20 - 19, 13, 1)) // Roof side
                        .setEmission(new Color(255, 215, 0)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),

                // Car windows (adjusted for visibility)
                new Polygon(new Point(-19.5 - 10, 10.5, 1.1), new Point(13.5 - 19, 10.5, 1.1), new Point(12.5 - 19, 15.5, 1.1), new Point(-15.5 - 10, 15.5, 1.1)) // front window
                        .setEmission(new Color(173, 216, 230)).setMaterial(new Material().setKd(0.3).setShininess(30)),
                new Polygon(new Point(14 - 19, 10.5, 1.1), new Point(13.3 - 19, 15.5, 1.1), new Point(15 - 19, 16.5, 1.1), new Point(16.5 - 19, 11.5, 1.1)) // side window 1
                        .setEmission(new Color(173, 216, 230)).setMaterial(new Material().setKd(0.3).setShininess(30)),
                new Polygon(new Point(15.2 - 19, 16.7, 1.1), new Point(16.7 - 19, 11.7, 1.1), new Point(19.5 - 19, 13, 1.1), new Point(16 - 19, 17.2, 1.1)) // side window 2
                        .setEmission(new Color(173, 216, 230)).setMaterial(new Material().setKd(0.3).setShininess(30)),

                // Car wheels
                new Cylinder(3, new Ray(new Point(11- 19, 4, 2), new Vector(0, 0, 0.3)), 2) // Front right
                        .setEmission(new Color(0, 0, 0)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Cylinder(3, new Ray(new Point(18- 19, 7, 2), new Vector(0, 0, 0.5)), 2) // back right
                        .setEmission(new Color(0, 0, 0)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Cylinder(2.5, new Ray(new Point(-10- 19, -6, 1), new Vector(0, 1, 0.5)), 2) // Front left (lowered)
                        .setEmission(new Color(0, 0, 0)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100))
        );



        // Adding lights
        scene.lights.add(new SpotLight(new Color(20, 44, 27), new Point(0, -100, 0), new Vector(1, 1, 1))
                        .setKl(1E-5).setKq(1.5E-7));
        scene.lights.add(new SpotLight(new Color(0, 510, 0), new Point(0, 0, 0), new Vector(1, 0, 0)) //
                .setKl(1).setKq(1));
        scene.lights.add(new SpotLight(new Color(WHITE),new Point(50,-30,200),new Vector(-1,0,-1)).setKl(0.0004).setKq(0.0000006));
        scene.lights.add(new SpotLight(new Color(WHITE),new Point(-75,30,200),new Vector(1,0,-0.55)).setKl(0.0004).setKq(0.0000006));
        scene.lights.add(new SpotLight(new Color(950, 550, 0), new Point(-95, 0, 50), new Vector(82, -18, -25)).setKl(0.1).setKq(0.0001));
        scene.lights.add(new SpotLight(new Color(102, 255, 255), new Point(79, -30, 100), new Vector(-5, 0, -14)).setKl(0.1).setKq(0.0001));

        // Rendering the image
        camera.setImageWriter(new ImageWriter("car_scene", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }



}