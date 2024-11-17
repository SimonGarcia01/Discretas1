package tests;

import org.junit.Test;
import static org.junit.Assert.*;

import exceptions.GraphException;
import structures.IGraph;
import structures.ListGraph;
import structures.Vertex;

import java.util.List;

import structures.Edge;

public class TestPrimKruskal {

    private IGraph<String> graph;

    //Setups
    public void classExcerciseSetup(){
        graph = new ListGraph<>(true, false, false);

        graph.add("At");
        graph.add("Ch");
        graph.add("Ny");
        graph.add("De");
        graph.add("Sf");
        
        try{
            graph.addEdge("At", "Ny", 800);
            graph.addEdge("At", "Ch", 700);
            graph.addEdge("At", "De", 1400);
            graph.addEdge("At", "Sf", 2200);
            graph.addEdge("Ny", "De", 1600);
            graph.addEdge("Ny", "Ch", 1000);
            graph.addEdge("Ny", "Sf", 2000);
            graph.addEdge("Ch", "De", 1300);
            graph.addEdge("Ch", "Sf", 1200);
            graph.addEdge("De", "Sf", 900);
        }catch(GraphException e){
            
        }
    }

    public void emptySetup(){
        graph = new ListGraph<>(true, false, false);
    }

    public void disconnectedGraphSetup() {
        graph = new ListGraph<>(true, false, false);
    
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.add("D");
    }

    public void denseGraphSetup() {
        graph = new ListGraph<>(true, false, false);
    
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.add("D");
        graph.add("E");
    
        try {
            graph.addEdge("A", "B", 2);
            graph.addEdge("A", "C", 3);
            graph.addEdge("A", "D", 1);
            graph.addEdge("A", "E", 4);
    
            graph.addEdge("B", "C", 2);
            graph.addEdge("B", "D", 5);
            graph.addEdge("B", "E", 1);
    
            graph.addEdge("C", "D", 1);
            graph.addEdge("C", "E", 3);
    
            graph.addEdge("D", "E", 2);
        } catch (GraphException e) {
            e.printStackTrace();
        }
    }

    public void negativeWeightGraphSetup() {
        graph = new ListGraph<>(true, false, false);
    
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.add("D");
    
        try {
            graph.addEdge("A", "B", -2);
            graph.addEdge("A", "C", 4);
            graph.addEdge("B", "C", 1);
            graph.addEdge("B", "D", 7);
            graph.addEdge("C", "D", -3);
        } catch (GraphException e) {
            e.printStackTrace();
        }
    }

    //Tests
    @Test
    public void classExcercisePrimTest(){
        //Init
        classExcerciseSetup();

        //Act
        try{
            graph.prim();
        } catch (GraphException e){

        }

        int edgeSum = 0;
        edgeSum += graph.searchVertexValue("At").getDistance();
        edgeSum += graph.searchVertexValue("Ch").getDistance();
        edgeSum += graph.searchVertexValue("Ny").getDistance();
        edgeSum += graph.searchVertexValue("Sf").getDistance();
        edgeSum += graph.searchVertexValue("De").getDistance();

        //Assert
        assertEquals("Ch", graph.searchVertexValue("Sf").getPredecessor().getValue());
        assertEquals(3600, edgeSum);
    }

    @Test
    public void classExcerciseKrukalTest(){
        //Init
        classExcerciseSetup();

        //Act
        List<Edge<String>> mst = null;
        try{
            mst = graph.kruskal();
        } catch (GraphException e){
            
        }

        //Assert
        assertEquals("[Start: At - End: Ch - Weight: 700, Start: Ny - End: At - Weight: 800, Start: De - End: Sf - Weight: 900, Start: Ch - End: Sf - Weight: 1200]", mst.toString());
    }

    @Test
    public void emptyPrimTest(){
        //Init
        emptySetup();

        //Act
        String message = "";
        try{
            graph.prim();
        } catch (GraphException e){
            message = e.getMessage();
        }

        //Assert
        assertEquals("Prim can't be done on an empty graph.", message);
    }

    @Test
    public void emptyKrukalTest(){
        //Init
        emptySetup();

        //Act
        String message = "";
        try{
            graph.kruskal();
        } catch (GraphException e){
            message = e.getMessage();            
        }
        //Assert
        assertEquals("Kruskal can't be done on an empty graph.", message);
    }

    @Test
    public void disconnectedPrimTest(){
        //Init
        disconnectedGraphSetup();

        //Act
        try{
            graph.prim();
        } catch (GraphException e){
            
        }

        //Assert
        assertNull(graph.searchVertexValue("A").getPredecessor());
        assertNull(graph.searchVertexValue("B").getPredecessor());
        assertNull(graph.searchVertexValue("C").getPredecessor());
        assertNull(graph.searchVertexValue("D").getPredecessor());
    }

    @Test
    public void disconnectedKrukalTest(){
        //Init
        disconnectedGraphSetup();

        //Act
        List<Edge<String>> mst = null;
        try{
            mst = graph.kruskal();
        } catch (GraphException e){
            
        }
        //Assert
        assertEquals("[]", mst.toString());
    }
    
    @Test
    public void densePrimTest(){
        //Init
        denseGraphSetup();

        //Act
        try{
            graph.prim();
        } catch (GraphException e){
            
        }

        //Assert
        List<Vertex<String>> vertices = graph.getVertices();

        for (int n = 1; n < vertices.size(); n++) {
            assertNotNull(vertices.get(n));
        }
    }

    @Test
    public void denseKrukalTest(){
        //Init
        denseGraphSetup();

        //Act
        List<Edge<String>> mst = null;
        try{
            mst = graph.kruskal();
            System.out.println(mst.toString());
        } catch (GraphException e){
            
        }
        //Assert
        assertEquals("[Start: A - End: D - Weight: 1, Start: D - End: C - Weight: 1, Start: B - End: E - Weight: 1, Start: E - End: D - Weight: 2]", mst.toString());
    }

    @Test
    public void negativePrimTest(){
        //Init
        negativeWeightGraphSetup();

        //Act
        try{
            graph.prim();
        } catch (GraphException e){
            
        }

        int edgeSum = 0;

        for(Vertex<String> vertex : graph.getVertices()){
            edgeSum += vertex.getDistance();
        }

        //Assert
        assertEquals(-1, edgeSum);
    }

    @Test
    public void negativeKrukalTest(){
        //Init
        negativeWeightGraphSetup();

        //Act
        List<Edge<String>> mst = null;
        try{
            mst = graph.kruskal();
            System.out.println(mst.toString());
        } catch (GraphException e){
            
        }
        //Assert
        assertEquals("[Start: C - End: D - Weight: -3, Start: B - End: A - Weight: -2, Start: B - End: C - Weight: 1]", mst.toString());
    }
}