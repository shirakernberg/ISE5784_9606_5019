package renderer;

import primitives.*;
import geometries.*;
import primitives.Vector;
import lighting.*;
import org.junit.jupiter.api.Test;
import scene.Scene;

import static java.awt.Color.*;
//test
public class Space {
    private final Scene scene = new Scene("Test scene");

    /**
     * Camera builder of the tests
     */
    private final Camera.Builder camera = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setLocation(new Point(100, 20, 1000)).setVpDistance(1000)
            .setVpSize(200, 200)
            .setRayTracer(new SimpleRayTracer(scene))
            .setMultithreading(3);

    @Test
    public void space() {
        // Define a transparent material
        Material shinyReflectiveMaterial = new Material()
                .setKd(new Double3(0.9))        // Adjust the diffuse coefficient
                .setKs(new Double3(0.9))        // Adjust the specular coefficient
                .setKt(new Double3(0.5))        // Set transparency (0.0 = fully opaque, 1.0 = fully transparent)
                .setShininess(300);             // Adjust the shininess as needed


// Light blue color
        Color lightBlue = new Color(BLUE);

        Material material = new Material().setKd(0.5).setKs(0.5).setKt(0.5).setShininess(300);
        int leftX = 350;
        int rightX = 550;
        int topY = -350;
        int bottomY = -550;
        int z1 = -200;
        int z2 = z1 - 200;
        scene.geometries.add(
//                // big shpere
                new Sphere(new Point(200, 0, -400), 300d).setEmission(new Color(28, 38, 66))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setKt(0.9999999).setShininess(300)),
                //baby sphere
                new Sphere(new Point(0, -500, -400), 100d).setEmission(new Color(28, 38, 66))
                        .setMaterial(material),
                //"green" sphere
                new Sphere(new Point(-350, -500, -400), 200d).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)),
                new Tube(new Ray(new Point(200, 0, 0),new Vector(1,1,1)),10).setEmission(new Color(87,84,83))
                        .setMaterial(material),

                new Triangle(new Point(-400, 500, -600), new Point(-200, 200, -800), new Point(-600, 200, -800))
                        .setEmission(new Color(180, 120, 80))  // Lighter brown color
                        .setMaterial(new Material().setKd(0.5).setKs(0.9).setKr(0.8).setShininess(300)),
                new Triangle(new Point(-400, 500, -600), new Point(-200, 200, -800), new Point(-400, 200, -400))
                        .setEmission(new Color(180, 120, 80))  // Lighter brown color
                        .setMaterial(new Material().setKd(0.5).setKs(0.9).setKr(0.8).setShininess(300)),
                new Triangle(new Point(-400, 500, -600), new Point(-600, 200, -800), new Point(-400, 200, -400))
                        .setEmission(new Color(180, 120, 80))  // Lighter brown color
                        .setMaterial(new Material().setKd(0.5).setKs(0.9).setKr(0.8).setShininess(300)),
                new Triangle(new Point(-200, 200, -800), new Point(-600, 200, -800), new Point(-400, 200, -400))
                        .setEmission(new Color(180, 120, 80))  // Lighter brown color
                        .setMaterial(new Material().setKd(0.5).setKs(0.9).setKr(0.8).setShininess(300)),
