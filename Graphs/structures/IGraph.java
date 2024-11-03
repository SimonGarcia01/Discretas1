package structures;

import java.util.Map;

import exceptions.GraphException;

public interface IGraph<K, V>{
    public abstract void add(K key, V vertex);
    public abstract void addEdge(K keyStart, K keyEnd, int weight) throws GraphException;
    public abstract void remove(K key);
    public abstract void removeEdge(K keyStart, K keyEnd);
    public abstract void bFS(K key);
    public abstract Map<K, Vertex<V>>  getVerticies();
}
