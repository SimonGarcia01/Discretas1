package structures;

import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

import exceptions.GraphException;

public class MatrixGraph<V> implements IGraph<V> {

    //Graph properties
    private List<Vertex<V>> vertices;

    private int[][] edges;

    private final int SIZE;
    private boolean simpleGraph;
    private boolean directed;
    private boolean allowLoops;

    public MatrixGraph(boolean simpleGraph, boolean directed, boolean allowLoops, int size){
        this.simpleGraph = simpleGraph;
        this.directed = directed;
        this.allowLoops = allowLoops;
        this.SIZE = size;
        vertices = new ArrayList<>();
        edges = new int[size][size];
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

        int intStart = vertices.indexOf(startVertex);
        int intEnd = vertices.indexOf(endVertex);

        if (simpleGraph && edges[intStart][intEnd] == 0) {
            //Must check if there is an edge with that vertex
            boolean edgeExists = startVertex.searchEdge(endVertex) != null;

            if (!directed && edges[intEnd][intStart] == 0) {
                //There shouldn't bne an edge coming back either way
                edgeExists = edgeExists || endVertex.searchEdge(startVertex) != null;
            }
            
            if (!edgeExists) {
                startVertex.addEdge(new Edge<V>(weight, endVertex));
                edges[intStart][intEnd] += 1;
                if (!directed) {
                    endVertex.addEdge(new Edge<V>(weight, startVertex));
                    edges[intEnd][intStart] += 1;
                }
            }
        } else {
            //If it's a multigraph there's no need to check
            startVertex.addEdge(new Edge<V>(weight, endVertex));
            edges[intStart][intEnd] += 1;
            if (!directed) {
                endVertex.addEdge(new Edge<V>(weight, startVertex));
                edges[intEnd][intStart] += 1;
            }
        }
    }

    @Override
    public void bFS(V rootValue) throws GraphException {
        Vertex<V> startVertex = searchVertexValue(rootValue);

        if (startVertex == null) {
            throw new GraphException("The vertex with the specified value was not found.");
        }

        for (Vertex<V> vertex : vertices) {
            vertex.setColor(Color.WHITE);
            vertex.setPredecesor(null);
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
                    adjVertex.setPredecesor(current);
                    queue.add(adjVertex);
                }
            }
            current.setColor(Color.BLACK);
        }
    }

    @Override
    public void removeEdge(V startValue, V endValue) throws GraphException {
        //FIirst must check both vertices even exist
        Vertex<V> startVertex = searchVertexValue(startValue);
        Vertex<V> endVertex = searchVertexValue(endValue);

        if (startVertex == null || endVertex == null) {
            throw new GraphException("One or both vertices do not exist in the graph.");
        }

        int intStart = vertices.indexOf(startValue);
        int intEnd = vertices.indexOf(endValue);

        //now check if the edges even exists
        if(edges[intStart][intEnd]== 0){
            throw new GraphException("The edge does not exist.");
        }

        Edge<V> startEdge = startVertex.searchEdge(endVertex);
        Edge<V> endEdge = directed ?  null : endVertex.searchEdge(startVertex);

        if (simpleGraph) {
            //only need to remove one edge once
            startVertex.removeEdge(startEdge);
            if (!directed && endEdge != null) {
                endVertex.removeEdge(endEdge);
            }
        } else {
            //In multigraphs i must remove all ocurrences of the same vertex
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

    @Override
    public void removeVertex(V value) throws GraphException {
        Vertex<V> vertex = searchVertexValue(value);

        //First check if the vertex exists
        if (vertex == null) {
            throw new GraphException("The vertex does not exist.");
        }

        for (Vertex<V> otherVertex : vertices) {
            //Here ill use my removeEdge since it will delete all edges (even if it's a multigraph)
            removeEdge(otherVertex.getValue(), value);
        }

        int index = vertices.indexOf(vertex);
        
        // Reset that row to 0's since all edges will dissapear
        for (int i = 0; i < edges[index].length; i++) {
            edges[index][i] = 0;
        }

        // Clear the column representing edges coming from others to this vertex
        for (int i = 0; i < edges.length; i++) {
            edges[i][index] = 0;
        }

        vertices.remove(vertex);
    }

    @Override
    public Vertex<V> searchVertexValue(V value) {
        for (Vertex<V> vertex : vertices) {
            if (vertex.getValue().equals(value)) {
                return vertex;
            }
        }
        return null;
    }

    public List<Vertex<V>> getVertices() {
        return vertices;
    }
    
}
