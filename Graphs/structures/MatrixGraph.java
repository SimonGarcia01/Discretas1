package structures;

import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

import exceptions.GraphException;

public class MatrixGraph<V> implements IGraph<V> {

    //Graph properties
    private List<VertexM<V>> vertices;
    private final int SIZE;
    private List<EdgeM<V>>[][] edges;
    private boolean simpleGraph;
    private boolean directed;
    private boolean allowLoops;

    private Queue<Integer> availablePositions = new LinkedList<>();
    
    @SuppressWarnings("unchecked") //recommended in stack overflow
    public MatrixGraph(int size, boolean simpleGraph, boolean directed, boolean allowLoops) throws GraphException{
        if (size <= 0) {
            throw new GraphException("Size must be positive.");
        }

        this.vertices = new ArrayList<>();
        this.SIZE = size;
        edges = (List<EdgeM<V>>[][]) new List[SIZE][SIZE];
        this.simpleGraph = simpleGraph;
        this.directed = directed;
        this.allowLoops = allowLoops;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                edges[i][j] = new ArrayList<>();
            }
        }
    }

    @Override
    public void add(V value) {
        //Make sure the value is unique
        if (searchVertexValue(value) == null) {
            //take up the position of a removed vertex if there is one
            if (!availablePositions.isEmpty()) {
                int position = availablePositions.poll();
                vertices.set(position, new VertexM<>(value));
            } else if(vertices.size() < SIZE){
                vertices.add(new VertexM<>(value));
            }
        }
    }

    @Override
    public void addEdge(V startValue, V endValue, int weight) throws GraphException {
        VertexM<V> startVertex = searchVertexValue(startValue);
        VertexM<V> endVertex = searchVertexValue(endValue);

        if (startVertex == null || endVertex == null) {
            throw new GraphException("One or both vertices do not exist in the graph.");
        }

        if (startVertex.equals(endVertex) && !allowLoops) {
            throw new GraphException("Loops are not allowed.");
        }

        
        int intStartVertex = vertices.indexOf(startVertex);
        int intEndVertex = vertices.indexOf(endVertex);

        if (simpleGraph) {
            boolean edgeExists = !edges[intStartVertex][intEndVertex].isEmpty();

            if (!directed) {
                edgeExists = edgeExists || !edges[intEndVertex][intStartVertex].isEmpty();
            }

            if (edgeExists) {
                throw new GraphException("An edge already exists between the specified vertices.");
            }
        }

        // If its a multigraph none of the preconditions from before must be checked
        addEdgeInternal(intStartVertex, intEndVertex, weight, startVertex, endVertex);

        if (!directed) {
            addEdgeInternal(intEndVertex, intStartVertex, weight, endVertex, startVertex);
        }
    }

    //Made this just to reduce the repetitive lines
    private void addEdgeInternal(int from, int to, int weight, VertexM<V> startVertex, VertexM<V> endVertex) {
        EdgeM<V> edge = new EdgeM<>(weight, startVertex, endVertex);
        edges[from][to].add(edge);
    }

    @Override
    public void bFS(V rootValue) throws GraphException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void prim() throws GraphException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeEdge(V startValue, V endValue) throws GraphException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeVertex(V value) throws GraphException {
        VertexM<V> vertex = searchVertexValue(value);

        if (vertex == null) {
            throw new GraphException("The vertex does not exist.");
        }
    
        int index = vertices.indexOf(vertex);
        //Place a removed vertex so it can be reused after and the matrix doesn't misalign with the list
        vertices.set(index, new RemovedVertex<V>());
        availablePositions.add(index);

        for (int i = 0; i < SIZE; i++) {
            edges[index][i].clear();
            edges[i][index].clear();
        }
    }

    private VertexM<V> searchVertexValue(V value) {
        for (VertexM<V> vertex : vertices) {
            // Ignore RemovedVertex placeholders
            if (vertex instanceof RemovedVertex) {
                continue;
            }
            if (vertex.getValue().equals(value)) {
                return vertex;
            }
        }
        return null;
    }
}
