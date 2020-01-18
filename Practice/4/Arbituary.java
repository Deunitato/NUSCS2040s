import java.util.HashMap;

/*


Algorithm
#1
Setup:
It will be a DAG


Bellman-Ford Algo
1. Run bellManFord on every edge
2. Test for neg cycles -> profit
for (int j = 0; j < num_edges; j++) {
    if (dist.get(edges[j][0]) + weights[j] < dist.get(edges[j][1])) {
        return false;
    }
}
3. If exist neg weight -> arbit 
else ok

 */

public class Arbituary {
	static String currCodes[];
	static String[][] myList; //list of edges
	static double[] myWeights; //list of weights
	static HashMap <String, Double> edges;
	static int[] weightFromStart;
	public static void main(String args[]) {
		try(Kattio kattio = new Kattio(System.in)){
			int currencies = kattio.getInt(); //get input

            while(currencies>0){
                currCodes = new String[currencies];
                //get the codes
				for(int i = 0; i < currencies ; i++){
					currCodes[i] = kattio.getWord();
				}

                int exchangeRates = kattio.getInt();
				myList = new String[exchangeRates][2];
				myWeights = new double[exchangeRates];
                for(int i = 0 ; i < exchangeRates ; i++){
                    myList[i][0] = kattio.getWord();//code 1
                    myList[i][1] = kattio.getWord(); //code 2
                    String[] exhangerates = (kattio.getWord()).split(":"); //gets the colon e.g 8:9
                    myWeights[i] = getWeight(Integer.parseInt(exhangerates[0]) , Integer.parseInt(exhangerates[1]));//first ex rate
                }

                //do arbituate here
				boolean isRaging = bellManFord(currencies, exchangeRates);
				if(isRaging){
					kattio.println("Arbitrage");
				}
				else{
					kattio.println("Ok");
				}
                currencies = kattio.getInt();
            }
			kattio.flush(); //so that it will be delivered to the kattis system, if not flush it will have error in output
		}
	}



    //calculate the weights for an exchange rate
    public static double getWeight(int curr1, int curr2){
        return -(Math.log((double) curr2 / curr1));
	}

	public static boolean relax(int edgeIndex){

		String curr1 = myList[edgeIndex][0];
		String curr2 = myList[edgeIndex][1];
		double edgeWeight = myWeights[edgeIndex];

		double dist1 = edges.get(curr1);
		double dist2 = edges.get(curr2);

		double newDist = dist1 + edgeWeight;

		if(newDist < dist2){ //replace the value
			edges.replace(curr2,newDist);
			return true;
		}
		return false;

	}

	//returns true when arbit is found, else false
	public static boolean bellManFord(int numOfVertices, int numOfEdges){

		edges = new HashMap<>();

		for(String m : currCodes){

			//init here
			for(String s: currCodes){
				edges.put(s, Double.MAX_VALUE);
			}

			edges.replace(m, 0.0);


            //bellmanford
			for(int i =0 ; i < numOfVertices; i++){
				for(int k = 0 ; k < numOfEdges; k++){
					relax(k);
				}
			}

			//test for neg loops
			for(int i = 0 ; i < numOfEdges; i ++){
				if(relax(i)){//found negative cycle
					return true;
				}
			}

		}

		return false;

	}

}