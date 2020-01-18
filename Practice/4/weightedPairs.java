public class weightedPairs{


    private int source = 0;
    private int vertice = 0;
    private int weight = 0;

    public weightedPairs(int source, int vertice, int weight){
        this.source = source;
        this.weight = weight;
        this.vertice = vertice;
    }

    public int getVertice(){
        return this.vertice;
    }

    public int getSource(){
        return this.source;
    }
    
    
    public int getWeight(){
        return this.weight;
    }
    


}