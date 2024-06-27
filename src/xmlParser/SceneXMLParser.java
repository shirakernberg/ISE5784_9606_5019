package xmlParser;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import lighting.AmbientLight;
import primitives.Color;
import geometries.Geometries;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point;
import scene.Scene;

/**
 * A utility class to parse XML files and load scene objects from them.
 */
public class SceneXMLParser {

    /**
     * Loads a scene from an XML file.
     *
     * @param filePath the path to the XML file
     * @return the Scene object created from the XML file
     */
    public static Scene loadSceneFromFile(String filePath) {
        try {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            String sceneName = "Scene from XML";
            Scene scene = new Scene(sceneName);

            // Parse background color
            Element sceneElement = (Element) doc.getElementsByTagName("scene").item(0);
            String backgroundColor = sceneElement.getAttribute("background-color");
            scene.setBackground(parseColor(backgroundColor));

            // Parse ambient light
            Element ambientLightElement = (Element) doc.getElementsByTagName("ambient-light").item(0);
            String ambientLightColor = ambientLightElement.getAttribute("color");
            AmbientLight ambientLight = new AmbientLight(parseColor(ambientLightColor), 1.0);
            scene.setAmbientLight(ambientLight);

            // Parse geometries
            Geometries geometries = new Geometries();
            NodeList spheresList = doc.getElementsByTagName("sphere");
            for (int i = 0; i < spheresList.getLength(); i++) {
                Element sphereElement = (Element) spheresList.item(i);
                Point center = parsePoint(sphereElement.getAttribute("center"));
                double radius = Double.parseDouble(sphereElement.getAttribute("radius"));
                geometries.add(new Sphere(center, radius));
            }

            NodeList trianglesList = doc.getElementsByTagName("triangle");
            for (int i = 0; i < trianglesList.getLength(); i++) {
                Element triangleElement = (Element) trianglesList.item(i);
                Point p0 = parsePoint(triangleElement.getAttribute("p0"));
                Point p1 = parsePoint(triangleElement.getAttribute("p1"));
                Point p2 = parsePoint(triangleElement.getAttribute("p2"));
                geometries.add(new Triangle(p0, p1, p2));
            }

            scene.setGeometries(geometries);

            return scene;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Parses a color from a string.
     *
     * @param colorStr the string representing the color in the format "r g b"
     * @return the Color object
     */
    private static Color parseColor(String colorStr) {
        String[] rgb = colorStr.split(" ");
        return new Color(Double.parseDouble(rgb[0]), Double.parseDouble(rgb[1]), Double.parseDouble(rgb[2]));
    }

    /**
     * Parses a point from a string.
     *
     * @param pointStr the string representing the point in the format "x y z"
     * @return the Point object
     */
    private static Point parsePoint(String pointStr) {
        String[] coords = pointStr.split(" ");
        return new Point(Double.parseDouble(coords[0]), Double.parseDouble(coords[1]), Double.parseDouble(coords[2]));
    }
}
