import java.util.List;
import java.util.ArrayList;

public class Set<T extends Comparable<T>> implements ISet<T>{
    private List<T> allElements;

    public Set(){
        //Como se declara abstraccion List<T> al crearse la clase ya sabe el tipo
        //Luego el arrayList va a inferir cual es su tipo
        this.allElements = new ArrayList<>();
    }

    public void add(T element){
        if(findElement(element) != -1){
            allElements.add(element);
        }
    }

    public void delete(T element){
        int deletingE= findElement(element);

        if(deletingE != -1){
            allElements.remove(deletingE);
        }

        allElements.remove(element);
    }

    public void union(ISet<T> set){

    }
    public void intersection(ISet<T> set){

    }
    public void difference(ISet<T> set){

    }
    public List<T> getAllElements(){
        return this.allElements;
    }

    public int findElement(T element){
        int found = -1;
        boolean isFound = false;
        for(int n = 0; n < allElements.size() && !isFound; n++){
            if(allElements.get(n).compareTo(element) == 0){
                found = n;
            }
        }

        return found;
    }

    public void complement(ISet<T> set){

    }

    public boolean isSubset(ISet<T> set){

    }
}
