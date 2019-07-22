/*
 * Name: Anh Nguyen
 * NETID: anguy39
 * Email: anguy39@u.rochester.edu
 * Assignment: Project 3
 * Lab session: Monday Wednesday 15:25 - 16:40
 *
 * I DID NOT COLLABORATE WITH ANYONE ON THIS ASSIGNMENT
 */
import java.util.HashMap;

public class Node {
    /**
     * This class represents a Node (or an Intersection)
     */
    String id;
    double latitude;
    double longitude;
    HashMap<String, Edge> adjList; //store the adj edges

    public Node(String id, double latitude, double longitude){
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        adjList = new HashMap<>();
    }
}
