/**
 *
 * Algorthim Planning
 * #1
 * SetUp:
 *  Queue to sort the edges for min span tree
 * Store edges into queue
 * Storage: Edge list
 *
 * Algo
 * 1. Sort edge
 * 2. Try create min span graph containing all nodes
 * if(not all nodes included ie size) => Return "impossible"
 * else continue
 * 3. As create minspan graph, record the edges in order in list(maybe in string form)
 * 4. Sum the weights up as the min span graph continues
 * 
 * #2 wrong answer:
 * fixes:
 * ensure the smaller node is put in first
 *

 */

import java.util.PriorityQueue;
import java.util.Comparator;

class MST{

    static PriorityQueue<Edge> myQueue;
    static PriorityQueue<Edge> printQueue;

    static int[] myArray;
    static int[] size;
    static Kattio kattio = new Kattio(System.in, System.out);

    public static void main(String[] args) {

        while(true){

            int nodes = kattio.getInt();
            int edges = kattio.getInt();
            if(nodes == 0 && edges == 0){break;}

            //copy from unionfind
            myArray = new int[nodes];
            size = new int[nodes];
            populate();

            //======================
            myQueue = new PriorityQueue<>();
            printQueue = new PriorityQueue<>(new Comparator<Edge>() {
                @Override
                public int compare(Edge e1, Edge e2) {
                //return left.weight.compareTo(right.weight);
                    if(e1.getParent() != e2.getParent()){
                        return Integer.compare(e1.getParent(), e2.getParent());
                    }
                    else{
                        return Integer.compare(e1.getChild(), e2.getChild());
                    }
                }
            });
            //======================

            for(int i = 0 ; i < edges ; i++){
                int e1 = kattio.getInt();
                int e2 = kattio.getInt();
                int weight = kattio.getInt();
                if(e1 > e2){
                    int temp = e1;
                    e1 =  e2;
                    e2 = temp;
                }
                myQueue.add(new Edge(e1, e2, weight));
            }

            int weight = Kruskal();
            if(check(nodes)){
                System.out.println("Impossible");
                continue;
            }
            kattio.println(weight);
            printEdges();
            kattio.flush();
        }


    }


    static int Kruskal(){
        int weight = 0;

        while(!myQueue.isEmpty()){
            Edge e =  myQueue.poll();
            int parent = e.getParent();
            int child = e.getChild();
            if(!query(parent, child)){
                union(parent,child);
                weight += e.getWeight();
                printQueue.add(e);
            }//not inside
            //else ignore
        }

        return weight;
    }

    static void printEdges(){

        while(!printQueue.isEmpty()){
            Edge e = printQueue.poll();
            kattio.println(e.getParent() + " " + e.getChild());
        }
    }

    //returns true when impossible
    static boolean check(int nodes){
        return printQueue.isEmpty() || printQueue.size() != nodes-1;
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

    static void populate(){
        //  myArray = IntStream.range(0, n+1).toArray();
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