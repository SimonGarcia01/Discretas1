package structures;

import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

import exceptions.GraphException;

public class MatrixGraph<V> implements IGraph<V> {

    //Graph properties
    private final List<VertexM<V>> vertices;
    private final int SIZE;
    private final List<EdgeM<V>>[][] edges;
    private final boolean simpleGraph;
    private final boolean directed;
    private final boolean allowLoops;

    private final Queue<Integer> availablePositions = new LinkedList<>();

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

        // If it's a multi graph none of the preconditions from before must be checked
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
        VertexM<V> startVertex = searchVertexValue(rootValue);

        if(startVertex == null){
            throw new GraphException("The vertex with the specified value was not found.");  
        }

        for(VertexM<V> vertex : vertices){
            vertex.setColor(Color.WHITE);
            vertex.setPredecessor(null);
            vertex.setDistance(Integer.MAX_VALUE);
        }

        Queue<VertexM<V>> queue = new LinkedList<>();
        startVertex.setDistance(0);
        startVertex.setColor(Color.GRAY);
        queue.add(startVertex);

        while(!queue.isEmpty()){
            VertexM<V> current = queue.remove();
            int currentIndex = vertices.indexOf(current);
            for(int neighborIndex = 0; neighborIndex < SIZE; neighborIndex++){
                //Check if there is atleast one edge
                if(!edges[currentIndex][neighborIndex].isEmpty()){
                    VertexM<V> neighborVertex = vertices.get(neighborIndex);

                    if (neighborVertex.getColor() == Color.WHITE) {
                        neighborVertex.setColor(Color.GRAY);
                        neighborVertex.setDistance(current.getDistance() + 1);
                        neighborVertex.setPredecessor(current);
                        queue.add(neighborVertex);
                    }
                }
            }
            current.setColor(Color.BLACK);
        }
    }

    @Override
    public void prim() throws GraphException {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeEdge(V startValue, V endValue) throws GraphException {
        //First must check both vertices even exist
        VertexM<V> startVertex = searchVertexValue(startValue);
        VertexM<V> endVertex = searchVertexValue(endValue);

        if (startVertex == null || endVertex == null) {
            throw new GraphException("One or both vertices do not exist in the graph.");
        }

        int intStartVertex = vertices.indexOf(startVertex);
        int intEndVertex = vertices.indexOf(endVertex);

        //At least one edge must exist
        if (edges[intStartVertex][intEndVertex].isEmpty()) {
            if (directed || edges[intEndVertex][intStartVertex].isEmpty()) {
                throw new GraphException("The edge does not exist.");
            }
        }

        //Either if its multi graph or simple, I will erase all relations between the two edges
        edges[intStartVertex][intEndVertex].clear();
        if(!directed){
            edges[intEndVertex][intStartVertex].clear();
        }
    }

    @Override
    public void removeVertex(V value) throws GraphException {
        VertexM<V> vertex = searchVertexValue(value);

        if (vertex == null) {
            throw new GraphException("The vertex does not exist.");
        }

        int index = vertices.indexOf(vertex);
        //Place a removed vertex so, it can be reused after and the matrix doesn't misalign with the list
        vertices.set(index, new RemovedVertex<>());
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

    public List<VertexM<V>> getVertices(){
        return vertices;
    }

    @Override
    public List<Edge<V>> kruskal() throws GraphException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String printTree() {
        // TODO Auto-generated method stub
        return null;
    }
}