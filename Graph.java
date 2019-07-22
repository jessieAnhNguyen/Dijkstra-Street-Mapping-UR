/*
 * Name: Anh Nguyen
 * NETID: anguy39
 * Email: anguy39@u.rochester.edu
 * Assignment: Project 3
 * Lab session: Monday Wednesday 15:25 - 16:40
 *
 * I DID NOT COLLABORATE WITH ANYONE ON THIS ASSIGNMENT
 */
import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Graph {
    /**
     * This class represents a Graph. It contains the method constructGraph() to read the input data and build the graph
     */
    HashMap<String, Node> vSet = new HashMap<>(); //store the nodes of the graph

    public void constructGraph(String filename){
        /*
         * This method reads the data from the input file, and construct the graph
         */
        File file = new File(filename);
        Scanner sc = null;
        try{
            sc = new Scanner(file);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        String input1;
        String input2;
        String input3;
        String input4;

        while (sc.hasNextLine()){
            input1 = sc.next();
            input2 = sc.next();
            input3 = sc.next();
            input4 = sc.next();

            //If the input is an intersection
            if (input1.equals("i")){
                Node v = new Node(input2, Double.parseDouble(input3), Double.parseDouble(input4));
                vSet.put(input2, v);
            }
            //If the input is a road
            else if (input1.equals("r")){
                Node i1 = vSet.get(input3);
                Node i2 = vSet.get(input4);
                double weight = haversine(i1, i2); //use haversine formula to calculate the weight

                Edge e = new Edge(input2, i1, i2, weight);
                i1.adjList.put(i2.id, e);
                Edge f = new Edge(input2, i2, i1, weight);
                i2.adjList.put(i1.id, f);
            }
        }
    }


    public double haversine(Node i1, Node i2) {
        /*
         * This method uses the Haversine formula to find distance between two intersections
         * Retrieved from: https://en.wikipedia.org/wiki/Haversine_formula
         */
        double earthRadius = 6356.752; //km
        double latitude1R = Math.toRadians(i1.latitude);
        double latitude2R = Math.toRadians(i2.latitude);
        double longitude1R = Math.toRadians(i1.longitude);
        double longitude2R = Math.toRadians(i2.longitude);

        return 2.0 * earthRadius * Math.asin(Math.sqrt(
                Math.pow(Math.sin((latitude2R - latitude1R) / 2), 2) + Math.cos(latitude1R) * Math.cos(latitude2R) * Math.pow(Math.sin((longitude2R - longitude1R) / 2), 2)
        ));
    }

}
