/*

Algorithm
#1
setup
hashmap of arrays
Key -> vertices
value-> arrays of pairs
Pair-> an object that contain the vertices connected to the key

#2
setup
store edges
edges -> contain source, vertice, weight

2. Running
Run bellmanford algo
Run through all edges again to check if theres a change
change = negative cycle

Optimisation:
change to array
changes objects to array
run bellmanford once each test case rather then every testcase

Runtime errors:
- Array out of boundsc

 */

public class SSSP_negWeights {
	//static HashMap<Integer, ArrayList<weightedPairs>> myList = new HashMap<>();
	//static HashMap<Integer, Integer> weightFromStart;

	static int[] weightFromStart;
	//static ArrayList<weightedPairs> myList;
	static int[][] myList;
	static boolean[] negCycles;
	public static void main(String args[]) {
		try(Kattio kattio = new Kattio(System.in)){
			while(true){
				int verticeNum = kattio.getInt();
				int edgeNum = kattio.getInt();
				int queryNum = kattio.getInt();
				int startIndex = kattio.getInt();

				if(verticeNum==0 && edgeNum==0 && queryNum==0 && startIndex==0){break;}
				//myList = new ArrayList<>();
				myList = new int[edgeNum][3];
				negCycles = new boolean[verticeNum];
				initializeAllWeights(verticeNum);
				weightFromStart[startIndex] =  0;

				for(int i = 0 ; i< edgeNum; i++){
					myList[i][0]= kattio.getInt(); //source
					myList[i][1] = kattio.getInt(); //target
					myList[i][2]= kattio.getInt(); //weight
					//weightedPairs pair = new weightedPairs(firstNode, secondNode, weight);
					//myList.add(pair);
				}

				BellManFord(verticeNum, edgeNum);

				for(int i = 0; i < queryNum; i++){
					int end = kattio.getInt();
					if(end > verticeNum){
						kattio.println("Impossible");
						continue;
					}
					//weightFromStart.replace(startIndex, 0);
					kattio.println(result(end, edgeNum));
				}

				kattio.println("");
				kattio.flush(); //so that it will be delivered to the kattis system, if not flush it will have error in output
			}

		}
	}

	public static void initializeAllWeights(int verticeNum){
		//weightFromStart = new HashMap<>();
		weightFromStart = new int[verticeNum] ;
		for(int i=0; i<verticeNum; i++){
			//weightFromStart.put(i, Integer.MAX_VALUE);
			weightFromStart[i]= Integer.MAX_VALUE;
			negCycles[i] = false;
		}
	}

	public static boolean relax(int[] pair){
		//int node1 = pair.getSource();
		int node1 = pair[0];
		//int node1Distance = weightFromStart.get(node1);
		int node1Distance = weightFromStart[node1];
		int node2 = pair[1];
		//int node2 = pair.getVertice();
		//int node2Distance = weightFromStart.get(node2);
		int node2Distance = weightFromStart[node2];
		//int newWeight = pair.getWeight() + node1Distance;
		int newWeight = pair[2] + node1Distance;
		if(node1Distance != Integer.MAX_VALUE && node2Distance > newWeight){
			//weightFromStart.replace(node2, newWeight);
			weightFromStart[node2] = newWeight;
			return true;
		}
		return false;
	}

	public static void BellManFord(int numOfVertices, int numOfEdges){

		for(int i =0 ; i < numOfVertices-1 ; i++){
			//boolean isStillUpdating = false;
			for(int j = 0 ; j < numOfEdges; j++){
				//isStillUpdating = relax(myList.get(k)) || isStillUpdating;
				//isStillUpdating = relax(myList[k]) || isStillUpdating;
				relax(myList[j]);
			}
			// if(isStillUpdating == false){
			// 	break;
			// }
		}

		for(int i =0 ; i < numOfVertices-1 ; i++){
			//boolean isStillUpdating = false;
			for(int j = 0 ; j < numOfEdges; j++){
				//isStillUpdating = relax(myList.get(k)) || isStillUpdating;
				//isStillUpdating = relax(myList[k]) || isStillUpdating;
				if(negCycles[myList[j][0]]){ //if my source is neg
					//set target to true
					int targetAffected = myList[j][1];
                    negCycles[targetAffected] = true;
				}
				else if(relax(myList[j])){
					int targetAffected = myList[j][1];
                    negCycles[targetAffected] = true;
				}
			}
			// if(isStillUpdating == false){
			// 	break;
			// }
		}
		//int finalWeight = weightFromStart.get(end);
	}

	//test for neg loops
	public static String result(int end, int numOfEdges){
		int finalWeight = weightFromStart[end];
		Integer finalWeightinInt = new Integer(finalWeight);

		/*for(int k = 0 ; k < numOfEdges; k++){
			if(weightFromStart[k]!= Integer.MAX_VALUE){
				relax(myList[k]);
			}
		}*/

		//hint: do DFS/BFS from vertices that are part of any negative cycle
		//int changedWeight = weightFromStart[end];

		if(finalWeightinInt.equals(Integer.MAX_VALUE)){
			return "Impossible";
		}
		//else if(changedWeight != finalWeight){
		else if(negCycles[end]){
			return "-Infinity";
		}
		else {
			return String.valueOf(finalWeight);
		}
	}
}