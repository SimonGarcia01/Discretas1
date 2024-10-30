package structures;

import java.util.List;
import java.util.ArrayList;

public class ListVertex<V> {
    private V value;
    private List<V> edges;

    public ListVertex(V value){
        this.value = value;
        this.edges = new ArrayList<>();
    }

    public void addEdge(V edge) {
        if (!edges.contains(edge)) {
            edges.add(edge);
        }
    }

    public void removeEdge(V edge) {
        edges.remove(edge);
    }

    public V getValue() {
        return value;
    }

    public List<V> getEdges() {
        return edges;
    }

    public void setEdges(List<V> edges) {
        this.edges = edges;
    }
    
}
