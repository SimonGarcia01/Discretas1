package structures;

import java.util.List;
import java.util.ArrayList;


public class MatrixGraph<T> implements IGraph<T> {

    List<T> vertices;
    int[][] matrix;

    public MatrixGraph(){
        this.vertices = new ArrayList<>();
        this.matrix = new int[0][0];
    }
    
    @Override
    public void add(T item) {
        //Only add if the item wasn't added before
        if (!vertices.contains(item)) {
            vertices.add(item);
            
            // Increase matrix size to accommodate the new vertex
            int newSize = vertices.size();
            int[][] newMatrix = new int[newSize][newSize];
    
            // Copy existing connections to the new matrix
            for (int i = 0; i < matrix.length; i++) {
                System.arraycopy(matrix[i], 0, newMatrix[i], 0, matrix[i].length);
            }
    
            // Replace the old matrix with the new one
            matrix = newMatrix;
        }
    }

    @Override
    public void addConnection(T itemStart, T itemEnd) {
        //First i must make sure both items exist
        if(vertices.contains(itemStart) && vertices.contains(itemEnd)){
            int start = vertices.indexOf(itemStart);
            int end = vertices.indexOf(itemEnd);

            matrix[start][end] = 1;
            matrix[end][start] = 1;
        }
    }

    @Override
    public void remove(T item) {
        int itemIndex = vertices.indexOf(item);
        if (itemIndex != -1) {
            // Remove item from the vertex list
            vertices.remove(itemIndex);
    
            // Create a new matrix with the reduced size
            int newSize = vertices.size();
            int[][] newMatrix = new int[newSize][newSize];
    
            // Copy all rows and columns except the removed one
            for (int i = 0, newRow = 0; i < matrix.length; i++) {
                if (i == itemIndex) continue; // Skip the row
    
                for (int j = 0, newCol = 0; j < matrix[i].length; j++) {
                    if (j == itemIndex) continue; // Skip the column
                    newMatrix[newRow][newCol++] = matrix[i][j];
                }
                newRow++;
            }
    
            matrix = newMatrix;
        }
    }
    

    @Override
    public void removeConnection(T itemStart, T itemEnd) {
        //Check that both items exist
        if(vertices.contains(itemStart) && vertices.contains(itemEnd)){
            int start = vertices.indexOf(itemStart);
            int end = vertices.indexOf(itemEnd);

            matrix[start][end] = 0;
            matrix[end][start] = 0;
        }
    }
}
