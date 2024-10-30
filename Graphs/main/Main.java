package main;

import structures.IGraph;
import structures.ListGraph;
import structures.MatrixGraph;

public class Main{
    public static void main(String[] args){
        IGraph<String> listgraph = new ListGraph<>();

        listgraph.add("Hello");

        IGraph<String> matrixgraph = new MatrixGraph<>();

    }
}