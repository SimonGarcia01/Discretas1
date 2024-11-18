package structures;

public class VertexM<V> implements Comparable<VertexM<V>> {
    private V value;
    private int distance;
    private Color color;
    private Vertex<V> predecessor;

    public VertexM(V value){
        super();
        this.value = value;
    }

    @Override
    public int compareTo(VertexM<V> o){
        return distance - o.getDistance();
    }

    @Override
    public String toString(){
        return value.toString();
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

    public Vertex<V> getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Vertex<V> predecessor) {
        this.predecessor = predecessor;
    }

    
}
