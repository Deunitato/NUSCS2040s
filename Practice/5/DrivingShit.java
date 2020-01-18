/**
 * 
 * Algorthim Planning
 * Find the Max min shortest path along the mst
 * #1
 * Setup:
 * Edges list, input into the list
 * - 2 queue, one sort according to
 * - What ever is needed for union
 * Algo
 * 1. Sort the edges in accending
 * 2. Create mst using kruskal
 * 3. Input selected edges into queue (max)
 * 4. Poll the first value and get the edge
 * 
 * 

 */

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Comparator;

class DrivingShit{

    static int[] myArray;
    static PriorityQueue<Edge> myQueue;
    static PriorityQueue<Integer> myBloodyMST;
    static int[] size;
    static Kattio kattio = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        
        
        int nodes = kattio.getInt();
        int edges = kattio.getInt();
        init(nodes);
        for(int i = 0 ; i < edges ; i++){
            myQueue.add(new Edge(kattio.getInt(), kattio.getInt(), kattio.getInt()));
        }
        //copied from mst====
        Kruskal();
        if(check(nodes)){
            System.out.println("IMPOSSIBLE");
        
        }
        else{
        System.out.println(printEdges());
        }
        
        //===============
        
    }

    //copied from my MST code

    static int Kruskal(){
        int weight = 0;

        while(!myQueue.isEmpty()){
            Edge e =  myQueue.poll();
            int parent = e.getParent();
            int child = e.getChild();
            if(!query(parent, child)){
                union(parent,child);
                weight += e.getWeight();
                myBloodyMST.add(e.getWeight());
                //printQueue.add(e);
            }//not inside
            //else ignore
        }

        return weight;
    }

    static int printEdges(){

        return myBloodyMST.poll();
    }

    //returns true when impossible
    static boolean check(int nodes){
        return myBloodyMST.isEmpty() || myBloodyMST.size() != nodes-1;
    }

    //Copied from my union find
    static void union(int a, int b){
        // int s = myArray[b];
         //int change = myArray[a];

        a = findRoot(a);
        b = findRoot(b);
         if(a==b){ //same
            return;
        }
        if(size[a] > size[b]){
            myArray[b] = a;
            size[a] = size[a] + size[b];
        } else {
            myArray[a] = b ;
            size[b] = size[a] + size[b];
        }
    }


    static boolean query(int a, int b){
        a = findRoot(a);
        b = findRoot(b);
        return (myArray[a] == myArray[b]);
    }

    static void init(int nodes){ //expanded populate function
        //max queue in decending order
        myQueue = new PriorityQueue<>();
            myBloodyMST = new PriorityQueue<>(Collections.reverseOrder());
        myArray = new int[nodes];
        size = new int[nodes];

        for(int i = 0;  i < myArray.length ; i++){
            myArray[i]= i;
            size[i] = 1;
        }
    }

    static int findRoot(int a){
        int root = a;
        while(myArray[root] != root){
            myArray[root] = myArray[myArray[root]];
            root = myArray[root];
        }
        return root;
    }

}