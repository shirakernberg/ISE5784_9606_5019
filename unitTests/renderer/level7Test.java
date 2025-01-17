package renderer;

import geometries.*;
import lighting.AmbientLight;
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
            .setLocation(new Point(30, 20, 1000)).setVpDistance(1000)
            .setVpSize(200, 200)
            .setRayTracer(new SimpleRayTracer(scene));

    // private final Scene          scene         = new Scene("Test scene");
    /** Camera builder for the tests with triangles */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setRayTracer(new SimpleRayTracer(scene));

    /** The sphere in the tests */
    private final Intersectable sphere     = new Sphere(new Point(0, -50, -200), 60d)
            .setEmission(new Color(RED))
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));
    /** The material of the triangles in the tests */
    private final Material       trMaterial = new Material().setKd(0.5).setKs(0.5).setShininess(30);

    /** Helper function for the tests in this module
     * @param pictName     the name of the picture generated by a test
     * @param triangle     the triangle in the test
     * @param spotLocation the spotlight location in the test */
    private void sphereTriangleHelper(String pictName, Sphere triangle, Point spotLocation) {
        scene.geometries.add(sphere, triangle.setEmission(new Color(BLUE)).setMaterial(trMaterial));
        scene.lights.add( //
                new SpotLight(new Color(400, 240, 0), spotLocation, new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7));

        camera.setImageWriter(new ImageWriter(pictName, 1000, 1000))
                .build()
                .renderImage() //
                .writeToImage();
    }

    /** Sphere-Triangle shading - move triangle upper-righter */
    @Test
    public void sphereTriangleMove2() {
        scene.geometries.add(
                new Sphere(new Point(0,0,0),21d).setEmission(new Color(GREEN)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(0,20,0),22d).setEmission(new Color(GREEN)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(0,-5,0),20d).setEmission(new Color(GREEN)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(0,40,0),20d).setEmission(new Color(GREEN)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(41,0,0),24d).setEmission(new Color(PINK)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30))
                ,new Sphere(new Point(-41,0,0),24d).setEmission(new Color(PINK)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(42,40,0),27d).setEmission(new Color(BLUE)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30))
                ,new Sphere(new Point(-42,40,0),27d).setEmission(new Color(BLUE)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Cylinder(20.0,new Ray(new Point(43, 50, 0),new Vector(1 ,1, 0),new Vector(0, 1, 0)),5)
                      .setEmission(new Color(yellow)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30))
        );
        scene.lights.add(
                new SpotLight(new Color(400, 240, 0), new Point(100, 550, -250), new Vector(1, 1, -3))
                        .setKl(1E-5).setKq(1.5E-7));
        scene.lights.add(
                new SpotLight(new Color(400, 240, 0), new Point(0, 1, 0), new Vector(1, 1, 1))
                        .setKl(1E-5).setKq(1.5E-7));
        scene.lights.add(
                new SpotLight(new Color(400, 240, 0), new Point(0, 100, 0), new Vector(1, 1, 1))
                        .setKl(1E-5).setKq(1.5E-7));
        scene.geometries.add(
                new Sphere(new Point(-410,80,50),5d).setEmission(new Color(BLUE)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(-400,80,40),5d).setEmission(new Color(BLUE)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(-400,-100,0),100d).setEmission(new Color(pink)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(-400,10,0),70d).setEmission(new Color(pink)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(-400,80,0),40d).setEmission(new Color(pink)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Triangle(new Point(-500,-150,100),new Point(-300,-150,100),new Point(-400,-250,100))
                        .setEmission(new Color(ORANGE)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30))
        );
        //   scene.background= new Color(25,250,60);
        cameraBuilder.setLocation(new Point(0, -30, 1000)).setVpDistance(1000)
                .setVpSize(1000, 1000)
                .setImageWriter(new ImageWriter("Move2" +
                        "", 1000, 1000))
                .build()
                .renderImage()
                .writeToImage();
    }


    /** Sphere-Triangle shading - move triangle upper-righter */
    @Test
    public void snowman() {
        scene.lights.add(
                new SpotLight(new Color(109, 255, 72), new Point(300, 170, 200), new Vector(-800, 0, -500))
                        .setKl(1E-5).setKq(1.5E-7));
        scene.geometries.add(
                //eyes
                new Sphere(new Point(-33,90,500),8d).setEmission(new Color(BLUE)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(18,90,500),8d).setEmission(new Color(BLUE)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                //body
                new Sphere(new Point(-10,-200,0),200d).setEmission(new Color(255,232,232)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(-10,0,0),170d).setEmission(new Color(255,232,232)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(-10,170,0),140d).setEmission(new Color(255,232,232)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Plane(new Point(500, -1000, 0), new Vector(0, -300, -1))//floor
                        .setEmission(new Color(black)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Triangle(new Point(-7,70,500),new Point(-25,70,500),new Point(-45,45,500))//nose
                        .setEmission(new Color(ORANGE)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                //bottons
                new Sphere(new Point(-5,20,500),10d).setEmission(new Color(PINK)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(-5,-10,500),10d).setEmission(new Color(PINK)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(-5,-40,500),10d).setEmission(new Color(PINK)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                //hat
                new Sphere(new Point(-10,180,-5),140d).setEmission(new Color(112,49,144)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(-10,190,-7),140d).setEmission(new Color(255,43,172)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(-10,340,0),15d).setEmission(new Color(PINK)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30))


                );
        scene.background= new Color(14,11,20);
        cameraBuilder.setLocation(new Point(0, -30, 1000)).setVpDistance(1000)
                .setVpSize(1000, 1000)
                .setImageWriter(new ImageWriter("Snowman" +
                        "", 1000, 1000))
                .build()
                .renderImage()
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
                new Sphere(new Point(0, -700, -400), 300d).setEmission(new Color(WHITE))
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
    @Test
    public void l7() {
        scene.geometries.add(
                new Sphere(new Point(-200, -200, -3), 50d).setEmission(new Color(YELLOW))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(-400, -200, 0), 20).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(0, 30, 0), 10).setEmission(new Color(PINK))
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
//        scene.lights.add(
//                new SpotLight(new Color(400, 240, 0), new Point(-200, -200, 0), new Vector(1, 1, -3))
//                        .setKl(1E-5).setKq(1.5E-7));

        scene.lights.add(
                new SpotLight(new Color(400, 240, 0), new Point(0, 1, 0), new Vector(1, 1, 1))
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
    @Test
    public void space() {
        scene.geometries.add(
                new Sphere(new Point(0, -900, -400), 800d).setEmission(new Color(28,38,66))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),//earth
                 //body
                 new Sphere(new Point(-12,30,0),30d)  .setEmission(new Color(223,255,228)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(-32,30,0),30d)  .setEmission(new Color(223,255,228)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(12,30,0),30d)  .setEmission(new Color(223,255,228)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(new Point(-20,27,20),15d)  .setEmission(new Color(BLACK)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),

                //front
                new Triangle(new Point(-90,30,100),new Point(-40,30,0),new Point(-40,60,0)).setEmission(new Color(RED)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Triangle(new Point(-90,30,100),new Point(-40,30,0),new Point(-40,-1,0)).setEmission(new Color(RED)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
               // new Triangle(new Point(-90,30,100),new Point(-40,30,0),new Point(-40,-1,-10)).setEmission(new Color(RED)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                //back
                new Triangle(new Point(70,11,70),new Point(30,30,0),new Point(30,5,0)).setEmission(new Color(RED)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Triangle(new Point(70,30,70),new Point(30,45,0),new Point(30,14,0)).setEmission(new Color(RED)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Triangle(new Point(70,43,70),new Point(30,55,0),new Point(30,40,0)).setEmission(new Color(RED)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30))

                //nutterfly and snial flower fruits home
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

         scene.background= new Color(10,0,26);
        cameraBuilder.setLocation(new Point(0, -30, 1000)).setVpDistance(1000)
                .setVpSize(1000, 1000)
                .setImageWriter(new ImageWriter("Space" +
                        "", 1000, 1000))
                .build()
                .renderImage()
                .writeToImage();
    }

}

