import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ConcurrentModificationException;

public class Tests {

    private ISet<Integer> set1;
    private ISet<Integer> set2;

    public void defaultSetup(){
        set1 = null;
    }

    public void oneSetSetup(){
        set1 = new Set<>();

        set1.add(1);
        set1.add(2);
        set1.add(3);
    }

    public void twoSetSetup(){
        set1 = new Set<>();
        set2 = new Set<>();

        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(4);
        set1.add(5);

        set2.add(4);
        set2.add(5);
        set2.add(6);
        set2.add(7);
        set2.add(8);
        set2.add(9);
    }

    public void subSetSetup(){
        set1 = new Set<>();
        set2 = new Set<>();

        set1.add(1);
        set1.add(2);
        set1.add(3);

        set2.add(1);
        set2.add(2);
        set2.add(3);
        set2.add(4);
        set2.add(5);
        set2.add(6);
    }

    public void repeatSetup(){
        set1 = new Set<>();
        set2 = new Set<>();

        set1.add(1);
        set1.add(1);
        set1.add(1);

        set2.add(1);
        set2.add(1);
        set2.add(1);
    }

    @Test
    public void createSetTest(){
        //Init
        defaultSetup();

        //Act
        set1 = new Set<>();

        //Assert
        assertNotNull(set1);
    }

    //ADD TESTS
    @Test
    public void addTest(){
        //Init
        oneSetSetup();

        //Act
        set1.add(2);
        set1.add(4);

        //Assert
        assertEquals("{1, 2, 3, 4}", set1.toString());
    }

    @Test
    public void addTestRepeat(){
        //Init
        oneSetSetup();

        //Act
        set1.add(1);
        set1.add(1);
        set1.add(2);
        set1.add(2);
        set1.add(3);
        set1.add(3);

        //Assert
        assertEquals("{1, 2, 3}", set1.toString());
    }

    @Test
    public void addDeleteAddTest(){
        //Init
        oneSetSetup();

        //Act
        set1.add(99);
        set1.delete(99);
        set1.delete(2);
        set1.add(99);
        set1.add(02);

        //Assert
        assertEquals("{1, 3, 99, 2}", set1.toString());
    }

    //DELETE TESTS
    @Test
    public void deleteTest(){
        //Init
        oneSetSetup();

        //Act
        set1.delete(2);

        //Assert
        assertEquals("{1, 3}", set1.toString());
    }

    @Test
    public void deleteNull(){
        //Init
        defaultSetup();

        //Act
        String message = "";
        try{
            set1.delete(1);
        } catch (NullPointerException e) {
            message = e.getMessage();
        }

        //Assert
        assertEquals("Cannot invoke \"ISet.delete(Object)\" because \"this.set1\" is null", message);
    }

    @Test
    public void deleteEmpty(){
        //Init
        defaultSetup();

        //Act
        set1 = new Set<>();
        set1.add(1);
        set1.delete(1);
        set1.delete(1);

        //Assert
        assertEquals("{}", set1.toString());
    }

    //FIND ELEMENT TESTS
    @Test
    public void findElementTest(){
        //Init
        oneSetSetup();

        //Act
        int foundElement = set1.findElement(2);

        //Assert
        assertEquals(1, foundElement);
    }

    @Test
    public void findElementinOtherSet(){
        //Init
        twoSetSetup();

        //Act
        int foundElement = set1.findElement(set2.getAllElements().get(1));

        //Assert
        assertEquals(4, foundElement);
    }

    @Test
    public void findRepeatedElement(){
        //Init
        repeatSetup();

        //Act
        int foundElement = set1.findElement(1);

        //Assert
        assertEquals(0,foundElement);
    }

    //GETALLELEMENTS TESTS
    @Test
    public void getAllElementsTest(){
        //Init
        oneSetSetup();

        //Act
        List<Integer> elements = set1.getAllElements();

        //Assert
        assertNotNull(elements);
        assertTrue(elements.contains(2));
    }

    @Test
    public void getAllElementsEmptySet(){
        //Init
        defaultSetup();

        //Act
        set1 = new Set<>();
        
        //Assert
        assertTrue(set1.getAllElements().isEmpty());
    }

