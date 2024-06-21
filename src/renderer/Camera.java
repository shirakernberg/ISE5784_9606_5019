package renderer;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.MissingResourceException;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Camera class represents a camera in 3D space with a position and orientation.
 * It uses the Builder design pattern to construct instances.
 */
public class Camera implements Cloneable {
    // Camera fields
    // Location of the camera
    private Point location;
    // Direction vectors
    private Vector to, up, right;
    // View plane dimensions
    private double vpWidth = 0.0;
    private double vpHeight = 0.0;
    private double vpDistance = 0.0;

    // Private constructor for the Builder to use
    private Camera() {}

    /**
     * Returns a new Builder instance for creating a Camera.
     *
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

        /**
         * Constructor for Builder initializes a new Camera instance.
         */
        public Builder() {
            this.camera = new Camera();
        }

        /**
         * Constructor for Builder initializes a new Camera instance.
         *
         * @param camera Camera instance to copy.
         */
        public Builder(Camera camera) {
            this.camera = camera;
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
         *
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
         * Builds and returns the Camera instance.
         *
         * @return The constructed Camera instance.
         */
        public Camera build()  {
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
     *
     * @param nX Number of columns (pixels) in the view plane.
     * @param nY Number of rows (pixels) in the view plane.
     * @param j Column index of the pixel.
     * @param i Row index of the pixel.
     * @return The constructed ray.
     */
    public Ray constructRay(int nX, int nY, int j, int i)  {
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

    // Getter and Setter methods for all fields

    public Point getLocation() {
        return location;
    }

    public Vector getTo() {
        return to;
    }

    public Vector getUp() {
        return up;
    }

    public Vector getRight() {
        return right;
    }

    public double getVpWidth() {
        return vpWidth;
    }

    public double getVpHeight() {
        return vpHeight;
    }

    public double getVpDistance() {
        return vpDistance;
    }

}

