public class Edge implements Comparable<Edge>{

    private int vertice = 0;
    private int weight = 0;

    public Edge(int vertice, int weight){
        this.weight = weight;
        this.vertice = vertice;
    }

    public int getVertice(){
        return this.vertice;
    }
    
    public int getWeight(){
        return this.weight;
    }
    
    public int compareTo(Edge anotherEdge) {
		//ascending order
		return this.weight - anotherEdge.getWeight();
		
	}	


}