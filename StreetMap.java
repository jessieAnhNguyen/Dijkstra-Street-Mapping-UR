/*
 * Name: Anh Nguyen
 * NETID: anguy39
 * Email: anguy39@u.rochester.edu
 * Assignment: Project 3
 * Lab session: Monday Wednesday 15:25 - 16:40
 *
 * I DID NOT COLLABORATE WITH ANYONE ON THIS ASSIGNMENT
 */
import java.util.*;

public class StreetMap {
    /**
     * This class is the main class. The main method reads the command line and run the program.
     * It also contains the findShortestPath() method using Dijkstra's algorithm
     */

    static Set<String> roadPath; //store the roads that lead to the shortest path

    public static void main(String[] args){
        StreetMap streetMap = new StreetMap();
        Graph graph = new Graph();
        graph.constructGraph(args[0]); //call constructGraph to build the graph

        boolean show = false; //true if command contains "show"
        boolean direct = false; //false if command contains "directions"

        String node1 = "";
        String node2 = "";

        for (int i = 0; i < args.length; i++){
            if (args[i].equals("--show")){
                show = true;
            }
            else if (args[i].equals("--directions")){
                direct = true;
                node1 = args[i+1];
                node2 = args[i+2];
            }
        }

        if(direct){
            streetMap.findShortestPath(graph, node1, node2); // call findShortestPath() method to find the shortest path
        }
        if ((show == true) && (direct != true)) {
            new GUI(graph.vSet, new HashSet<>()); //draw the map
        }
        else if (show == true){
            new GUI(graph.vSet, StreetMap.roadPath); //draw the map and highlight the shortest path
        }

    }

    public ArrayList<String> findShortestPath(Graph graph, String source, String target){
        /*
         * This method finds the shortest path between two intersections using Dijkstra's algorithm.
         * It returns the list of intersections that lead to the destination
         */
        HashMap<String,Integer> map1 = new HashMap<>();
        HashMap<Double,Integer> map2 = new HashMap<>();

        String[] findValue = new String[graph.vSet.size()];
        double[] distance = new double[graph.vSet.size()]; //store the shortest distance to each node
        boolean[] visited = new boolean[graph.vSet.size()];
        int[] parent = new int[graph.vSet.size()]; //store the parents

        ArrayList<String> shortestPath = new ArrayList<>(); //list of intersections that lead to the destination
        roadPath = new HashSet<>(); //set of roads that lead to the shortest path

        int i = 0;
        //Initialize the nodes' attributes
        for (String key : graph.vSet.keySet()){
            map1.put(key,i);
            distance[i] = Double.MAX_VALUE;
            findValue[i] = key;
            visited[i] = false;
            i++;
        }

        // Check if two input locations are valid
        if (!map1.containsKey(source) || !map1.containsKey(target)){
            System.out.println("You've entered the invalid locations!");
            System.exit(0);
            return null;
        }

        else {
            //Initialize the source
            parent[map1.get(source)] = -1;
            distance[map1.get(source)] = 0;
            map2.put(0.0, map1.get(source));

            //Create a priority queue
            PriorityQueue<Double> pq = new PriorityQueue<>();
            pq.add(0.0);

            while (!visited[map1.get(target)]) {
                int u = 0;
                if (!pq.isEmpty()) {
                    u = map2.get(pq.poll()); //get the node with the shortest distance
                }
                visited[u] = true; //change visited to true

                //Check if the destination is reachable
                if (distance[u] == Double.MAX_VALUE) {
                    System.out.println("This destination is unreachable from " + source);
                    System.exit(0);
                    return null;
                }

                HashMap<String, Edge> edges = graph.vSet.get(findValue[u]).adjList; //the set of adj edges

                //Iterate through each adj edge, relax the edge when possible
                for (String key: edges.keySet()) {
                    if ((!visited[map1.get(key)]) && ((distance[u] + edges.get(key).weight) < distance[map1.get(key)])) {
                        distance[map1.get(key)] = distance[u] + edges.get(key).weight;
                        parent[map1.get(key)] = u;
                        pq.add(distance[map1.get(key)]);
                        map2.put(distance[map1.get(key)], map1.get(key));
                    }
                }
            }

            //Trace back the shortest path
            String s = target;
            while (parent[map1.get(s)] != -1) {
                shortestPath.add(0, s); //add node to the shortest path
                roadPath.add(graph.vSet.get(s).adjList.get(findValue[parent[map1.get(s)]]).id); //add edge to the shortest path
                s = findValue[parent[map1.get(s)]];
            }
            shortestPath.add(0, source);

            //Print out the nodes that lead to the shortest path
            System.out.print("The shortest path from " + source + " to " + target + " is: ");
            for (int l = 0; l < shortestPath.size(); l++) {
                System.out.print(shortestPath.get(l) + " ");
            }
            System.out.println();

            //Print out the shortest distance
            System.out.println("The total distance is " + distance[map1.get(target)] + " km.");
            return shortestPath; //return the ArrayList of intersections to reach the destination
        }
    }
}
