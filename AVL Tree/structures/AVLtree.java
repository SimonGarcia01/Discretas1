package structures;

import exceptions.AVLException;

public class AVLtree<T extends Comparable<T>>{

    private Node<T> root;

    public String inOrder(){
        String message = "";

        if(root == null){
            message = "Empty Tree";
        }

        else{
            message = inOrder(root, message);
        }

        return message;
    }

    private String inOrder(Node<T> current, String message){

        if(current == null){
            message += "";
        }
        else{
            //Check method
            message += inOrder(current.getLeft(), message) + " " + current.getValue() + " " + inOrder(current.getRight(), message);
        }

        return message;
    }

    public void delete(T value){
        //Base Case 0: The tree is empty
        if(root == null) {
            //Can't delete
        } else {
            //Base case 0.1 The tree is not empty
            try{
                delete(null, root, value);
            } catch(AVLException e){
                e.printStackTrace();
            }

        }
    }

    private void delete(Node<T> parent, Node<T> current, T value) throws AVLException {
        // The node exists in the tree
        if (current != null) {
            // Base case -> Finding the node
            if (current.getValue().compareTo(value) == 0) {
    
                // Base case 1: it's a leaf node
                if (current.getLeft() == null && current.getRight() == null) {
                    // Base Case 1.1: Its leaf node and it's the root
                    if (current == root) {
                        root = null;
                    }
                    // Base case 1.2: It's a leaf node and its to the left
                    else if (parent.getLeft() == current) {
                        parent.setLeft(null);
                    }
                    // Base case 1.3: It's a leaf node and its to the right
                    else if (parent.getRight() == current) {
                        parent.setRight(null);
                    }
                }
                // Base Case 2: If the node only has one left node
                else if (current.getLeft() != null && current.getRight() == null) {
                    if (current == root) {
                        root = current.getLeft();
                        current.getLeft().setParent(null);  // Update root's parent reference
                    } else if (parent.getLeft() == current) {
                        parent.setLeft(current.getLeft());
                        current.getLeft().setParent(parent);  // Update child's parent reference
                    } else {
                        parent.setRight(current.getLeft());
                        current.getLeft().setParent(parent);  // Update child's parent reference
                    }
                }
                // Base Case 3: If the node only has a right node
                else if (current.getLeft() == null && current.getRight() != null) {
                    if (current == root) {
                        root = current.getRight();
                        current.getRight().setParent(null);  // Update root's parent reference
                    } else if (parent.getLeft() == current) {
                        parent.setLeft(current.getRight());
                        current.getRight().setParent(parent);  // Update child's parent reference
                    } else {
                        parent.setRight(current.getRight());
                        current.getRight().setParent(parent);  // Update child's parent reference
                    }
                }
                // Base Case 4: The node has both children nodes
                else if (current.getLeft() != null && current.getRight() != null) {

                    Node<T> predecessor = predecessor(current);

                    if (predecessor == null) {
                        throw new AVLException("Predecessor not found for node: " + current.getValue());
                    }

                    current.setValue(predecessor.getValue());

                    delete(current, current.getLeft(), predecessor.getValue());
                }

                rebalance(current);

            }
    
            // Recursive cases
            // Look to the left
            else if (current.getValue().compareTo(value) > 0) {
                delete(current, current.getLeft(), value);
            }
    
            // Look to the right
            else if (current.getValue().compareTo(value) < 0) {
                delete(current, current.getRight(), value);
            }
        } else {
            throw new AVLException("Node hasn't been found");
        }
    }

    public Node<T> search(T value){
        Node<T> found = null;

        //BASE CASE 1: The searched node is the root
        if(root.getValue().compareTo(value)==0){
            found = root;
        }
        //Call recursive method
        else{
            found = search(root, value);
        }
        return found;
    }

    private Node<T> search(Node<T> current, T value){
        Node<T> found = null;
        //Check if it's inside the tree
        if(current != null){

            //Base case: The node is found
            if(current.getValue().compareTo(value) == 0){
                found = current;
            }
            //Recursive case: Look to the left
            else if(current.getValue().compareTo(value) > 0){
                found = search(current.getLeft(),value);
            }
            //Recursive case: Look to the right
            else if(current.getValue().compareTo(value) < 0){
                found = search(current.getRight(),value);
            }
        } else {
            //It's not inside the tree
        }

        return found;
    }

