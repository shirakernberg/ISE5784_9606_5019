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

public class TestForProject{

    private Scene scene = new Scene("Bowling Scene");

    public void addPin(Point p)
    {
        scene.geometries.add(
                new Sphere(new Point(p.getX(), 1.1, p.getZ()), 2).setEmission(new Color(WHITE))
                ,new Cylinder(2.2,new Ray(new Point(p.getX(), 0.6, p.getZ()), new Vector(0, 1, 0)), 2).setEmission(new Color(WHITE))
                ,new Sphere(new Point(p.getX(), 3.1, p.getZ()), 2).setEmission(new Color(WHITE))
                ,new Cylinder(3,new Ray(new Point(p.getX(), 4.6, p.getZ()), new Vector(0, 1, 0)), 1).setEmission(new Color(RED))
                ,new Cylinder(0.6,new Ray(new Point(p.getX(), 5.3, p.getZ()), new Vector(0, 1, 0)), 1.01).setEmission(new Color(WHITE))
                ,new Sphere(new Point(p.getX(), 7.3, p.getZ()), 1.25).setEmission(new Color(WHITE))
                ,new Cylinder(1,new Ray(new Point(p.getX(), 7.6, p.getZ()), new Vector(0, 1, 0)), 1.25).setEmission(new Color(WHITE))
                ,new Sphere(new Point(p.getX(), 8.3, p.getZ()), 1.25).setEmission(new Color(WHITE))

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
                ).setMaterial(new Material().setKr(0.5).setKd(0.5).setKs(0.3))
        );


/*
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
*/
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
}
