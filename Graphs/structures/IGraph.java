package structures;

import java.util.List;

import exceptions.GraphException;

public interface IGraph <V>{
    public abstract Vertex<V> searchVertexValue(V value);
    public abstract void add(V value);
    public abstract void addEdge(V startValue, V endValue, int weight) throws GraphException;
    public abstract void removeVertex(V value) throws GraphException;
    public abstract void removeEdge(V startValue, V endValue) throws GraphException;
    public abstract void bFS(V rootValue) throws GraphException;
    public abstract void prim() throws GraphException;
    public abstract List<Edge<V>> kruskal() throws GraphException;
    public abstract String printTree();
}
