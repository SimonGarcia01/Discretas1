package structures;

import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

import exceptions.GraphException;

public class ListGraph<V> implements IGraph<V> {
    private List<Vertex<V>> vertices;
    private List<Edge<V>> edges;

    // Properties of the Graph
    private boolean simpleGraph;
    private boolean directed;
    private boolean allowLoops;

    public ListGraph(boolean simpleGraph, boolean directed, boolean allowLoops) {
        this.simpleGraph = simpleGraph;
        this.directed = directed;
        this.allowLoops = allowLoops;
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
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
                Edge<V> startEdge = new Edge<V>(weight, startVertex, endVertex);
                startVertex.addEdge(startEdge);
                edges.add(startEdge);
                if (!directed) {
                    Edge<V> endEdge = new Edge<V>(weight, endVertex, startVertex);
                    endVertex.addEdge(endEdge);
                    edges.add(endEdge);
                }
            }
        } else {
            //If it's a multigraph there's no need to check
            Edge<V> startEdge = new Edge<V>(weight, startVertex, endVertex);
            startVertex.addEdge(startEdge);
            edges.add(startEdge);
            if (!directed) {
                Edge<V> endEdge = new Edge<V>(weight, endVertex, startVertex);
                endVertex.addEdge(endEdge);
                edges.add(endEdge);
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

        vertices.remove(vertex);

        for(Edge<V> edge: edges){
            if(edge.getStartVertex().equals(vertex) && edge.getEndVertex().equals(vertex)){
                edges.remove(edge);
            }
        }

        System.out.println(edges);
    }

    @Override
    public void removeEdge(V startValue, V endValue) throws GraphException {
        //FIirst must check both vertices even exist
        Vertex<V> startVertex = searchVertexValue(startValue);
        Vertex<V> endVertex = searchVertexValue(endValue);

        if (startVertex == null || endVertex == null) {
            throw new GraphException("One or both vertices do not exist in the graph.");
        }

        //now check if the edges even eist
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
            //In multigraphs i must remove all ocurrences of the same vertex
            while ((startEdge = startVertex.searchEdge(endVertex)) != null) {
                startVertex.removeEdge(startEdge);
                edges.remove(startEdge);
            }
            if (!directed) {
                while ((endEdge = endVertex.searchEdge(startVertex)) != null) {
                    endVertex.removeEdge(endEdge);
                    edges.remove(startEdge);
                }
            }
        }
    }
}