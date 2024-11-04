package structures;

import java.util.Map;

import exceptions.GraphException;

public interface IGraph<K extends Comparable<K>, V>{
    public abstract void add(K key, V vertex);
    public abstract void addEdge(K keyStart, K keyEnd, int weight) throws GraphException;
    public abstract void removeVertex(K key) throws GraphException;
    public abstract void removeEdge(K keyStart, K keyEnd) throws GraphException;
    public abstract void bFS(K key) throws GraphException;
    public abstract Map<K, Vertex<V>>  getVertices();
}
