import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

class Graph {

    // What does a Graph have?

    // An edge and a node right?

    // Have an arrayList of the various nodes
    // Then chain the Edges to the various nodes

    // Number of vertices!
    int vertex;
    ArrayList<Node> verticeList = new ArrayList<>();
    private LinkedList<Edge>[] adjacencylist;
    ArrayList<Edge> mst_edges = new ArrayList<>();

    Graph(int vertex) {
        this.vertex = vertex;
        this.adjacencylist = new LinkedList[vertex];

        for (int i = 0; i < vertex; i++) {
            verticeList.add(new Node(i));
            adjacencylist[i] = new LinkedList<>();
        }
    }

    void addEdge(Node source, Node destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        mst_edges.add(edge);
        adjacencylist[source.index].addFirst(edge);
    }

    int addVertice (int s) {
        verticeList.add(new Node(s));
        adjacencylist[verticeList.size()-1] = new LinkedList<>();
        return verticeList.size()-1;
    }

    ArrayList<Node> getVerticeList() {
        return verticeList;
    }

    Node getVertex(int vertex) {

        Node vertex_a = null;

        for (Node a : verticeList) {
            if (vertex == a.index) {
                vertex_a = a;
            }
        }
        return vertex_a;
    }

    LinkedList<Edge>[] getAdjacencylist() {
        return adjacencylist;
    }

    LinkedList<Edge> getAdjacencyList2(int index) {
        return adjacencylist[index];
    }

    void sort_linkedList () {
        mst_edges.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });

    }


}
