
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import student.TestCase;

public class MaxHeapTest extends student.TestCase {

    public void testMinHeap() throws NoSuchElementException, IOException {

        Integer[] vals = { 2, 7, 4, 9, 2, 1 };

        MaxHeap<Integer> heap = new MaxHeap<Integer>(vals, 6, 6);
        // This constructor calls build-heap automatically

        assertEquals(6, heap.heapSize());
        assertEquals(9, (int)vals[0]);
        assertEquals(9, (int)heap.removeMax());
        
        assertEquals(7, (int)vals[0]);
        assertEquals(9, (int)vals[5]); // still in array, but out of heap
        assertEquals(5, heap.heapSize());
        
        heap.insert(3);
        assertEquals(7, (int)vals[0]);
        assertEquals(6, heap.heapSize());
        
        assertEquals(7, (int)heap.removeMax());
        assertEquals(4, (int)heap.removeMax());
        assertEquals(3, (int)heap.removeMax());
        assertEquals(2, (int)heap.removeMax());
        assertEquals(2, (int)heap.removeMax());
        assertEquals(1, (int)heap.removeMax());
    }

}
