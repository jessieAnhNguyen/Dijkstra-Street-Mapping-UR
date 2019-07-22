/*
 * Name: Anh Nguyen
 * NETID: anguy39
 * Email: anguy39@u.rochester.edu
 * Assignment: Project 3
 * Lab session: Monday Wednesday 15:25 - 16:40
 *
 * I DID NOT COLLABORATE WITH ANYONE ON THIS ASSIGNMENT
 */
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Set;

public class GUI extends JPanel {
    /**
     * This class uses Java Graphics to draw the map, and highlight the shortest path
     */

    HashMap<String, Node> vSet; //store the nodes
    double minLatitude, maxLatitude, minLongitude, maxLongitude; //variables that represent the max, min latitude, longitude of the nodes
    Set<String> roadPath; //store the edges of the shortest path

    //Constructor
    public GUI(HashMap<String, Node> vSet, Set<String> roadPath) throws HeadlessException {
        this.vSet = vSet;
        this.roadPath = roadPath;

        minLatitude = 180;
        maxLatitude = -180;
        minLongitude = 180;
        maxLongitude = -180;
        //Find the max, min latitude, longitude of the nodes
        for (String key : vSet.keySet()) {
            Node intersection = vSet.get(key);

            minLatitude = Math.min(minLatitude, intersection.latitude);
            maxLatitude = Math.max(maxLatitude, intersection.latitude);
            minLongitude = Math.min(minLongitude, intersection.longitude);
            maxLongitude = Math.max(maxLongitude, intersection.longitude);
        }

        //Create the frame
        JFrame frame = new JFrame();
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(this); //add the canvas
    }

    //calculate the latitude displayed on canvas
    public int canvasLatitude(double latitude) {
        return (int) ((maxLatitude - latitude) * this.getHeight() / (maxLatitude - minLatitude));
    }

    //calculate the longitude displayed on canvas
    public int canvasLongitude(double longitude) {
        return (int) ((longitude - minLongitude) * this.getWidth() / (maxLongitude - minLongitude));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        Graphics2D g2D = (Graphics2D) g;

        for (String key : vSet.keySet()) {
            Node node = vSet.get(key);

            for (String key1 : node.adjList.keySet()) {
                Edge edge = node.adjList.get(key1);
                //Draw the roads
                g2D.drawLine(
                        canvasLongitude(node.longitude),
                        canvasLatitude(node.latitude),
                        canvasLongitude(vSet.get(edge.i2.id).longitude),
                        canvasLatitude(vSet.get(edge.i2.id).latitude));

                //highlight the shortest path with red
                if (roadPath.contains(edge.id)) {
                    g2D.setColor(Color.RED);
                    g2D.setStroke(new BasicStroke(3));

                    g2D.drawLine(
                            canvasLongitude(node.longitude),
                            canvasLatitude(node.latitude),
                            canvasLongitude(vSet.get(edge.i2.id).longitude),
                            canvasLatitude(vSet.get(edge.i2.id).latitude));

                    g2D.setColor(Color.BLACK);
                    g2D.setStroke(new BasicStroke(1));
                }
            }
        }
    }
}
