package structures;

import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Queue;
import java.util.LinkedList;

import exceptions.GraphException;

public class ListGraph<V> implements IGraph<V> {
    private final List<Vertex<V>> vertices;
    private final List<Edge<V>> edges;

    // Properties of the Graph
    private final boolean simpleGraph;
    private final boolean directed;
    private final boolean allowLoops;

    public ListGraph(boolean simpleGraph, boolean directed, boolean allowLoops) {
        this.simpleGraph = simpleGraph;
        this.directed = directed;
        this.allowLoops = allowLoops;
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    @Override
    public void bFS(V rootValue) throws GraphException {
        Vertex<V> startVertex = searchVertexValue(rootValue);

        if (startVertex == null) {
            throw new GraphException("The vertex with the specified value was not found.");
        }

        for (Vertex<V> vertex : vertices) {
            vertex.setColor(Color.WHITE);
            vertex.setPredecessor(null);
            vertex.setDistance(Integer.MAX_VALUE);
        }

        Queue<Vertex<V>> queue = new LinkedList<>();
        startVertex.setDistance(0);
        startVertex.setColor(Color.GRAY);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.remove();
            for (Edge<V> edge : current.getEdges()) {
                Vertex<V> adjVertex = edge.getEndVertex();
                if (adjVertex.getColor() == Color.WHITE) {
                    adjVertex.setColor(Color.GRAY);
                    adjVertex.setDistance(current.getDistance() + 1);
                    adjVertex.setPredecessor(current);
                    queue.add(adjVertex);
                }
            }
            current.setColor(Color.BLACK);
        }
    }

    @Override
    public void prim() throws GraphException{

        if (directed) {
            throw new GraphException("Prim's algorithm cannot be applied to directed graphs.");
        }

        if(vertices.isEmpty()){
            throw new GraphException("Prim can't be done on an empty graph.");
        }

        for (Vertex<V> vertex : vertices) {
            vertex.setColor(Color.WHITE);
            vertex.setPredecessor(null);
            vertex.setDistance(Integer.MAX_VALUE);
        }

        Vertex<V> startVertex = vertices.get(0);

        startVertex.setDistance(0);

        PriorityQueue<Vertex<V>> queue = new PriorityQueue<>();
        queue.addAll(vertices);

        while(!queue.isEmpty()){

            Vertex<V> start = queue.poll();

            for(Edge<V> endEdge : start.getEdges()){

                Vertex<V> endVertex = endEdge.getEndVertex();

                if(endVertex.getColor() == Color.WHITE && endEdge.getWeight() < endVertex.getDistance()){
                    endVertex.setDistance(endEdge.getWeight());
                    endVertex.setPredecessor(start);
                    queue.remove(endVertex);
                    queue.add(endVertex);
                }
            }
            start.setColor(Color.BLACK);
        }
    }

    private Vertex<V> searchVertexValue(V value) {
        for (Vertex<V> vertex : vertices) {
            if (vertex.getValue().equals(value)) {
                return vertex;
            }
        }
        return null;
    }

    @Override
    public List<Edge<V>>  kruskal() throws GraphException {
        if (directed) {
            throw new GraphException("Kruskal's algorithm cannot be applied to directed graphs.");
        }
        
        if(vertices.isEmpty()){
            throw new GraphException("Kruskal can't be done on an empty graph.");
        }

        DisjointSet<Vertex<V>> disjointSet = new DisjointSet<>();
        for (Vertex<V> vertex : vertices) {
            disjointSet.makeSet(vertex);
        }
        PriorityQueue<Edge<V>> queue = new PriorityQueue<>();
        queue.addAll(edges);
        List<Edge<V>> mst = new ArrayList<>();
        while (!queue.isEmpty()) {
            Edge<V> edge = queue.poll(); 
            Vertex<V> u = edge.getStartVertex();
            Vertex<V> v = edge.getEndVertex();
            if (!disjointSet.find(u).equals(disjointSet.find(v))) {
                mst.add(edge);
                disjointSet.union(u, v);
            }
        }
        return mst;
    }

