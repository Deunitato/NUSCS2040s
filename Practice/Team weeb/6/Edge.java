public class Edge implements Comparable<Edge>{

    private int vertice = 0;
    private int weight = 0;
    private double dbweight = 0;

    public Edge(int vertice, int weight){
        this.weight = weight;
        this.vertice = vertice;
    }

    public Edge(int vertice, double weight){
        this.dbweight = weight;
        this.vertice = vertice;
    }

    public int getVertice(){
        return this.vertice;
    }
    
   /* public int getWeight(){
        return this.weight;
    }*/
    
    public double getWeight(){
        return this.dbweight;
    }
    
    public int compareTo(Edge anotherEdge) {
		//ascending order
		return (int) (this.weight * 1000- anotherEdge.getWeight()*1000);
		//potential problem
	}	


}