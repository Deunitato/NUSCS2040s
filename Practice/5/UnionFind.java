/**
 *
 * Algorthim Planning
 * #1
 * Setup:
 * 1. Initialised array of numbers in accending to itself
 * Input:
 * Store query as string, indentify if it is a ? or =
 * Algo
 * Basic Union find
 * 1. get index of b as s
 * 2. Get value of a as k
 * 3. Look up at O(n) for all index with k values
 * 4. Set all indexes with k as values into s
 * Query just return if a and b is the same parent
 * #2 (Time limited exitd)
 * Weighted union

 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.io.BufferedOutputStream;

class UnionFind{

    static int[] myArray;
    static int[] size;
    //static Kattio kattio = new Kattio(System.in, System.out);

    public static void main(String[] args) throws IOException {

        BufferInput bufferInput = new BufferInput();
        //BufferOutput bufferOutput = new BufferOutput();
        //bufferOutput.writeString(Double.toString(bufferInput.nextInt() * 10.4));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int nodes = bufferInput.nextInt();
        int queries = bufferInput.nextInt();
        myArray = new int[nodes];
        size = new int[nodes];
       // populate(nodes);
        populate();

        for(int i = 0 ; i < queries ; i++){
            String command = bufferInput.nextString();
            if(command.equals("?")){ //queryy
                String ans = query(bufferInput.nextInt(), bufferInput.nextInt());
                //kattio.println(ans);
                //kattio.flush();
                out.println(ans);
            }
            else{
                union(bufferInput.nextInt(), bufferInput.nextInt());
            }
        }
        out.flush();
    }


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

    static void populate(){
      //  myArray = IntStream.range(0, n+1).toArray();
        for(int i = 0;  i < myArray.length ; i++){
            myArray[i]= i;
            size[i] = 1;
        }
    }

    static String query(int a, int b){
        a = findRoot(a);
        b = findRoot(b);
        return (myArray[a] == myArray[b])? "yes" : "no";
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