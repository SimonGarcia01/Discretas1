package main;

import java.util.Scanner;

import structures.IGraph;
import structures.ListGraph;
import exceptions.GraphException;

public class Main{

    private static Scanner sk;
    private static IGraph<String, String> listGraph;
    public static void main(String[] args){

        sk = new Scanner(System.in);

        listGraph = new ListGraph<>(true, true, false);

        boolean flag = true;

        do{
            menu();
            int option = sk.nextInt();
            sk.nextLine();

            switch (option) {
                case 1 -> addVertex();
                case 2 -> addEdge();
                case 3 -> determineConnectivity();
                default -> System.out.println("Enter a valid option.");
            }
            

        } while (flag);

        sk.close();
    }

    public static void menu(){
        System.out.println("1. Add Vertex");
        System.out.println("2. Add Edge");
        System.out.println("3. Determine connectivity.");
        System.out.print("Enter one option:");
    }

    public static void addVertex(){
        System.out.print("Enter the key: ");
        String key = sk.nextLine();
        System.out.print("Enter the text: ");
        String text = sk.nextLine();
        listGraph.add(key, text);
    }

    public static void addEdge(){
        System.out.print("Enter the starting key: ");
        String keyStart = sk.nextLine();
        System.out.print("Enter the ending key: ");
        String keyEnd = sk.nextLine();
        System.out.print("Enter the weight: ");
        int weight = sk.nextInt();
        sk.nextLine();
        try{
            listGraph.addEdge(keyStart, keyEnd, weight);
        } catch (GraphException e){
            System.out.println(e.getMessage());
        }
    }

    public static void determineConnectivity(){
        System.out.println(listGraph.determineConnectivity());
    }
}