// down
                new Triangle(new Point(-400, -100, -600), new Point(-200, 200, -800), new Point(-600, 200, -800))
                        .setEmission(new Color(180, 120, 80))  // Lighter brown color
                        .setMaterial(new Material().setKd(0.5).setKs(0.9).setKr(0.8).setShininess(300)),
                new Triangle(new Point(-400, -100, -600), new Point(-200, 200, -800), new Point(-400, 200, -400))
                        .setEmission(new Color(180, 120, 80))  // Lighter brown color
                        .setMaterial(new Material().setKd(0.5).setKs(0.9).setKr(0.8).setShininess(300)),
                new Triangle(new Point(-400, -100, -600), new Point(-600, 200, -800), new Point(-400, 200, -400))
                        .setEmission(new Color(180, 120, 80))  // Lighter brown color
                        .setMaterial(new Material().setKd(0.5).setKs(0.9).setKr(0.8).setShininess(300)),
                new Triangle(new Point(-200, 200, -800), new Point(-600, 200, -800), new Point(-400, 200, -400))
                        .setEmission(new Color(180, 120, 80))  // Lighter brown color
                        .setMaterial(new Material().setKd(0.5).setKs(0.9).setKr(0.8).setShininess(300)),


                new Tube(new Ray(new Point(200, 200, 200), new Vector(-1, 1, -1)), 10)
                        .setEmission(new Color(87,84,83))  // Color: Dark Silver
                        .setMaterial(new Material().setKd(0.5).setKs(1.0).setKr(1.0).setShininess(300)),

                new Sphere(new Point(200, 200, 200), 50)
                        .setEmission(new Color(BLACK))  // Darker shade of teal
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)),

                new Triangle(
                        new Point(100, 300, -200),
                        new Point(300, 500, -400),
                        new Point(-100, 500, -400)
                )
                        .setEmission(new Color(0,0,0))
                        .setMaterial(new Material().setKd(0.5).setKs(1.0).setKr(1.0).setKt(1.0).setShininess(300)),

                new Triangle(
                        new Point(100, 300, -200),
                        new Point(300, 500, -400),
                        new Point(100, 500, 0)
                )
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setKd(0.5).setKs(1.0).setKr(1.0).setKt(1.0).setShininess(300)),

                new Triangle(
                        new Point(100, 300, -200),
                        new Point(-100, 500, -400),
                        new Point(100, 500, 0)
                )
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setKd(0.5).setKs(1.0).setKr(1.0).setKt(1.0).setShininess(300)),

                new Triangle(
                        new Point(300, 500, -400),
                        new Point(-100, 500, -400),
                        new Point(100, 500, 0)
                )
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setKd(0.5).setKs(1.0).setKr(1.0).setKt(1.0).setShininess(300)),

                //front
                new Triangle(
                        new Point(leftX, bottomY, z1),  // x changed from 250 to 350
                        new Point(rightX, bottomY, z1),  // x changed from 450 to 550
                        new Point(leftX, topY, z1)   // x changed from 250 to 350
                ).setMaterial(shinyReflectiveMaterial).setEmission(lightBlue),

                new Triangle(
                        new Point(rightX - 1, bottomY, z1),  // x changed from 450 to 550
                        new Point(rightX - 1, topY, z1),   // x changed from 250 to 35
                        new Point(leftX - 1, topY, z1)  // x changed from 450 to 550
                ).setMaterial(shinyReflectiveMaterial).setEmission(lightBlue),

//// Back face

                new Triangle(
                        new Point(leftX, bottomY, z2),  // x changed from 250 to 350
                        new Point(rightX, bottomY, z2),  // x changed from 450 to 550
                        new Point(leftX, topY, z2)   // x changed from 250 to 350
                ).setMaterial(shinyReflectiveMaterial).setEmission(lightBlue),

                new Triangle(
                        new Point(rightX - 1, bottomY, z2),  // x changed from 450 to 550
                        new Point(rightX - 1, topY, z2),   // x changed from 250 to 35
                        new Point(leftX - 1, topY, z2)  // x changed from 450 to 550
                ).setMaterial(shinyReflectiveMaterial).setEmission(lightBlue),
//
//// Left face

        new Triangle(
                new Point(leftX, bottomY, z2),
                new Point(leftX, bottomY, z1),
                new Point(leftX, topY, z2)
        ).setMaterial(shinyReflectiveMaterial).setEmission(lightBlue),

        new Triangle(
                new Point(leftX, bottomY, z1),
                new Point(leftX, topY, z1),
                new Point(leftX, topY, z2)
        ).setMaterial(shinyReflectiveMaterial).setEmission(lightBlue),




//
//// Right face



        new Triangle(
                new Point(rightX-1, bottomY, z2),
                new Point(rightX-1, bottomY, z1),
                new Point(rightX-1, topY, z2)
        ).setMaterial(shinyReflectiveMaterial).setEmission(lightBlue),

                new Triangle(
                        new Point(rightX-1, bottomY, z1),
                        new Point(rightX-1, topY, z1),
                        new Point(rightX-1, topY, z2)
                ).setMaterial(shinyReflectiveMaterial).setEmission(lightBlue),



//
//// Top face

        new Triangle(
                new Point(leftX,topY,z1),
                new Point(rightX,topY,z1),
                new Point(leftX,topY,z2)
        ).setMaterial(shinyReflectiveMaterial).setEmission(lightBlue),

                new Triangle(
                        new Point(rightX-1,topY,z1),
                        new Point(rightX-1,topY,z2),
                        new Point(leftX,topY,z2)
                ).setMaterial(shinyReflectiveMaterial).setEmission(lightBlue),


