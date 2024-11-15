package structures;

public class Edge <V>{

    private int weight;
    private Vertex<V> startVertex;
    private Vertex<V> endVertex;

    public Edge(int weight, Vertex<V> startVertex, Vertex<V> endVertex){
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

    public Vertex<V> getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(Vertex<V> startVertex) {
        this.startVertex = startVertex;
    }
    
}
