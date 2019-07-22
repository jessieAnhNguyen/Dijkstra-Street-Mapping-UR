/*
 * Name: Anh Nguyen
 * NETID: anguy39
 * Email: anguy39@u.rochester.edu
 * Assignment: Project 3
 * Lab session: Monday Wednesday 15:25 - 16:40
 *
 * I DID NOT COLLABORATE WITH ANYONE ON THIS ASSIGNMENT
 */
public class Edge {
    /**
     * This class represents an Edge (or a Road)
     */
    String id;
    Node i1;
    Node i2;
    double weight;

    public Edge(String id, Node i1, Node i2, double weight){
        this.id = id;
        this.i1 = i1;
        this.i2 = i2;
        this.weight = weight;
    }
}
