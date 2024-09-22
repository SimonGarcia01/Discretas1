import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

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

    @Test
    public void createSetTest(){
        //Init
        defaultSetup();

        //Act
        set1 = new Set<>();

        //Assert
        assertNotNull(set1);
    }

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
    public void deleteTest(){
        //Init
        oneSetSetup();

        //Act
        set1.delete(2);

        //Assert
        assertEquals("{1, 3}", set1.toString());
    }

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
    public void unionTest(){
         //Init
        twoSetSetup();
        
        //Act
        set1.union(set2);

        //Assert
        assertEquals("{1, 2, 3, 4, 5, 6, 7, 8, 9}", set1.toString());
    }

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
    public void differenceTest(){
        //Init
        twoSetSetup();

        //Act
        set1.difference(set2, 1);

        //Assert
        assertEquals("{1, 2, 3}", set1.toString());
    }

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
    public void complementTest(){
        //Init
        subSetSetup();
        //Act
        set1.complement(set2);

        //Assert
        assertEquals("{4, 5, 6}", set1.toString());
    }
}