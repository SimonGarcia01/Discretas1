package main;

import structures.IGraph;
import structures.ListGraph;
// import structures.MatrixGraph;
import exceptions.GraphException;
import model.Animal;

public class Main{
    public static void main(String[] args){
        IGraph<String, Animal> listGraph = new ListGraph<>(true, true, true);

        Animal animal1 = new Animal("Santiago", "Cat");
        listGraph.add(animal1.getName(), animal1);

        Animal animal2 = new Animal("AA", "S1");
        listGraph.add(animal2.getName(), animal2);

        Animal animal3 = new Animal("BB", "S2");
        listGraph.add(animal3.getName(), animal3);

        Animal animal4 = new Animal("CC", "S3");
        listGraph.add(animal4.getName(), animal4);

        Animal animal5 = new Animal("DD", "S4");
        listGraph.add(animal5.getName(), animal5);
        
        try{
            listGraph.addEdge("Santiago", "AA",1);
            listGraph.addEdge("AA", "BB", 1);
            listGraph.addEdge("AA", "CC", 1);
            listGraph.addEdge("CC", "DD", 1);
            listGraph.addEdge("Santiago", "BB", 1);
    
            listGraph.bFS("Santiago");
        }catch(GraphException e){
            System.out.println(e.getMessage());
        }


        System.out.println("Santiago");
        System.out.println(listGraph.getVertices().get("Santiago").getDistance());
        System.out.println(listGraph.getVertices().get("Santiago").getColor());
        System.out.println("null");

        System.out.println("AA");
        System.out.println(listGraph.getVertices().get("AA").getDistance());
        System.out.println(listGraph.getVertices().get("AA").getColor());
        System.out.println(listGraph.getVertices().get("AA").getPredecesor().getValue().getName());

        System.out.println("BB");
        System.out.println(listGraph.getVertices().get("BB").getDistance());
        System.out.println(listGraph.getVertices().get("BB").getColor());
        System.out.println(listGraph.getVertices().get("BB").getPredecesor().getValue().getName());
        
        System.out.println("CC");
        System.out.println(listGraph.getVertices().get("CC").getDistance());
        System.out.println(listGraph.getVertices().get("CC").getColor());
        System.out.println(listGraph.getVertices().get("CC").getPredecesor().getValue().getName());

        System.out.println("DD");
        System.out.println(listGraph.getVertices().get("DD").getDistance());
        System.out.println(listGraph.getVertices().get("DD").getColor());
        System.out.println(listGraph.getVertices().get("DD").getPredecesor().getValue().getName());
    }
}