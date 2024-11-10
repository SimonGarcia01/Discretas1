package structures;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import exceptions.GraphException;


public class ListGraphKV<K extends Comparable<K> ,V> implements IGraphKV<K, V>{
    private Map<K, Vertex<V>> vertices;

    //Properties of the Graph
    private boolean simpleGraph;
    private boolean directed;
    private boolean allowLoops;

    public ListGraphKV(boolean simpleGraph, boolean directed, boolean allowLoops) {
        this.simpleGraph = simpleGraph;
        this.directed = directed;
        this.allowLoops = allowLoops;

        this.vertices = new HashMap<>();
    }

    @Override
    public void bFS(K key) throws GraphException{

        Vertex<V> startVertex = vertices.get(key);

        if(startVertex == null){
            throw new GraphException("The entered key wasn't found");
        }

        for (Vertex<V> vertex : vertices.values()) {
            vertex.setColor(Color.WHITE);
            vertex.setPredecesor(null);
            vertex.setDistance(Integer.MAX_VALUE);
        }

        Queue<Vertex<V>> queue = new LinkedList<>();
        startVertex.setDistance(0);
        startVertex.setColor(Color.GRAY);
        queue.add(startVertex);
        
        while(!queue.isEmpty()){
            Vertex<V> current = queue.remove();
            for(Edge<V> edge : current.getEdges()){
                Vertex<V> adjVertex = edge.getEndVertex();
                if(adjVertex.getColor()==Color.WHITE){
                    adjVertex.setColor(Color.GRAY);
                    adjVertex.setDistance(current.getDistance()+1);
                    adjVertex.setPredecesor(current);
                    queue.add(adjVertex);
                }
            }
            current.setColor(Color.BLACK);
        }
    }



    @Override
    public void add(K key, V vertex) {
        //HashMap doesn't allow duplicated keys when adding a new vertex
        vertices.put(key, new Vertex<V>(vertex));
    }

    
    @Override
    public void addEdge(K keyStart, K keyEnd, int weight) throws GraphException {
        Vertex<V> startVertex = vertices.get(keyStart);
        Vertex<V> endVertex = vertices.get(keyEnd);
    
        if (startVertex == null || endVertex == null) {
            throw new GraphException("The starting or ending vertex doesn't exist.");
        }
    
        if (keyStart.compareTo(keyEnd) == 0 && !allowLoops) {
            throw new GraphException("Loops are not allowed.");
        }
    
        if (simpleGraph) {
            //Must check first if there as an edge already to that vertex
            boolean edgeExists = startVertex.searchEdge(endVertex) != null;
            
            if (!directed) {
                //There shouldn't be an edge coming back either way
                edgeExists = edgeExists || endVertex.searchEdge(startVertex) != null;
            }
            
            if (!edgeExists) {
                startVertex.addEdge(new Edge<V>(weight, endVertex));
                if (!directed) {
                    endVertex.addEdge(new Edge<V>(weight, startVertex));
                }
            }

        } else {
            //If its multigraph then no need to check
            startVertex.addEdge(new Edge<V>(weight, endVertex));
            if (!directed) {
                endVertex.addEdge(new Edge<V>(weight, startVertex));
            }
        }
    }

    @Override
    public void removeVertex(K key) throws GraphException{
        //First it must be checked if the vertex exists
        if (!vertices.containsKey(key)) {
            throw new GraphException("The vertex doesn't exist.");
        }

        //Must delete all edges that hold the vertex so it is completely removed
        for (Map.Entry<K, Vertex<V>> entry : vertices.entrySet()) {
            K otherKey = entry.getKey();
            
            if (!otherKey.equals(key)) {
                try {
                    //Here ill use my removeEdge since it will delete all edges (even if it's a multigraph)
                    removeEdge(otherKey, key);
                    if (!directed) {
                        //Remove the edges in the other direction (includes multigraph)
                        removeEdge(key, otherKey);
                    }
                } catch (GraphException e) {
                    // Ignore exceptions where edges don’t exist
                }
            }
        }
        vertices.remove(key);
    }

    @Override
    public void removeEdge(K keyStart, K keyEnd) throws GraphException {
        // First it must check both vertices exist
        Vertex<V> startVertex = vertices.get(keyStart);
        Vertex<V> endVertex = vertices.get(keyEnd);
        
        if (startVertex == null || endVertex == null) {
            throw new GraphException("The starting or ending vertex doesn't exist.");
        }
        
        //Now check if the edfge even exists
        Edge<V> startEdge = startVertex.searchEdge(endVertex);
        Edge<V> endEdge = directed ? null : endVertex.searchEdge(startVertex);
    
        if (startEdge == null && (directed || endEdge == null)) {
            throw new GraphException("The edge doesn't exist.");
        }
        
        if (simpleGraph) {
            //only remove the edge once in simplegraphs
            startVertex.removeEdge(startEdge);
            if (!directed && endEdge != null) {
                endVertex.removeEdge(endEdge);
            }
        } else {
            //In multigraphs all the ocurrences must be removed
            while ((startEdge = startVertex.searchEdge(endVertex)) != null) {
                startVertex.removeEdge(startEdge);
            }
            if (!directed) {
                while ((endEdge = endVertex.searchEdge(startVertex)) != null) {
                    endVertex.removeEdge(endEdge);
                }
            }
        }
    }
    
    public List<V> getShortestPath(K startKey, K endKey) throws GraphException {
    Vertex<V> startVertex = vertices.get(startKey);
    Vertex<V> endVertex = vertices.get(endKey);

    if (startVertex == null || endVertex == null) {
        throw new GraphException("One or both vertices not found.");
    }

    bFS(startKey);

    if(!endVertex.getColor().equals(Color.WHITE)){
        List<V> path = new ArrayList<>();
        for (Vertex<V> at = endVertex; at != null; at = at.getPredecesor()) {
            path.add(at.getValue());
        }
        Collections.reverse(path);
    
        if (path.get(0).equals(startVertex.getValue())) {
            return path;  // Path from start to end
        } else {
            throw new GraphException("No path found between these vertices.");
        }
    }

    //if the vertex is white it means there is no path, so return null
    return null;
}

    public Map<K, Vertex<V>> getVertices() {
        return vertices;
    }
}
