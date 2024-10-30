package structures;
import java.util.HashMap;
import java.util.Map;

public class ListGraph<T> implements IGraph<T>{
    private Map<T, ListVertex<T>> verticies;

    public ListGraph(){
        this.verticies = new HashMap<>();
    }

    @Override
    public void add(T item) {
        verticies.put(item, new ListVertex<T>(item));
    }

    
    @Override
    public void addConnection(T itemStart, T  itemEnd) {
        //Only add the edge if it wasn't added before, so i checked the start and end
        verticies.get(itemStart).addEdge(itemEnd);
        verticies.get(itemEnd).addEdge(itemStart);
    }

    @Override
    public void remove(T item) {
        //First it must be checked if the vertex exists
        if(verticies.containsKey(item)){
            verticies.remove(item);

            //should i erase the reference in all the other edges???????
            for (ListVertex<T> vertex : verticies.values()) {
                vertex.removeEdge(item);
            }
        }
    }

    @Override
    public void removeConnection(T itemStart, T  itemEnd) {
        //First it must be checked that both verticies exist
        if(verticies.containsKey(itemStart) && verticies.containsKey(itemEnd)){
            //Then it must be checked if the edge exists in one of the edges at least
            //?????????????? do i need to check?
            if(verticies.get(itemStart).getEdges().contains(itemEnd)){
                verticies.get(itemStart).removeEdge(itemEnd);
                verticies.get(itemEnd).removeEdge(itemStart);
            }
        }
    }

}
