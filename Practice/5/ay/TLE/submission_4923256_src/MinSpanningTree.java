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
                if(o1.source.index != o2.source.index) {
                    return o1.source.index - o2.source.index;
                }

                return o1.target.index-o2.target.index;
            }
        });


        int counter = 0;

        while (io.hasMoreTokens()) {
            int n = io.getInt(); // Number of nodes
            int m = io.getInt(); // Number of edges

            if(n == 0 && m==0) break;

            Graph graph = new Graph(n);
            UnionFindDisjointSet uf = new UnionFindDisjointSet(n);

            // To add all my edges
            for (int i = 0; i < m; i++) {
                graph.addEdge(new Node(io.getInt()),
                        new Node(io.getInt()), io.getInt());
            }

            graph.sort_linkedList();

            for(int i=0; i< graph.mst_edges.size();i++) {
                Edge e = graph.mst_edges.get(i);
                Node endPoint = e.target;
                Node startPoint = e.source;

                if(!uf.isSameSet(endPoint.index, startPoint.index)) {

                    if(final_final_mst_edges.contains(e)) {
                        continue;
                    } else {
                        final_final_mst_edges.add(e);
                        counter += e.weight;
                        uf.union(endPoint.index, startPoint.index);
                    }


                }
            }

            // If the final mst has edges less than (nodes -1)
            if(final_final_mst_edges.size() != (n-1) ) {
                System.out.println("Impossible");
                //final_final_mst_edges.clear();

            } else {
//                int counter = 0;
//                for(Edge k : final_final_mst_edges) {
//                    counter += k.weight;
//                }

                System.out.println(counter);
                counter = 0;

                while(!final_final_mst_edges.isEmpty()) {
                    Edge e = final_final_mst_edges.poll();
                    System.out.println(e.source.index + " " + e.target.index);
                }


            }



        }
    }

}
