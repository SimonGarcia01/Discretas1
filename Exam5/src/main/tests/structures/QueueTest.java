package structures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structures.IQueue;
import structures.Queue;

public class QueueTest {

    private IQueue<String> queue;

    @BeforeEach
    void setUp() {
        // TODO - instantiate with own implementation.
        queue = new Queue<String>();
    }

    @Test
    void testEnqueueOneElement() {
        // Arrange
        String cat = "Michi";

        // Act
        queue.enqueue(cat);

        // Assert
        assertEquals(cat, queue.dequeue());
    }

    @Test
    void testEnqueueTwoElements() {
        // Arrange
        String cat1 = "Michi";
        String cat2 = "Gus";

        // Act
        queue.enqueue(cat1);
        queue.enqueue(cat2);

        // Assert
        assertEquals(cat1, queue.dequeue());
        assertEquals(cat2, queue.dequeue());
    }

    @Test
    void testEnqueueOneElementGetsOnFront() {
        // Arrange
        String cat1 = "Michi";
        String cat2 = "Gus";

        // Act
        queue.enqueue(cat1);
        queue.enqueue(cat2);

        queue.front();

        // Assert
        assertEquals(cat1, queue.front());
        assertNotEquals(cat2, queue.front());
    }

    @Test
    void testFrontEmptyQueue() {
        // Act - Arrange
        assertNull(queue.front());
    }

    @Test
    void testFrontNonEmpty() {
        // Arrange
        String cat1 = "Gus";

        // Act
        queue.enqueue(cat1);

        // Assert
        assertEquals(cat1, queue.front());
    }

    @Test
    void testFrontAfterDequeue() {
        // Arrange
        String cat1 = "Gus";

        // Act
        queue.enqueue(cat1);
        queue.dequeue();

        // Assert
        assertNull(queue.front());
    }

    @Test
    void testDequeueWhenCreated() {
        // Act
        String element = queue.dequeue();

        // Assert
        assertNull(element);
    }

    @Test
    void testDequeueWhenOneElements() {
        // Arrange
        String cat1 = "Gus";

        // Act
        queue.enqueue(cat1);

        // Assert
        assertEquals(cat1, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    void testDequeueWhenMultipleElements() {
        // Arrange
        String cat1 = "Gus";
        String cat2 = "Michi";
        String cat3 = "Tyr";

        // Act
        queue.enqueue(cat1);
        queue.enqueue(cat2);
        queue.enqueue(cat3);
        queue.dequeue();

        // Assert
        assertEquals(cat2, queue.dequeue());
        assertFalse(queue.isEmpty());
    }

    @Test
    void testIsEmptyAfterCreation() {
        // Act - Assert
        assertTrue(queue.isEmpty());
    }

    @Test
    void testIsEmptyAfterEnqueue() {
        // Arrange
        String cat = "Gus";
        queue.enqueue(cat);

        // Act - Assert
        assertFalse(queue.isEmpty());
    }

    @Test
    void testIsEmptyAfterDequeuMultipleElements() {
        // Arrange
        String cat1 = "Gus";
        String cat2 = "Michi";
        queue.enqueue(cat1);
        queue.enqueue(cat2);
        queue.dequeue();

        // Act - Assert
        assertFalse(queue.isEmpty());
    }
}
