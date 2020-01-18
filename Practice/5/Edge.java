public class Edge implements Comparable<Edge>{

    private int vertice1 = 0;
    private int vertice2 = 0;
    private int weight = 0;

    public Edge(int vertice1, int vertice2, int weight){
        this.weight = weight;
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
    }

    public Edge(int vertice1, int weight){
        this.weight = weight;
        this.vertice1 = vertice1;
    }

    public int getParent(){
        return this.vertice1;
    }

    public int getChild(){
        return this.vertice2;
    }

    public int getWeight(){
        return this.weight;
    }

    public int compareTo(Edge anotherEdge) {
		//ascending order
		return this.weight - anotherEdge.getWeight();
	}


}