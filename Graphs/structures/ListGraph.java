package structures;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.Queue;

import exceptions.GraphException;


public class ListGraph<K ,V> implements IGraph<K, V>{
    private Map<K, Vertex<V>> verticies;

    //Properties of the Graph
    private boolean simpleGraph;
    private boolean directed;
    private boolean allowLoops;

    

    

    public ListGraph(boolean simpleGraph, boolean directed, boolean allowLoops) {
        this.simpleGraph = simpleGraph;
        this.directed = directed;
        this.allowLoops = allowLoops;

        this.verticies = new HashMap<>();
    }



    @Override
    public void bFS(K key) {
        for (Vertex<V> vertex : verticies.values()) {
            vertex.setColor(Color.WHITE);
            vertex.setPredecesor(null);
            vertex.setDistance(0);
        }

        Queue<Vertex<V>> queue = new LinkedList<>();
        
        queue.add(verticies.get(key));
        
        while(!queue.isEmpty()){
            Vertex<V> current = queue.remove();
            for(Vertex<V> vertex : current.getEdges()){
                if(vertex.getColor()==Color.WHITE){
                    vertex.setColor(Color.GRAY);
                    vertex.setDistance(current.getDistance()+1);
                    vertex.setPredecesor(current);
                    queue.add(vertex);
                }
                current.setColor(Color.BLACK);
            }
        }
    }



    @Override
    public void add(K key, V vertex) {
        //HashMap doesn't allow duplicated keys when adding a new vertex
        verticies.put(key, new Vertex<V>(vertex));
    }

    
    @Override
    public void addEdge(K keyStart, K  keyEnd, int weight) throws GraphException{
        Vertex<V> startVertex = verticies.get(keyStart);
        Vertex<V> endVertex = verticies.get(keyEnd);

        if(startVertex == null || endVertex == null){
            throw new GraphException("The starting or ending vertex don't exist.");
        }
        
        if(simpleGraph){
            Edge<V> searchedEdge = startVertex.searchEdge(endVertex);
            if(searchedEdge == null){
                startVertex.addEdge(new Edge(weight, endVertex));
            }
        } else {

        }

        if(directed){

        } else {

        }

        if(allowLoops){

        } else {

        }
        if(!verticies.get(keyStart).getEdges().contains(verticies.get(keyEnd))){
            verticies.get(keyStart).addEdge(verticies.get(keyEnd));
            verticies.get(keyEnd).addEdge(verticies.get(keyStart));
        }
    }

    @Override
    public void remove(K key) {
        //First it must be checked if the vertex exists
        if(verticies.containsKey(key)){
            Vertex<V> vertexremoved = verticies.get(key);

            //should i erase the reference in all the other edges???????
            for (Vertex<V> vertex : verticies.values()) {
                vertex.removeEdge(vertexremoved);
            }

            verticies.remove(key);
        }
    }

    @Override
    public void removeEdge(K keyStart, K  keyEnd) {
        //First it must be checked that both verticies exist
        if(verticies.containsKey(keyStart) && verticies.containsKey(keyEnd)){
            if(verticies.get(keyStart).getEdges().contains(verticies.get(keyEnd))){
                verticies.get(keyStart).removeEdge(verticies.get(keyEnd));
                verticies.get(keyEnd).removeEdge(verticies.get(keyStart));
            }
        }
    }

    public Map<K, Vertex<V>> getVerticies() {
        return verticies;
    }

}
