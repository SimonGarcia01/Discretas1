package structures;

import java.util.List;
import java.util.ArrayList;

public class Vertex<V> {
    private V value;
    private int distance;
    private Color color;
    private Vertex<V> predecesor;
    private List<Edge<V>> edges;

    public Vertex(V value){
        this.value = value;
        this.edges = new ArrayList<>();
    }

    public void addEdge(Edge<V> edge) {
        edges.add(edge);
    }

    public boolean removeEdge(Edge<V> edge) {
        return edges.remove(edge);
    }

    public Edge<V> searchEdge(Vertex<V> endVertex){
        
        for(Edge<V> edge : edges){
            if(edge.getEndVertex().equals(endVertex)){
                return edge;
            }
        }
        
        return null;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Vertex<V> getPredecesor() {
        return predecesor;
    }

    public void setPredecesor(Vertex<V> predecesor) {
        this.predecesor = predecesor;
    }

    public List<Edge<V>> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge<V>> edges) {
        this.edges = edges;
    }
    
}
