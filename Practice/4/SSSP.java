import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.ArrayList;

/*


Algorithm
#1 Dijkstra's Algo



 */

public class SSSP{
	static int[] weightFromStart;
    static ArrayList<Edge>[] myList;
    
	public static void main(String args[]) {
		try(Kattio kattio = new Kattio(System.in)){
			while(true){
				int verticeNum = kattio.getInt();
				int edgeNum = kattio.getInt();
				int queryNum = kattio.getInt();
                int startIndex = kattio.getInt();
                weightFromStart = new int[verticeNum];

                if(verticeNum==0 && edgeNum==0 && queryNum==0 && startIndex==0){break;}
                myList = new ArrayList[verticeNum]; 
                //myList = new ArrayList[];
                
                //Populate the list with array lists
				for(int i = 0 ; i < verticeNum ; i++){
                    myList[i] = new ArrayList<Edge>();
                }
                
                
				//user input for new edges
				for(int i = 0 ; i< edgeNum; i++){
                    int source = kattio.getInt();
                    int vertice = kattio.getInt();
                    int weight = kattio.getInt();
					(myList[source]).add(new Edge(vertice, weight));
                }

				Dijkstra(verticeNum, edgeNum, startIndex);

				for(int i = 0; i < queryNum; i++){
					int end = kattio.getInt();
					if(end > verticeNum){
						kattio.println("Impossible");
                        continue;
                    }
					kattio.println(result(end));
				}

				kattio.println("");
			}

			kattio.flush(); //so that it will be delivered to the kattis system, if not flush it will have error in output
		}
    }

	public static String result(int query){

		int finalWeight = weightFromStart[query];
		Integer finalWeightinInt = new Integer(finalWeight);
		if(finalWeightinInt.equals(Integer.MAX_VALUE)){
			return "Impossible";
		}
		else {
			return String.valueOf(finalWeight);
		}
    }
    
    public static boolean relax(Edge e, int source){
		int sourceWeight = weightFromStart[source];
		int targetNEWDistance = sourceWeight + e.getWeight();
        int targetPrevWeight = weightFromStart[e.getVertice()];
		if(targetNEWDistance < targetPrevWeight){ //if my new weight is smaller than prev
			weightFromStart[e.getVertice()] = targetNEWDistance;
            return true;
        }
        return false;
	}

    /*Dijkstra(G, s)
  create vertex set Q
  for each v in G
       dist[v] = INFTY
       prev[v] = UNDEF    //Store path
   dist[s] = 0
   while Q is not empty
        u = vertex in Q with min dist[u]
        remove u from Q
        for each neighbor v of u:
               relax(u,v)	
    return dist, prev  */ 
    
    public static void Dijkstra(int verticeNum, int edgeNum, int start){
        PriorityQueue<Edge> nodes = new PriorityQueue<>();
        for(int i = 0 ; i < verticeNum ; i ++){
            weightFromStart[i] = Integer.MAX_VALUE;
        }

		weightFromStart[start] = 0;
		nodes.add(new Edge(start, 0));
		
        while(!nodes.isEmpty()){
            Edge curr = nodes.poll();
            //for(int v = 0 ; v < verticeNum ; v++){
			int vert = curr.getVertice();
			for(Edge e : myList[vert]){
				if(relax(e, vert)){
                    nodes.add(new Edge(e.getVertice(), weightFromStart[e.getVertice()])); //add only if path is edited
                }
			}
            //}

        }
        
    }
}