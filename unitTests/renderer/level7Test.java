package renderer;

import geometries.*;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import static java.awt.Color.*;

public class level7Test {
    /** Scene of the tests */
    private final Scene scene      = new Scene("Test scene");
    /** Camera builder of the tests */
    private final Camera.Builder camera     = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setLocation(new Point(0, 0, 1000)).setVpDistance(1000)
            .setLocation(new Point(30, 20, 1000)).setVpDistance(1000)
            .setVpSize(200, 200)
            .setRayTracer(new SimpleRayTracer(scene));
    // private final Scene          scene         = new Scene("Test scene");
    /** Camera builder for the tests with triangles */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setRayTracer(new SimpleRayTracer(scene));

    /** The sphere in the tests */
    private final Intersectable sphere     = new Sphere(new Point(0, 0, -750), 60d)
            .setEmission(new Color(RED))
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));
    /** The material of the triangles in the tests */
    private final Material       trMaterial = new Material().setKd(0.5).setKs(0.5).setShininess(30);

    @Test
    public void Move2() {
        Sphere s=new Sphere(new Point(-49, -40, 200),10d);
        Point spotLocation=new Point(900, 900, -1000);
        scene.geometries.add(sphere, s.setEmission(new Color(BLUE)).setMaterial(trMaterial));
        scene.lights.add( //
                new SpotLight(new Color(400, 240, 0), spotLocation, new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7));

        camera.setImageWriter(new ImageWriter("Move2", 1000, 1000))
                .build()
                .renderImage() //
                .writeToImage();
    }




    @Test
    public void level7() {
        scene.geometries.add(
                new Sphere(new Point(0, -600, -400), 50).setEmission(new Color(PINK))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(0, -620, -400), 50d).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(0, -640, -400), 50).setEmission(new Color(PINK))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(0, -660, -400), 50d).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(120, -600, -400), 50).setEmission(new Color(PINK))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(120, -620, -400), 50d).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(120, -640, -400), 50).setEmission(new Color(PINK))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(120, -660, -400), 50d).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(120, -700, -400), 50d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(0, -700, -400), 50d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)));
//                new Cylinder(10.0,new Ray(new Point(0, -400, 0),new Vector(1 ,0, 0),new Vector(0, 1, 0)),50)
//                        .setEmission(new Color(yellow)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)));
//                new Tube(new Ray(new Point(550, 0, -550),new Vector(1 ,0, 0),new Vector(1, 1, 0)),40)
//                        .setEmission(new Color(yellow)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
//                 new Tube(new Ray(new Point(700, 80, -700),new Vector(1 ,0, 0),new Vector(1, 1, 0)),40));
        scene.lights.add(
                new SpotLight(new Color(400, 240, 0), new Point(100, 550, -250), new Vector(1, 1, -3))
                        .setKl(1E-5).setKq(1.5E-7));
        //   scene.background= new Color(25,250,60);
        cameraBuilder.setLocation(new Point(0, -30, 1000)).setVpDistance(1000)
                .setVpSize(1000, 1000)
                .setImageWriter(new ImageWriter("move 2" +
                        "", 1000, 1000))
                .build()
                .renderImage()
                .writeToImage();
    }














    }






