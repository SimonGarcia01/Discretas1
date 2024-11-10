package structures;

public class Edge <V>{

    private int weight;
    private Vertex<V> endVertex;

    public Edge(int weight, Vertex<V> endVertex){
        this.weight = weight;
        this.endVertex = endVertex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Vertex<V> getEndVertex() {
        return endVertex;
    }

    public void setEndVertex(Vertex<V> endVertex) {
        this.endVertex = endVertex;
    }

    
}
