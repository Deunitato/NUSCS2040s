import java.util.ArrayList;

public class Node {

    // Each weighted node has an index,
    // The distance, and what its connected to previously
    public int index;
    public int distance;
    ArrayList<Edge> edges;

    public Node(int index) {
        this.index = index;

        // Set all the distances to be Max_Value or positive infinity
        // For initialisation of bellmanFord
        this.distance = Integer.MAX_VALUE;
        this.edges = new ArrayList<>();
    }


    public Node(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }

}
