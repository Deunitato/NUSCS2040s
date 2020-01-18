public class Bellman{

    static int[] distance;
	static int[][] edgeList;
    static boolean[] negCycleNodes;

    public static void main(String args[]) {
		try(Kattio kattio = new Kattio(System.in)){

			int totalVertices = kattio.getInt();
			int totalEdges = kattio.getInt();
			int queryNum = kattio.getInt();
			int start = kattio.getInt();

			boolean flag = totalVertices==0 && totalEdges==0 && queryNum==0 && start==0;

			while(!flag){

				edgeList = new int[totalEdges][3];
				negCycleNodes = new boolean[totalVertices];
				distance = new int[totalVertices];

				for(int i=0; i<totalVertices; i++){
					negCycleNodes[i] = false;
					distance[i]= Integer.MAX_VALUE;
				}

				distance[start] = 0;

				for(int i = 0 ; i< totalEdges; i++){
					edgeList[i][0]= kattio.getInt();
					edgeList[i][1] = kattio.getInt();
					edgeList[i][2]= kattio.getInt();
				}

                for(int i =0 ; i < totalVertices-1 ; i++){
                    for(int j = 0 ; j < totalEdges; j++){
                        relaxation(edgeList[j]);
                    }
                }

                for(int i =0 ; i < totalVertices-1; i++){
                    for(int j = 0 ; j < totalEdges; j++){
                        if(relaxation(edgeList[j])){
							int targetAffected = edgeList[j][1];
                            negCycleNodes[targetAffected] = true;
                        }
                    }
                }

				for(int i = 0; i < queryNum; i++){
					int end = kattio.getInt();
					if(end > totalVertices){
						kattio.println("Impossible");
						continue;
                    }

                    int finalWeight = distance[end];

                    if(finalWeight == Integer.MAX_VALUE){
                        kattio.println("Impossible");
                    } else if(negCycleNodes[end]) {
                        kattio.println("-Infinity");
                    } else {
                        kattio.println(finalWeight);
                    }
				}

				kattio.println("");

				totalVertices = kattio.getInt();
				totalEdges = kattio.getInt();
				queryNum = kattio.getInt();
				start = kattio.getInt();
				flag = totalVertices==0 && totalEdges==0 && queryNum==0 && start==0;

			}

			kattio.flush();

		}
	}

	public static boolean relaxation(int[] pair){
		int vertice1 = pair[0];
		int vertice1Distance = distance[vertice1];
		int vertice2 = pair[1];
		int vertice2Distance = distance[vertice2];
		int newWeight = pair[2] + vertice1Distance;
		if(distance[vertice1] != Integer.MAX_VALUE && vertice2Distance > newWeight){
			distance[vertice2] = newWeight;
			return true;
		}
		return false;
	}

}