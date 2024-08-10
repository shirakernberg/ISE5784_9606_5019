package renderer;

import primitives.*;

import java.util.MissingResourceException;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;
import java.util.stream.*;


/**
 * Camera class represents a camera in 3D space with a position and orientation.
 * It uses the Builder design pattern to construct instances.
 */
public class Camera implements Cloneable {
    // Camera fields
    private Point location;
    private Vector to, up, right;
    private double vpWidth = 0.0;
    private double vpHeight = 0.0;
    private double vpDistance = 0.0;
    int numOfRays=2;
    private double print = 0;

    // Image writer for creating the image file by the camera
    private ImageWriter imageWriter;

    // Ray tracer for casting the rays from the camera
    private RayTracerBase rayTracer;

    private int numOfPointsOnAperture;

    // Private constructor for the Builder to use
    private Camera() {}


    /**
     * Returns a new Bulder instance for creating a Camera.
     * @return A new Camera Builder.
     */
    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * Builder class for constructing Camera instances.
     */
    public static class Builder {
        // Camera instance to build
        final private Camera camera;
        int numOfRays;
        /*
         * number of threads
         */
        private int threads = 0;
        /*
         * interval of debug prints option
         */

        private final int SPARE_THREADS = 2;
        /**
         * Constructor for Builder initializes a new Camera instance.
         */
        public Builder() {
            this.camera = new Camera();
        }

        /**
         * Sets the camera location.
         *
         * @param location Camera location.
         * @return The current Builder instance.
         */
        public Builder setLocation(Point location) {
            if (location == null)
                throw new IllegalArgumentException("Location cannot be null");
            this.camera.location = location;
            return this;
        }

        /**
         * Sets the camera direction vectors.
         *
         * @param to Vector pointing forward.
         * @param up Vector pointing up.
         * @return The current Builder instance.
         */
        public Builder setDirection(Vector to, Vector up) {
            if (to == null || up == null || to.dotProduct(up) != 0)
                throw new IllegalArgumentException("Invalid direction vectors");
            this.camera.to = to.normalize();
            this.camera.up = up.normalize();
            this.camera.right = to.crossProduct(up).normalize();
            return this;
        }

        /**
         * Sets the view plane size.
         *
         * @param vpWidth  View plane width.
         * @param vpHeight View plane height.
         * @return The current Builder instance.
         */
        public Builder setVpSize(double vpWidth, double vpHeight) {
            if (alignZero(vpWidth) <= 0 || alignZero(vpHeight) <= 0)
                throw new IllegalArgumentException("View plane dimensions must be positive");
            this.camera.vpWidth = vpWidth;
            this.camera.vpHeight = vpHeight;
            return this;
        }

        /**
         * Sets the distance from the camera to the view plane.
         * @param vpDistance Distance to the view plane.
         * @return The current Builder instance.
         */
        public Builder setVpDistance(double vpDistance) {
            if (vpDistance <= 0)
                throw new IllegalArgumentException("View plane distance must be positive");
            this.camera.vpDistance = vpDistance;
            return this;
        }

        /**
         * Sets the image writer for the camera.
         * @param imageWriter the image writer
         * @return The current Builder instance.
         */
        public Builder setImageWriter(ImageWriter imageWriter) {
            this.camera.imageWriter = imageWriter;
            return this;
        }

        /**
         * Sets the ray tracer for the camera.
         * @param rayTracer the ray tracer
         * @return The current Builder instance.
         */
        public Builder setRayTracer(RayTracerBase rayTracer) {
            this.camera.rayTracer = rayTracer;
            return this;
        }
        /**
         * setter for multithreading
         *
         * @param threads number of threads
         * @return the updated camera
         */
        public Builder setMultithreading(int threads) {
            if (threads < 0)
                throw new IllegalArgumentException("Multithreading parameter must be 0 or bigger");
            else if (threads > 0)
                this.threads = threads;
            else {
                // number of cores less the spare threads is taken
                int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
                if (cores <= 2)
                    this.threads = 1;
                else
                    this.threads = cores;
            }
            return this;
        }
        /**
         * Translate the camera by a vector.
         * @param translation Vector for translation.
         * @return The current Builder instance.
         */
        public Builder translate(Vector translation) {
            if (translation == null) {
                throw new IllegalArgumentException("Translation vector cannot be null");
            }
            this.camera.location = this.camera.location.add(translation);
            return this;
        }

        /**
         * Rotate the camera around the X-axis.
         * @param angle Angle in radians.
         * @return The current Builder instance.
         */
        public Builder rotateX(double angle) {
            double cosAngle = Math.cos(angle);
            double sinAngle = Math.sin(angle);

            Vector newUp = this.camera.up.scale(cosAngle).add(this.camera.to.scale(sinAngle));
            Vector newTo = this.camera.to.scale(cosAngle).subtract(this.camera.up.scale(sinAngle));

            this.camera.up = newUp.normalize();
            this.camera.to = newTo.normalize();
            this.camera.right = this.camera.to.crossProduct(this.camera.up).normalize();

            return this;
        }

        /**
         * Rotate the camera around the Y-axis.
         * @param angle Angle in radians.
         * @return The current Builder instance.
         */
        public Builder rotateY(double angle) {
            double cosAngle = Math.cos(angle);
            double sinAngle = Math.sin(angle);

            Vector newTo = this.camera.to.scale(cosAngle).add(this.camera.right.scale(sinAngle));
            Vector newRight = this.camera.right.scale(cosAngle).subtract(this.camera.to.scale(sinAngle));

            this.camera.to = newTo.normalize();
            this.camera.right = newRight.normalize();
            this.camera.up = this.camera.right.crossProduct(this.camera.to).normalize();

            return this;
        }