    public Node<T> predecessor(Node<T> current) {
        if (current == null) return null;
    
        if (current.getLeft() != null) {
            return maximum(current.getLeft());
        }
    
        Node<T> parent = current.getParent();
        while (parent != null && current == parent.getLeft()) {
            current = parent;
            parent = parent.getParent();
        }
    
        return parent;
    }    
    

    public Node<T> maximum(Node<T> current){
        if(current.getRight()==null){
            return current;
        } else {
            return maximum(current.getRight());
        }
    }

    public void add(T value) {
        Node<T> node = new Node<>(value);
        if(root == null){
            root = node;
        }
        else {
            add(node, root);
        }

        rebalance(node);
    }

    private void add(Node<T> node, Node<T> current) {
        if (node.getValue().compareTo(current.getValue()) < 0) {
            if (current.getLeft() == null) {
                current.setLeft(node);
                node.setParent(current);
            } else {
                add(node, current.getLeft());
            }
        } else if (node.getValue().compareTo(current.getValue()) > 0) {
            if (current.getRight() == null) {
                current.setRight(node);
                node.setParent(current);
            } else {
                add(node, current.getRight());
            }
        }
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    public void leftRotate(Node<T> current) throws AVLException {
        if (current.getRight() == null) {
            throw new AVLException("Left rotation error: There was no right child.");
        }
    
        Node<T> originalRightNode = current.getRight();
        Node<T> middleSubTree = originalRightNode.getLeft();
        Node<T> generalParent = current.getParent();
    
        //In case it's the root (doesn't have a parent)
        if (current == root) {
            root = originalRightNode;
            originalRightNode.setParent(null); 
        } else {
            if (generalParent.getValue().compareTo(current.getValue()) < 0) {
                generalParent.setRight(originalRightNode);
            } else {
                generalParent.setLeft(originalRightNode);
            }
            originalRightNode.setParent(generalParent);
        }
    
        current.setRight(middleSubTree);
        if (middleSubTree != null) {
            middleSubTree.setParent(current);
        }
        
        originalRightNode.setLeft(current); 
        current.setParent(originalRightNode); 
    }
    
    public void rightRotate(Node<T> current) throws AVLException {
        if (current.getLeft() == null) {
            throw new AVLException("Right rotation error: There was no left child.");
        }
    
        Node<T> originalLeftNode = current.getLeft();
        Node<T> middleSubTree = originalLeftNode.getRight();
        Node<T> generalParent = current.getParent();
    
        //In case it's the root (it has no parent)
        if (current == root) {
            root = originalLeftNode; 
            originalLeftNode.setParent(null); 
        } else {
            if (generalParent.getValue().compareTo(current.getValue()) < 0) {
                generalParent.setRight(originalLeftNode);
            } else {
                generalParent.setLeft(originalLeftNode);
            }
            originalLeftNode.setParent(generalParent); 
        }
    
        current.setLeft(middleSubTree); 
        if (middleSubTree != null) {
            middleSubTree.setParent(current);
        }

        originalLeftNode.setRight(current); 
        current.setParent(originalLeftNode);  
    }
    
    public void rotationManager(Node<T> pNode){
        int pBalancingFactor = pNode.getBalancingFactor();
        
        try{
            if(pBalancingFactor == 2){
                Node<T> qNode = pNode.getRight();
                int qBalancingFactor = qNode.getBalancingFactor();
                
                if(qBalancingFactor == 1 || qBalancingFactor == 0){
                    leftRotate(pNode);
                } else if(qBalancingFactor == -1){
                    rightRotate(qNode);
                    leftRotate(pNode);
                }
    
            } else if (pBalancingFactor == -2){
                Node<T> qNode = pNode.getLeft();
                int qBalancingFactor = qNode.getBalancingFactor();
                
                if(qBalancingFactor == -1 || qBalancingFactor == 0){
                    rightRotate(pNode);
                } else if(qBalancingFactor == 1){
                    leftRotate(qNode);
                    rightRotate(pNode);
                }
            }
        } catch(AVLException e){
            e.printStackTrace();
        }
    }

    public void rebalance(Node<T> current){
        Node<T>parent = current.getParent();
        while (parent != null) {
            rotationManager(parent);
            current = parent;
            parent = current.getParent();
        }
    }
}