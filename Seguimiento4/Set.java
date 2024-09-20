import java.util.List;
import java.util.ArrayList;

public class Set<T> implements ISet<T>{
    private List<T> allElements;

    public Set(){
        //Como se declara abstraccion List<T> al crearse la clase ya sabe el tipo
        //Luego el arrayList va a inferir cual es su tipo
        this.allElements = new ArrayList<>();
    }

    public void add(T element){
        allElements.add(element);
    }

    public void delete(T element){
        for(T e:allElements){

        }

        allElements.remove(element);
    }

    public ISet<T> union(ISet<T> set){

    }
    public ISet<T> intersection(ISet<T> set){

    }
    public ISet<T> difference(ISet<T> set){

    }
    public ArrayList<T> getAllElements(){

    }
    public T getElement(T element){
        


    }
    public boolean belongs(T element){

    }
    public ISet<T> complement(ISet<T> set){

    }
}