        /**
         * Rotate the camera around the Z-axis.
         * @param angle Angle in radians.
         * @return The current Builder instance.
         */
        public Builder rotateZ(double angle) {
            double cosAngle = Math.cos(angle);
            double sinAngle = Math.sin(angle);

            Vector newRight = this.camera.right.scale(cosAngle).add(this.camera.up.scale(sinAngle));
            Vector newUp = this.camera.up.scale(cosAngle).subtract(this.camera.right.scale(sinAngle));

            this.camera.right = newRight.normalize();
            this.camera.up = newUp.normalize();
            this.camera.to = this.camera.right.crossProduct(this.camera.up).normalize();

            return this;
        }
        /**
         * setter for NumOfRays
         *
         * @param num to ser for NumOfRays
         * @return the updated camera
         */
        public Builder setNumOfRays(int num) {
            this.numOfRays = num;
            return this;
        }
        /**
         * Builds and returns the Camera instance.
         * @return The constructed Camera instance.
         */
        public Camera build() {
            // Check for missing fields
            if (this.camera.location == null) {
                throw new MissingResourceException("Missing required parameter: location", Camera.class.getName(), "location");
            }
            if (this.camera.to == null) {
                throw new MissingResourceException("Missing required parameter: to", Camera.class.getName(), "to");
            }
            if (this.camera.up == null) {
                throw new MissingResourceException("Missing required parameter: up", Camera.class.getName(), "up");
            }
            if (camera.imageWriter == null)
                throw new MissingResourceException("Missing required parameter: image writer", Camera.class.getName(), "image writer");
            if (camera.rayTracer == null)
                throw new MissingResourceException("Missing required parameter: ray tracer", Camera.class.getName(), "ray tracer");

            // Ensure view plane width and height are set
            if (this.camera.vpWidth == 0 || this.camera.vpHeight == 0) {
                throw new IllegalArgumentException("View plane width and height must be set");
            }

            // Ensure view plane distance is set
            if (this.camera.vpDistance == 0) {
                throw new IllegalArgumentException("View plane distance must be set");
            }

            try {
                return (Camera) camera.clone();
            } catch (CloneNotSupportedException ignore) {
                return null;
            }
        }
    }

    /**
     * Constructs a ray through a specific pixel in the view plane.
     * @param nX Number of columns (pixels) in the view plane.
     * @param nY Number of rows (pixels) in the view plane.
     * @param j Column index of the pixel.
     * @param i Row index of the pixel.
     * @return The constructed ray.
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        // Compute the center of the view plane
        Point pc = location.add(to.scale(vpDistance));
        // Pixel size
        double rY = vpHeight / nY;
        double rX = vpWidth / nX;
        // Pixel center
        double yi = (i - (nY - 1) / 2.0) * rY;
        double xj = (j - (nX - 1) / 2.0) * rX;
        Point pij = pc;
        if (!isZero(xj))
            pij = pij.add(right.scale(xj));
        if (!isZero(yi))
            pij = pij.add(up.scale(-yi));
        Vector vij = pij.subtract(location);
        return new Ray(location, vij);
    }

    /**
     * This function renders image's pixel color map from the scene included in the ray tracer object.
     * @return the camera object itself
     */
    public Camera renderImage() {
        int nx = this.imageWriter.getNx();
        int ny = this.imageWriter.getNy();
        PixelManager pixel = new PixelManager(ny, nx, print);
        IntStream.range(0, ny).parallel().forEach(i -> IntStream.range(0, nx).parallel().forEach(j -> {
            this.imageWriter.writePixel(i,j,castRay(nx, ny,i, j));

            pixel.pixelDone();
        }));
        return this;
    }


    /**
     * Create a grid [over the picture] in the pixel color map. Given the grid's step and color.
     * @param step grid's step
     * @param color grid's color
     * @return the camera object itself
     */
    public Camera printGrid(int step, Color color) {
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        for (int i = 0; i < nY; ++i) {
            for (int j = 0; j < nX; ++j) {
                if (j % step == 0 || i % step == 0) {
                    imageWriter.writePixel(j, i, color);
                }
            }
        }
        return this;
    }

    /**
     * Produce a rendered image file.
     * @return the camera object itself
     */
    public Camera writeToImage() {
        imageWriter.writeToImage();
        return this;
    }

    /**
     * Cast ray from camera in order to color a pixel.
     *
     * @param nX resolution on X axis (number of pixels in row)
     * @param nY resolution on Y axis (number of pixels in column)
     *           //     * @param col pixel's column number (pixel index in row)
     *           //     * @param row pixel's row number (pixel index in column)
     * @return
     */
//    private void castRay(int nX, int nY, int col, int row) {
//        imageWriter.writePixel(col, row, rayTracer.traceRay(constructRay(nX, nY, col, row)));
//
//    }

    private Color castRay(int nX, int nY, int j, int i) {
        // Initialize the color sum to black.
        Color sum = new Color(0, 0, 0);

        // Loop through each sample within the pixel.
        for (int k = 0; k < numOfRays * numOfRays; ++k) {
            // Calculate the X and Y offsets for the current sample.
            double x = j + (k % numOfRays + (Math.random() - 0.5)) / (double) numOfRays;
            double y = i + (k / numOfRays + (Math.random() - 0.5)) / (double) numOfRays;

            // Construct the ray for the current sample.
            Ray ray = constructRay(nX, nY,(int)x, (int)y);

            // Trace the ray and get its color.
            Color color = rayTracer.traceRay(ray);

            // Add the color to the sum.
            sum = sum.add(color);
        }

        // Calculate the average color for the pixel by scaling the sum.
        Color color = sum.scale(1d / (numOfRays * numOfRays));

        // Write the average color to the pixel in the image.
        imageWriter.writePixel(j, i, color);

        return color;
    }

}
