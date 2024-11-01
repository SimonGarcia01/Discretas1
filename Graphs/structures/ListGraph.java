package structures;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.Queue;


public class ListGraph<K ,T> implements IGraph<K, T>{
    private Map<K, ListVertex<T>> verticies;

    public ListGraph(){
        this.verticies = new HashMap<>();
    }

    

    @Override
    public void bFS(K key) {
        for (ListVertex<T> vertex : verticies.values()) {
            vertex.setColor(Color.WHITE);
            vertex.setPredecesor(null);
            vertex.setDistance(0);
        }

        Queue<ListVertex<T>> queue = new LinkedList<>();
        
        queue.add(verticies.get(key));
        
        while(!queue.isEmpty()){
            ListVertex<T> current = queue.remove();
            for(ListVertex<T> vertex : current.getEdges()){
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
    public void add(K key, T item) {
        verticies.put(key, new ListVertex<T>(item));
    }

    
    @Override
    public void addConnection(K keyStart, K  keyEnd) {
        //Only add the edge if it wasn't added before, so i checked the start and end
        if(!verticies.get(keyStart).getEdges().contains(verticies.get(keyEnd))){
            verticies.get(keyStart).addEdge(verticies.get(keyEnd));
            verticies.get(keyEnd).addEdge(verticies.get(keyStart));
        }
    }

    @Override
    public void remove(K key) {
        //First it must be checked if the vertex exists
        if(verticies.containsKey(key)){
            ListVertex<T> vertexremoved = verticies.get(key);

            //should i erase the reference in all the other edges???????
            for (ListVertex<T> vertex : verticies.values()) {
                vertex.removeEdge(vertexremoved);
            }

            verticies.remove(key);
        }
    }

    @Override
    public void removeConnection(K keyStart, K  keyEnd) {
        //First it must be checked that both verticies exist
        if(verticies.containsKey(keyStart) && verticies.containsKey(keyEnd)){
            if(verticies.get(keyStart).getEdges().contains(verticies.get(keyEnd))){
                verticies.get(keyStart).removeEdge(verticies.get(keyEnd));
                verticies.get(keyEnd).removeEdge(verticies.get(keyStart));
            }
        }
    }

    public Map<K, ListVertex<T>> getVerticies() {
        return verticies;
    }

    

}
