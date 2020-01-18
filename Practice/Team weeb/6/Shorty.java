import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.ArrayList;

/*


Algorithm
#1 Dijkstra's Algo



 */

public class Shorty{
	static double[] weightFromStart;
	static ArrayList<Edge>[] myList;
	static double finalWeight;
    
	public static void main(String args[]) {
		try(Kattio kattio = new Kattio(System.in)){
			while(true){
				int verticeNum = kattio.getInt();
				int edgeNum = kattio.getInt();
				weightFromStart = new double[verticeNum];
				finalWeight = 1.0;

                if(verticeNum==0 && edgeNum==0){break;}
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
					double weight = -Math.log(kattio.getDouble());
                    (myList[source]).add(new Edge(vertice, weight));
                    (myList[vertice]).add(new Edge(source, weight));
                }

				Dijkstra(verticeNum, edgeNum, 0);

				double ans = weightFromStart[verticeNum-1];

				kattio.println(String.format("%.4f", Math.pow(Math.E, -ans)));

				kattio.println("");
			}

			kattio.flush(); //so that it will be delivered to the kattis system, if not flush it will have error in output
		}
    }
    
    public static boolean relax(Edge e, int source){
		double sourceWeight = weightFromStart[source];
		double targetNEWDistance = sourceWeight + e.getWeight();
		//System.out.println("tand"+targetNEWDistance);
        double targetPrevWeight = weightFromStart[e.getVertice()];
		if(targetNEWDistance < targetPrevWeight){ //if my new weight is smaller than prev
            weightFromStart[e.getVertice()] = targetNEWDistance;
            //finalWeight *= Math.pow(2, -targetNEWDistance);
            return true;
        }
        return false;
	}
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