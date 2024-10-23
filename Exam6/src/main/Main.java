package main;

import structures.AVLtree;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sk = new Scanner(System.in);
        AVLtree<Integer> tree = new AVLtree<>();

        int loop = sk.nextInt();
        sk.nextLine();

        for(int n = 0; n < loop; n++) {
            String input = sk.nextLine();

            String[] inputSplit = input.split(" ");

            int option = Integer.parseInt(inputSplit[0]);
            int value = 0;
            if(inputSplit.length == 2){
                value = Integer.parseInt(inputSplit[1]);
            }

            switch (option){
                case 1: tree.add(value); break;
                case 2: tree.delete(value); break;
                case 3:
                    System.out.println(tree.preOrder());break;
            }
        }
    }
}