    @Override
    public void dijkstra(V rootValue) throws GraphException{
        Vertex<V> startVertex = searchVertexValue(rootValue);

        if (startVertex == null) {
            throw new GraphException("The vertex with the specified value was not found.");
        }

        for (Vertex<V> vertex : vertices) {
            vertex.setPredecessor(null);
            vertex.setDistance(Integer.MAX_VALUE);
        }

        startVertex.setDistance(0);

        PriorityQueue<Vertex<V>> queue = new PriorityQueue<>();
        queue.add(startVertex);

        while(!queue.isEmpty()){
            Vertex<V> current = queue.poll();
            for(Edge<V> edge : current.getEdges()){
                Vertex<V> neighbor = edge.getEndVertex();
                int alt = current.getDistance() + edge.getWeight();
                if(alt < neighbor.getDistance()){

                    neighbor.setDistance(alt);
                    neighbor.setPredecessor(current);

                    //Re add the vertex but with the new priority
                    queue.remove(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }
    
    @Override //This is a mix between dijkstra and bfs trying to find longest path
    public void antiDijkstra(V rootValue) throws GraphException {
        Vertex<V> startVertex = searchVertexValue(rootValue);
    
        if (startVertex == null) {
            throw new GraphException("The vertex with the specified value was not found.");
        }
    

        for (Vertex<V> vertex : vertices) {
            vertex.setColor(Color.WHITE); 
            vertex.setPredecessor(null);
            vertex.setDistance(Integer.MIN_VALUE); 
        }
    
        startVertex.setDistance(0);
        startVertex.setColor(Color.GRAY); 
    
        PriorityQueue<Vertex<V>> queue = new PriorityQueue<>(Comparator.comparingInt(Vertex<V>::getDistance).reversed());
        queue.add(startVertex);
    
        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();
    
            for (Edge<V> edge : current.getEdges()) {
                Vertex<V> neighbor = edge.getEndVertex();
    
                if (neighbor.getColor() == Color.WHITE) { // Prevent inifinite loop using colors
                    int alt = current.getDistance() + edge.getWeight();
    
                    if (alt > neighbor.getDistance()) {
                        neighbor.setDistance(alt);
                        neighbor.setPredecessor(current);
                    }
    
                    neighbor.setColor(Color.GRAY);
                    queue.add(neighbor);
                }
            }
    
            current.setColor(Color.BLACK);
        }
    }

    @Override
    public String printTree() {
        // Find the root (vertex with no predecessor)
        Vertex<V> root = findRoot();
    
        // If no root is found, return an empty tree representation
        if (root == null) {
            return "[ ]";
        }
    
        // Start building the tree representation from the root
        return buildTree(root);
    }
    
    // Helper method to find the root vertex
    private Vertex<V> findRoot() {
        for (Vertex<V> vertex : vertices) { // Assuming `vertices` is the list of all vertices in the graph
            if (vertex.getPredecessor() == null) {
                return vertex; // A vertex with no predecessor is considered the root
            }
        }
        return null; // No root found (this could indicate a disconnected or malformed graph)
    }
    
    // Recursive helper method to build the tree representation
    private String buildTree(Vertex<V> current) {
        StringBuilder message = new StringBuilder("[ ");
    
        // Add the current vertex's value
        message.append(current.getValue()).append(" ");
    
        // Iterate over the edges of the current vertex
        for (Edge<V> edge : current.getEdges()) {
            // Check if the current vertex is the predecessor of the end vertex
            if (edge.getEndVertex().getPredecessor() == current) {
                // Recursively build the subtree for the end vertex
                message.append(buildTree(edge.getEndVertex()));
            }
        }
    
        // Close the subtree representation
        message.append("] ");
        return message.toString();
    }

    @Override
    public void add(V value) {
        //Make sure the value is unique
        if (searchVertexValue(value) == null) {
            vertices.add(new Vertex<>(value));
        }
    }

    @Override
    public void addEdge(V startValue, V endValue, int weight) throws GraphException {
        Vertex<V> startVertex = searchVertexValue(startValue);
        Vertex<V> endVertex = searchVertexValue(endValue);

        if (startVertex == null || endVertex == null) {
            throw new GraphException("One or both vertices do not exist in the graph.");
        }

        if (startVertex.equals(endVertex) && !allowLoops) {
            throw new GraphException("Loops are not allowed.");
        }

        if (simpleGraph) {
            //Must check if there is an edge with that vertex
            boolean edgeExists = startVertex.searchEdge(endVertex) != null;
            if (!directed) {
                //There shouldn't be an edge coming back either way
                edgeExists = edgeExists || endVertex.searchEdge(startVertex) != null;
            }

            if (!edgeExists) {
                addEdgeInternal(weight, startVertex, endVertex);
            }
        } else {
            //If it's a multi graph there's no need to check
            addEdgeInternal(weight, startVertex, endVertex);
        }
    }

    private void addEdgeInternal(int weight, Vertex<V> startVertex, Vertex<V> endVertex) {
        Edge<V> startEdge = new Edge<>(weight, startVertex, endVertex);
        startVertex.addEdge(startEdge);
        edges.add(startEdge);
        if (!directed) {
            Edge<V> endEdge = new Edge<>(weight, endVertex, startVertex);
            endVertex.addEdge(endEdge);
            edges.add(endEdge);
        }
    }

    @Override
    public void removeVertex(V value) throws GraphException {
        Vertex<V> vertex = searchVertexValue(value);

        //First check if the vertex exists
        if (vertex == null) {
            throw new GraphException("The vertex does not exist.");
        }

        int vertexPosition = vertices.indexOf(vertex);

        for(int n = 0; n < vertices.size() && n!= vertexPosition; n++) {
            //Here ill use my removeEdge since it will delete all edges (even if it's a multi graph)
            removeEdge(vertices.get(n).getValue(), value);
        }

        List<Edge<V>> toRemove = new ArrayList<>();

        for (Edge<V> edge : edges) {
            if (edge.getStartVertex().equals(vertex) || edge.getEndVertex().equals(vertex)) {
                toRemove.add(edge);
            }
        }
        //Doing it like this to avoid the ConcurrentModificationException :c
        edges.removeAll(toRemove);

        vertices.remove(vertex);
    }

    @Override
    public void removeEdge(V startValue, V endValue) throws GraphException {
        //First must check both vertices even exist
        Vertex<V> startVertex = searchVertexValue(startValue);
        Vertex<V> endVertex = searchVertexValue(endValue);

        if (startVertex == null || endVertex == null) {
            throw new GraphException("One or both vertices do not exist in the graph.");
        }

        //now check if the edges even list
        Edge<V> startEdge = startVertex.searchEdge(endVertex);
        Edge<V> endEdge = directed ?  null : endVertex.searchEdge(startVertex);

        if (startEdge == null && (directed || endEdge == null)) {
            throw new GraphException("The edge does not exist.");
        }

        if (simpleGraph) {
            //only need to remove one edge once
            startVertex.removeEdge(startEdge);
            edges.remove(startEdge);
            if (!directed && endEdge != null) {
                endVertex.removeEdge(endEdge);
                edges.remove(endEdge);
            }
        } else {
            //In multi graphs I must remove all occurrences of the same vertex
            while ((startEdge = startVertex.searchEdge(endVertex)) != null) {
                startVertex.removeEdge(startEdge);
                edges.remove(startEdge);
            }
            if (!directed) {
                while ((endEdge = endVertex.searchEdge(startVertex)) != null) {
                    endVertex.removeEdge(endEdge);
                    edges.remove(endEdge);
                }
            }
        }
    }

    public List<Vertex<V>> getVertices() {
        return vertices;
    }
}