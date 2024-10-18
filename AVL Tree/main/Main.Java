package main;

import structures.BinarySearchTree;

public class Main {

    public static void main(String[] args) {

        BinarySearchTree<Integer> integerTree = new BinarySearchTree<>();

        integerTree.add(50);
        integerTree.add(60);
        integerTree.add(30);
        integerTree.add(40);
        integerTree.add(45);

        System.out.println(integerTree.inOrder());
        System.out.println(integerTree.search(45).getValue());

//        integerTree.delete(70);

//        System.out.println(integerTree.inOrder());

//        System.out.println("Left of the root of the tree");
//        System.out.println(integerTree.getRoot().getLeft());

//        integerTree.add(40);
//        System.out.println("root's left");
//        System.out.println(integerTree.getRoot().getLeft().getValue());
//
//        integerTree.add(60);
//        integerTree.add(55);
//        integerTree.add(30);
//        System.out.println("root's left");
//        System.out.println(integerTree.getRoot().getLeft().getValue());
    }
}