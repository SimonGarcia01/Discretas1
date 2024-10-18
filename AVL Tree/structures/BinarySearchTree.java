package structures;

public class BinarySearchTree<T extends Comparable<T>>{

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
            } catch(Exception e){
                e.printStackTrace();
            }

        }
    }

    private void delete(Node<T> parent, Node<T> current, T value) throws Exception {
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
                    // Find the predecessor (maximum node in the left subtree)
                    Node<T> predecessor = predecessor(current.getLeft());
    
                    // Replace current node's value with the predecessor's value
                    current.setValue(predecessor.getValue());
    
                    // Delete the predecessor node (which will be a leaf or have one child)
                    delete(current, current.getLeft(), predecessor.getValue());
                }
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
            // Throw exception saying the node doesn't exist
            throw new Exception("Node hasn't been found");
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

    public Node<T> predecessor(Node<T> node) {
        if (node == null) return null;
    
        // Case 1: The node has a left child
        if (node.getLeft() != null) {
            return maximum(node.getLeft());  // Maximum node in the left subtree
        }
    
        // Case 2: No left child, go up to find the predecessor
        Node<T> parent = node.getParent();
        while (parent != null && node == parent.getLeft()) {
            node = parent;
            parent = parent.getParent();
        }
    
        return parent;
    }
    

    //Finding the minimum based on a node
    public Node<T> maximum(Node<T> current){
        if(current.getRight()==null){
            return current;
        } else {
            return maximum(current.getLeft());
        }
    }

    public void add(T value) {
        Node<T> node = new Node<>(value);

        //Base Case
        if(root == null){
            root = node;
        }
        //Recursive case
        else {
            //Auxiliary recursive method
            add(node, root);
        }

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
}