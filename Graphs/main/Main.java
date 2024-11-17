package main;

import structures.*;
import exceptions.GraphException;
import model.Animal;

public class Main{
    public static void main(String[] args){
        IGraph<String> listGraph = new ListGraph<>(true, false, false);

        listGraph.add("At");
        listGraph.add("Ch");
        listGraph.add("Ny");
        listGraph.add("De");
        listGraph.add("Sf");
        
        try{
            listGraph.addEdge("At", "Ny", 800);
            listGraph.addEdge("At", "Ch", 700);
            listGraph.addEdge("At", "De", 1400);
            listGraph.addEdge("At", "Sf", 2200);
            listGraph.addEdge("Ny", "De", 1600);
            listGraph.addEdge("Ny", "Ch", 1000);
            listGraph.addEdge("Ny", "Sf", 2000);
            listGraph.addEdge("Ch", "De", 1300);
            listGraph.addEdge("Ch", "Sf", 1200);
            listGraph.addEdge("De", "Sf", 900);
            System.out.println(listGraph.kruskal());
            listGraph.prim();
        }catch(GraphException e){
            System.out.println(e.getMessage());
        }
    }
}