//
//// Bottom face


                new Triangle(
                        new Point(leftX,bottomY,z1),
                        new Point(rightX,bottomY,z1),
                        new Point(leftX,bottomY,z2)
                ).setMaterial(shinyReflectiveMaterial).setEmission(lightBlue),

                new Triangle(
                        new Point(rightX-1,bottomY,z1),
                        new Point(rightX-1,bottomY,z2),
                        new Point(leftX,bottomY,z2)
                ).setMaterial(shinyReflectiveMaterial).setEmission(lightBlue));

//        scene.lights.add(
//                new SpotLight(new Color(ORANGE),new Point(0, 0, 0), new Vector(0, 0, -1))  // Dark green, bottom center
//                        .setKl(1E-5).setKq(1.5E-7));

        scene.lights.add(
                new SpotLight(new Color(ORANGE), new Point(500, -500, -300), new Vector(-1, 0, 0))  // Dark green, bottom center
                        .setKl(1E-5).setKq(1.5E-7));

        scene.lights.add(
                new PointLight(new Color(56, 38, 62), new Vector(1, 1, 1))//purple
                        .setKl(1E-5).setKq(1.5E-7));
        scene.lights.add(
                new SpotLight(new Color(20, 44, 27), new Point(0, -100, 0), new Vector(1, 1, 1))  // Dark green, bottom center
                        .setKl(1E-5).setKq(1.5E-7));
        scene.lights.add(
                new SpotLight(new Color(200, 15, 100), new Point(0, 320, 0), new Vector(1, 1, 1))  // Less intense pink, top center
                        .setKl(1E-5).setKq(1.5E-9));

        scene.lights.add(
                new SpotLight(new Color(RED), new Point(-400, -30, 0), new Vector(1, 1, 1))  // Red, left center
                        .setKl(1E-5).setKq(1.5E-6));
        scene.lights.add(
                new SpotLight(new Color(ORANGE), new Point(100, -300, 0), new Vector(1, 1, 1))  // Orange, bottom right
                        .setKl(1E-5).setKq(1.5E-10));
        scene.lights.add(
                new SpotLight(new Color(13, 138, 29), new Point(-990, -300, 0), new Vector(1, 1, 1))  // Medium green, bottom far left
                        .setKl(1E-5).setKq(1.6E-10));

        scene.background = new Color(10, 0, 26);


        camera.setLocation(new Point(0, -30, 1000)).setVpDistance(1000)
                .setVpSize(1000, 1000)
                .setImageWriter(new ImageWriter("Space2", 1000, 1000))
                .build()
                .renderImage()
                .writeToImage();
    }

    @Test
    public void spaceShip() {
        //  Spaceship body
        scene.geometries.add(
                new Cylinder(140, new Ray(new Point(-100, 0, 0), new Vector(800, -500, 500)), 30)
                        .setEmission(new Color(169, 169, 169))
                        .setMaterial(new Material().setKs(0.8).setShininess(300)),// Gray color
                new Sphere(new Point(-200, 70, 0), 130).setEmission(new Color(28, 38, 66))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setKt(-500).setShininess(30)),
                //hat
                new Triangle(new Point(0, 20, 300), new Point(30, 30, 300), new Point(20, 50, 300))
                        .setEmission(new Color(red)).setMaterial(new Material().setKs(0.7)),// red color

                new Polygon(new Point(-110, 106, 0), new Point(-110, 94, 0), new Point(-105, 94, 0), new Point(-105, 106, 0))
                        .setEmission(new Color(192, 192, 192)), // Light Gray color
                new Triangle(new Point(-110, 106, 0), new Point(-110, 94, 0), new Point(-140, 100, 0))
                        .setEmission(new Color(255, 255, 0)), // Yellow color
                new Triangle(new Point(-110, 106, 0), new Point(-110, 94, 0), new Point(-125, 112, 0))
                        .setEmission(new Color(255, 255, 0)), // Yellow color
                new Triangle(new Point(-110, 106, 0), new Point(-110, 94, 0), new Point(-125, 88, 0))
                        .setEmission(new Color(255, 255, 0)), // Yellow color
                //    wings
                new Triangle(new Point(-40, 130, 0), new Point(-40, 70, 0), new Point(-85, 50, 0))
                        .setEmission(new Color(red)), // red color
                new Triangle(new Point(-40, 70, 0), new Point(-40, 130, 0), new Point(-85, 150, 0))
                        .setEmission(new Color(red)), // red color
                //window
                new Sphere(new Point(-20, 100, 50), 20)
                        .setEmission(new Color(3, 0, 82))
                        .setMaterial(new Material().setKt(0.5).setKd(0.5).setKs(0.5).setShininess(100)), // Blue color with transparency
                new Sphere(new Point(-20, 100, 50), 15)
                        .setEmission(new Color(192, 192, 192))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)), // Light Gray color
                new Cylinder(5, new Ray(new Point(-20, 100, 50), new Vector(1, 0, 0)), 18)
                        .setEmission(new Color(192, 192, 192))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100))); // Light Gray color

        scene.lights.add(
                new SpotLight(new Color(20, 44, 27), new Point(0, -100, 0), new Vector(1, 1, 1))
                        .setKl(1E-5).setKq(1.5E-7));//dark, brownish color
        scene.lights.add(
                new SpotLight(new Color(109, 255, 72), new Point(0, 300, 0), new Vector(1, 1, 1))
                        .setKl(1E-5).setKq(1.5E-7)); //lime green color
        scene.lights.add(
                new SpotLight(new Color(RED), new Point(-400, -30, 0), new Vector(1, 1, 1))
                        .setKl(1E-5).setKq(1.5E-6));//red
        scene.lights.add(
                new SpotLight(new Color(ORANGE), new Point(100, -300, 0), new Vector(1, 1, 1))
                        .setKl(1E-5).setKq(1.5E-10));//orange
        scene.lights.add(
                new SpotLight(new Color(13, 138, 29), new Point(-990, -300, 0), new Vector(1, 1, 1))
                        .setKl(1E-5).setKq(1.6E-10));//dark, greenish color

        scene.background = new Color(10, 0, 26);


        camera.setLocation(new Point(0, -30, 1000)).setVpDistance(1000)
                .setVpSize(1000, 1000)
                .setImageWriter(new ImageWriter("Space", 1000, 1000))
                .build()
                .renderImage()
                .writeToImage();

    }
    @Test
    public void antiTest(){
        scene.geometries.add(
                // big shpere
                new Sphere(new Point(200, 0, -400), 300d).setEmission(new Color(28, 38, 66))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setKt(0.9999999).setShininess(300)),
                //baby sphere
                new Triangle(new Point(0, 20, 300), new Point(30, 30, 300), new Point(20, 50, 300))
                        .setEmission(new Color(red)).setMaterial(new Material().setKs(0.7)),
                new Sphere(new Point(0,0,0),50)   .setEmission(new Color(BLUE)).setMaterial(new Material().setKs(0.7)));

 new AmbientLight(new Color(RED),10);
        scene.lights.add(
                new SpotLight(new Color(ORANGE), new Point(500, -500, -300), new Vector(-1, 0, 0))  // Dark green, bottom center
                        .setKl(1E-5).setKq(1.5E-7));

        scene.lights.add(
                new PointLight(new Color(56, 38, 62), new Vector(1, 1, 1))//purple
                        .setKl(1E-5).setKq(1.5E-7));
        scene.lights.add(
                new SpotLight(new Color(20, 44, 27), new Point(0, -100, 0), new Vector(1, 1, 1))  // Dark green, bottom center
                        .setKl(1E-5).setKq(1.5E-7));
        scene.lights.add(
                new SpotLight(new Color(200, 15, 100), new Point(0, 320, 0), new Vector(1, 1, 1))  // Less intense pink, top center
                        .setKl(1E-5).setKq(1.5E-7));

        scene.lights.add(
                new SpotLight(new Color(RED), new Point(-400, -30, 0), new Vector(1, 1, 1))  // Red, left center
                        .setKl(1E-5).setKq(1.5E-6));
        scene.lights.add(
                new SpotLight(new Color(ORANGE), new Point(100, -300, 0), new Vector(1, 1, 1))  // Orange, bottom right
                        .setKl(1E-5).setKq(1.5E-10));
        scene.lights.add(
                new SpotLight(new Color(13, 138, 29), new Point(-990, -300, 0), new Vector(1, 1, 1))  // Medium green, bottom far left
                        .setKl(1E-5).setKq(1.6E-10));

        scene.background = new Color(10, 0, 26);


        camera.setLocation(new Point(0, -30, 1000)).setVpDistance(1000)
                .setVpSize(1000, 1000)
                .setImageWriter(new ImageWriter("Anti aliasing", 1000, 1000))
                .setNumOfRays(1000)
                .build()
                .renderImage()
                .writeToImage();
    }

}