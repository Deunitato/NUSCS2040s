public class UnionFindDisjointSet {

    //////////////////////////////////////////////////////
    // Declare any additional variables as you see fit. //
    //////////////////////////////////////////////////////

    int[] parent = new int[10000000];
    int[] size = new int [10000000];

    /**
     * Constructs a new Union-Find Disjoint Set with the specified number of vertices,
     * indexed from 0 to (numVertices - 1).
     *
     * @param numVertices The number of vertices in this Union-Find Disjoint Set
     */
    public UnionFindDisjointSet(int numVertices) {

        // For the vertices,
        for(int i=0; i< numVertices; i++) {
            parent[i] = i;
            // Initialize every single node to be 0
            size[i] = 0;

        }


        //////////////////////////////////
        // Implement your solution here //
        //////////////////////////////////
    }

    /**
     * Unions the set containing vertex u with the set containing vertex v. If both
     * vertices u and v are in the same set to begin with, the sets should remain
     * unchanged.
     */
    public void union(int u, int v) {
        //////////////////////////////////
        // Implement your solution here //
        //////////////////////////////////

        int root_u = findRoot(u);
        int root_v = findRoot(v);



        if(root_u != root_v) {

            if(size[root_u] > size[root_v]) {
                parent[root_v] = root_u;
                size[root_u] += size[root_v];
            }
            else {
                parent[root_u] = root_v;
                size[root_v] += size[root_u];
            }
        }



    }

    /**
     * Checks if vertices u and v belong to the same set.
     *
     * @return True if vertices u and v belong to the same set, false otherwise.
     */
    public boolean isSameSet(int u, int v) {

        while(parent[u] != u) u = parent[u];
        while(parent[v] != v) v = parent[v];
        return (findRoot(u)==findRoot(v));

      //  return parent[u] == parent[v];
//        if(parent[u] == parent [v])  {
//            return true;
//        }
//
//
//        //////////////////////////////////
//        // Implement your solution here //
//        //////////////////////////////////
//        return false;
    }

    public int findRoot(int p) {
        int root = p;
        while(parent[root] != root) root = parent[root];

        while(parent[p] != p ) {
            int temp = parent[p];
            parent[p] = root;
            p = temp;
        }

        return root;
    }




}
