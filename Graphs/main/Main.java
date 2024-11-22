package main;

import structures.*;
import exceptions.GraphException;
import java.util.List;

public class Main{
    public static void main(String[] args){
        MatrixGraph<String> matrixGraph = null;

        try{
            matrixGraph = new MatrixGraph<>(50, true, false, false);
        } catch (GraphException e){
            System.out.println(e.getMessage());
        }

        String[] nodes = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        for (String node : nodes) {
            matrixGraph.add(node);
        }

        // matrixGraph.add("At");
        // matrixGraph.add("Ch");
        // matrixGraph.add("Ny");
        // matrixGraph.add("De");
        // matrixGraph.add("Sf");
        
        try{
            matrixGraph.addEdge("A", "B", 10);
            matrixGraph.addEdge("A", "C", 20);
            matrixGraph.addEdge("A", "D", 30);
            matrixGraph.addEdge("B", "E", 40);
            matrixGraph.addEdge("B", "F", 50);
            matrixGraph.addEdge("C", "G", 60);
            matrixGraph.addEdge("D", "H", 70);
            matrixGraph.addEdge("E", "I", 80);
            matrixGraph.addEdge("F", "J", 90);
            matrixGraph.addEdge("G", "I", 100);
            matrixGraph.addEdge("H", "J", 110);
            matrixGraph.addEdge("I", "J", 120);
            matrixGraph.addEdge("F", "G", 130);
            matrixGraph.addEdge("C", "F", 140);
            matrixGraph.addEdge("D", "G", 150);
            // matrixGraph.addEdge("At", "Ny", 800);
            // matrixGraph.addEdge("At", "Ch", 700);
            // matrixGraph.addEdge("At", "De", 1400);
            // matrixGraph.addEdge("At", "Sf", 2200);
            // matrixGraph.addEdge("Ny", "De", 1600);
            // matrixGraph.addEdge("Ny", "Ch", 1000);
            // matrixGraph.addEdge("Ny", "Sf", 2000);
            // matrixGraph.addEdge("Ch", "De", 1300);
            // matrixGraph.addEdge("Ch", "Sf", 1200);
            // matrixGraph.addEdge("De", "Sf", 900);
            // matrixGraph.bFS("At");
            matrixGraph.dijkstra("A");
            List<VertexM<String>> vertices = matrixGraph.getVertices();
            System.out.println(vertices.get(0).toStringNoPred());
            for(int n = 1; n < vertices.size(); n++){
                System.out.println(vertices.get(n));
            }
        }catch(GraphException e){
            System.out.println(e.getMessage());
        }

        // ListGraph<String> listGraph = new ListGraph<>(true, false, false);

        // String[] nodes = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        // for (String node : nodes) {
        //     listGraph.add(node);
        // }

        // // listGraph.add("At");
        // // listGraph.add("Ch");
        // // listGraph.add("Ny");
        // // listGraph.add("De");
        // // listGraph.add("Sf");
        
        // try{
        //     listGraph.addEdge("A", "B", 10);
        //     listGraph.addEdge("A", "D", 30);
        //     listGraph.addEdge("A", "C", 20);
        //     listGraph.addEdge("B", "E", 40);
        //     listGraph.addEdge("B", "F", 50);
        //     listGraph.addEdge("C", "G", 60);
        //     listGraph.addEdge("D", "H", 70);
        //     listGraph.addEdge("E", "I", 80);
        //     listGraph.addEdge("F", "J", 90);
        //     listGraph.addEdge("G", "I", 100);
        //     listGraph.addEdge("H", "J", 110);
        //     listGraph.addEdge("I", "J", 120);
        //     listGraph.addEdge("F", "G", 130);
        //     listGraph.addEdge("C", "F", 140);
        //     listGraph.addEdge("D", "G", 150);
        //     // listGraph.addEdge("At", "Ny", 800);
        //     // listGraph.addEdge("At", "Ch", 700);
        //     // listGraph.addEdge("At", "De", 1400);
        //     // listGraph.addEdge("At", "Sf", 2200);
        //     // listGraph.addEdge("Ny", "De", 1600);
        //     // listGraph.addEdge("Ny", "Ch", 1000);
        //     // listGraph.addEdge("Ny", "Sf", 2000);
        //     // listGraph.addEdge("Ch", "De", 1300);
        //     // listGraph.addEdge("Ch", "Sf", 1200);
        //     // listGraph.addEdge("De", "Sf", 900);
        //     // listGraph.bFS("At");
        //     listGraph.dijkstra("A");
        //     List<Vertex<String>> vertices = listGraph.getVertices();
        //     System.out.println(vertices.get(0).toStringNoPred());
        //     for(int n = 1; n < vertices.size(); n++){
        //         System.out.println(vertices.get(n));
        //     }
        // }catch(GraphException e){
        //     System.out.println(e.getMessage());
        // }
    }
}