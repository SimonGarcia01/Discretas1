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
        if(findElement(element) == -1){
            allElements.add(element);
        }
    }

    public void delete(T element){
        int deletingE= findElement(element);
        if(deletingE != -1){
            allElements.remove(deletingE);
        }
    }

    public void union(ISet<T> set){
        List<T> elements = set.getAllElements();
        for(T element : elements){
            this.add(element);
        }
    }

    public void intersection(ISet<T> set){
        List<T> elements = set.getAllElements();
        List<T> newElements = new ArrayList<>();

        for(T element : elements){
            if(findElement(element) != -1){
                newElements.add(element);
            }
        }

        allElements = newElements;
    }

    public void difference(ISet<T> set, int comparedSet){
        List<T> elements = set.getAllElements();
        
        if(comparedSet == 1){
            for(T element : elements){
                delete(element);
            }

        } else if(comparedSet == 2){
            for(T element : allElements){
                elements.remove(element);
            }

            allElements = elements;
        }
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
                isFound = true;
            }
        }

        return found;
    }

    public void complement(ISet<T> set){
        if(isSubset(set)){
            difference(set, 2);
        }
    }

    public boolean isSubset(ISet<T> set){
        boolean checkSubset =  true;
        List<T> elements = set.getAllElements();

        for(int n = 0; n < allElements.size() && checkSubset; n++){
            boolean isFound = false;
            for(int i = 0; i < elements.size() && !isFound; i++){
                if(allElements.get(n).compareTo(elements.get(i)) == 0){
                    isFound = true;
                } 
            }
            if(!isFound){
                checkSubset = false;
            }
        }

        return checkSubset;
    }

    @Override
    public String toString(){
        String message = "{";

        for(int n = 0; n < allElements.size(); n++){
            if(n == 0){
                message += allElements.get(n) ;
            } else {
                message += " " + allElements.get(n) ;
            }

            if(n < allElements.size() - 1){
                message += ",";
            }
        }

        return message + "}";
    }
}
