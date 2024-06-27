package xmlParser;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import lighting.AmbientLight;
import primitives.Color;
import geometries.Geometries;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point;
import scene.Scene;

public class SceneXMLParser {

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
            NodeList geometriesList = doc.getElementsByTagName("geometries").item(0).getChildNodes();
            for (int i = 0; i < geometriesList.getLength(); i++) {
                Node node = geometriesList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element geometryElement = (Element) node;
                    switch (geometryElement.getTagName()) {
                        case "sphere":
                            Point center = parsePoint(geometryElement.getAttribute("center"));
                            double radius = Double.parseDouble(geometryElement.getAttribute("radius"));
                            geometries.add(new Sphere(center, radius));
                            break;
                        case "triangle":
                            Point p0 = parsePoint(geometryElement.getAttribute("p0"));
                            Point p1 = parsePoint(geometryElement.getAttribute("p1"));
                            Point p2 = parsePoint(geometryElement.getAttribute("p2"));
                            geometries.add(new Triangle(p0, p1, p2));
                            break;
                    }
                }
            }
            scene.setGeometries(geometries);

            return scene;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Color parseColor(String colorStr) {
        String[] rgb = colorStr.split(" ");
        return new Color(Double.parseDouble(rgb[0]), Double.parseDouble(rgb[1]), Double.parseDouble(rgb[2]));
    }

    private static Point parsePoint(String pointStr) {
        String[] coords = pointStr.split(" ");
        return new Point(Double.parseDouble(coords[0]), Double.parseDouble(coords[1]), Double.parseDouble(coords[2]));
    }
}
