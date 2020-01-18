import java.util.Comparator;
import java.util.PriorityQueue;

public class MinSpanningTree {


    /* Main gist of the code,
    Have Graph class, which consists of Nodes and Edges.
    Edge class contains the source node, target node, and the weight of the edge
    Node class just contains the index, ignore the distance, thats for previous PS

    Get the inp
     */

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        PriorityQueue<Edge> final_final_mst_edges = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if (o1.source.index != o2.source.index) {
                    return o1.source.index - o2.source.index;
                }

                return o1.target.index - o2.target.index;
            }
        });

        PriorityQueue<Edge> all_edges = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });


        int counter = 0;

        while (io.hasMoreTokens()) {
            counter = 0;
            int n = io.getInt(); // Number of nodes
            int m = io.getInt(); // Number of edges

            if (n == 0 && m == 0) break;

            if(n>m+1) {
                System.out.println("Impossible");
                continue;
            }

            //  Graph graph = new Graph(n);
            UnionFindDisjointSet uf = new UnionFindDisjointSet(n);

            // To add all my edges
            for (int i = 0; i < m; i++) {
                int first = io.getInt();
                int second = io.getInt();

                // 3 , 1
                if(first > second) {
                    int temp = second;
                    second = first;
                    first = temp;

                }
                all_edges.add(new Edge(new Node(first), new Node(second), io.getInt()));
            }


            // If number of nodes > number of edges +1


                int index = 0;
                while (index < (n - 1)) {
                    Edge edge = all_edges.remove();
                    if (!uf.isSameSet(edge.source.index, edge.target.index)) {
                        final_final_mst_edges.add(edge);
                        counter += edge.weight;
                        index++;
                        uf.union(edge.source.index, edge.target.index);
                    }
                }

           // System.out.println("The size is " + );

                // If the final mst has edges less than (nodes -1)
                if (final_final_mst_edges.size() != (n - 1)) {
                    System.out.println("Impossible");
                    //final_final_mst_edges.clear();

                } else {
                    System.out.println(counter);
                    counter = 0;

                    while (!final_final_mst_edges.isEmpty()) {
                        Edge e = final_final_mst_edges.poll();
                        System.out.println(e.source.index + " " + e.target.index);
                    }


                   // final_final_mst_edges.clear();
                    all_edges.clear();
                }


            }
        }
    }


