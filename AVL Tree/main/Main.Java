package main;
import structures.AVLtree;

public class Main {

    public static void main(String[] args) {
        testSingleLeftRotation();
        testSingleRightRotation();
        testDoubleLeftRightRotation();
        testDoubleRightLeftRotation();

        AVLtree<Integer> integerTree = new AVLtree<>();
        integerTree.add(20);
        integerTree.add(30);
        integerTree.add(40); 
        System.out.println("In-order traversal after insertion: " + integerTree.inOrder());
        System.out.println("Root: " + integerTree.getRoot().getValue());
        System.out.println("Root balancing factor: " + integerTree.getRoot().getBalancingFactor());

        integerTree.add(15);
        integerTree.add(25);
        integerTree.add(28);
        System.out.println("In-order traversal after insertion: " + integerTree.inOrder());
        System.out.println("Root: " + integerTree.getRoot().getValue());
        System.out.println("Root balancing factor: " + integerTree.getRoot().getBalancingFactor());

    }
    
    // Test 1: Single Left Rotation (Right-heavy case)
    public static void testSingleLeftRotation() {
        AVLtree<Integer> integerTree = new AVLtree<>();
        System.out.println("Test 1: Single Left Rotation (Right-heavy)");
    
        integerTree.add(20);
        integerTree.add(30);
        integerTree.add(40);  // This should cause a left rotation on 20
    
        System.out.println("In-order traversal after insertion: " + integerTree.inOrder());
        System.out.println("Root: " + integerTree.getRoot().getValue());
        System.out.println("Root balancing factor: " + integerTree.getRoot().getBalancingFactor());
    
        System.out.println("----------------------------------");
    }
    
    // Test 2: Single Right Rotation (Left-heavy case)
    public static void testSingleRightRotation() {
        AVLtree<Integer> integerTree = new AVLtree<>();
        System.out.println("Test 2: Single Right Rotation (Left-heavy)");
    
        integerTree.add(40);
        integerTree.add(30);
        integerTree.add(20);  // This should cause a right rotation on 40
    
        System.out.println("In-order traversal after insertion: " + integerTree.inOrder());
        System.out.println("Root: " + integerTree.getRoot().getValue());
        System.out.println("Root balancing factor: " + integerTree.getRoot().getBalancingFactor());
    
        System.out.println("----------------------------------");
    }
    
    // Test 3: Double Left-Right Rotation (Left-heavy with a right-heavy subtree)
    public static void testDoubleLeftRightRotation() {
        AVLtree<Integer> integerTree = new AVLtree<>();
        System.out.println("Test 3: Double Left-Right Rotation");
    
        integerTree.add(40);
        integerTree.add(20);
        integerTree.add(30);  // This should cause a double rotation: left on 20, right on 40
    
        System.out.println("In-order traversal after insertion: " + integerTree.inOrder());
        System.out.println("Root: " + integerTree.getRoot().getValue());
        System.out.println("Root balancing factor: " + integerTree.getRoot().getBalancingFactor());
    
        System.out.println("----------------------------------");
    }
    
    // Test 4: Double Right-Left Rotation (Right-heavy with a left-heavy subtree)
    public static void testDoubleRightLeftRotation() {
        AVLtree<Integer> integerTree = new AVLtree<>();
        System.out.println("Test 4: Double Right-Left Rotation");
    
        integerTree.add(20);
        integerTree.add(40);
        integerTree.add(30);  // This should cause a double rotation: right on 40, left on 20
    
        System.out.println("In-order traversal after insertion: " + integerTree.inOrder());
        System.out.println("Root: " + integerTree.getRoot().getValue());
        System.out.println("Root balancing factor: " + integerTree.getRoot().getBalancingFactor());
    
        System.out.println("----------------------------------");
    }
    
}