package structures;

import java.util.List;
import java.util.ArrayList;

public class ListVertex<V> {
    private V value;
    private int distance;
    private Color color;
    private ListVertex<V> predecesor;
    private List<ListVertex<V>> edges;

    public ListVertex(V value){
        this.value = value;
        this.edges = new ArrayList<>();
    }

    public void addEdge(ListVertex<V> edge) {
        if (!edges.contains(edge)) {
            edges.add(edge);
        }
    }

    public void removeEdge(ListVertex<V> edge) {
        edges.remove(edge);
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

    public List<ListVertex<V>> getEdges() {
        return edges;
    }

    public void setEdges(List<ListVertex<V>> edges) {
        this.edges = edges;
    }

    public ListVertex<V> getPredecesor() {
        return predecesor;
    }

    public void setPredecesor(ListVertex<V> predecesor) {
        this.predecesor = predecesor;
    }
}