    @Test
    public void getAllElementsSubList(){
        //Init
        oneSetSetup();

        //Act
        set1.add(4);
        set1.add(5);

        //Assert
        assertEquals("[4, 5]", set1.getAllElements().subList(3,5).toString());
    }

    //UNION TESTS
    @Test
    public void unionTest(){
         //Init
        twoSetSetup();
        
        //Act
        set1.union(set2);

        //Assert
        assertEquals("{1, 2, 3, 4, 5, 6, 7, 8, 9}", set1.toString());
    }

    @Test
    public void unionItselfTest(){
        //Init
        twoSetSetup();

        //Act
        set1.union(set1);

        //Assert
        assertEquals("{1, 2, 3, 4, 5}", set1.toString());
    }

    @Test
    public void nestedUnion(){
        //Init
        twoSetSetup();

        //Act
        set2.union(set1);
        set1.union(set2);

        //Assert
        assertEquals("{1, 2, 3, 4, 5, 6, 7, 8, 9}", set1.toString());
    }

    //INTERSECTION TESTS
    @Test
    public void intersectionTest(){
        //Init
        twoSetSetup();

        //Act
        set1.intersection(set2);
        
        //Assert
        assertEquals("{4, 5}", set1.toString());
    }

    @Test
    public void emptyIntersection(){
        //Init
        twoSetSetup();

        //Act
        set1.delete(1);
        set1.delete(2);
        set1.delete(3);
        set1.delete(4);
        set1.delete(5);
        set2.intersection(set1);

        //Assert
        assertEquals("{}", set1.toString());
    }

    @Test
    public void subSetIntersection(){
        //Init
        subSetSetup();

        //Act
        set1.intersection(set2);

        //Assert
        assertEquals("{1, 2, 3}", set1.toString());
    }

    //DIFFERENCE TESTS
    @Test
    public void set1Set2differenceTest(){
        //Init
        twoSetSetup();

        //Act
        set1.difference(set2, 1);

        //Assert
        assertEquals("{1, 2, 3}", set1.toString());
    }

    @Test
    public void set2Set1Difference(){
                //Init
                twoSetSetup();

                //Act
                set1.difference(set2, 2);
        
                //Assert
                assertEquals("{6, 7, 8, 9}", set1.toString());
    }

    @Test
    public void selfDifferenceTest(){
        //init
        oneSetSetup();

        //Act
        ConcurrentModificationException e = null;
        try {
            set1.difference(set1, 1);
        } catch (ConcurrentModificationException ex) {
            e = ex;
        }

        // Assert
        assertNotNull("Expected ConcurrentModificationException", e);
    }

    //ISSUBSETTESTS
    @Test
    public void isSubsetTest(){
        //Init
        subSetSetup();

        //Act
        boolean isSet1SubetOfSet2 = set1.isSubset(set2);

        //Assert
        assertTrue(isSet1SubetOfSet2);
    }

    @Test
    public void nonSubSetIsSubSetTest(){
        //Init
        twoSetSetup();

        //Act
        boolean isSubsetTest = set1.isSubset(set2);

        //Assert
        assertFalse(isSubsetTest);
    }

    @Test
    public void selfSubSetTest(){
        //Init
        oneSetSetup();

        //Act
        boolean isSubsetTest = set1.isSubset(set1);

        //Assert
        assertTrue(isSubsetTest);
    }

    //COMPLIMENT TESTS
    @Test
    public void complementTest(){
        //Init
        subSetSetup();
        //Act
        set1.complement(set2);

        //Assert
        assertEquals("{4, 5, 6}", set1.toString());
    }

    @Test
    public void selfComplement(){
        // Init
        oneSetSetup();

        // Act
        ConcurrentModificationException e = null;
        try {
            set1.complement(set1);
        } catch (ConcurrentModificationException ex) {
            e = ex;
        }

        // Assert
        assertNotNull("Expected ConcurrentModificationException", e);
    }

    @Test
    public void UniverseToSubSetComplementTest(){
        //Init
        subSetSetup();

        //Act
        set2.complement(set1);

        //Assert
        assertEquals("{1, 2, 3, 4, 5, 6}", set2.toString());
    }
}