package main;

import structures.*;
import exceptions.GraphException;
import model.Animal;

public class Main{
    public static void main(String[] args){
        IGraph<String> listGraph = new ListGraph<>(true, false, true);

        listGraph.add("a");
        listGraph.add("b");
        listGraph.add("c");
        listGraph.add("d");
        
        try{
            listGraph.addEdge("a","b",1);
            listGraph.addEdge("b","c",1);
            listGraph.addEdge("b","c",2);
            listGraph.addEdge("c","d",1);
            listGraph.removeVertex("b");
        }catch(GraphException e){
            System.out.println(e.getMessage());
        }
    }
}