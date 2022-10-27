import java.io.IOException;
import java.util.NoSuchElementException;

// Max-heap implementation by Patrick Sullivan, based on OpenDSA Heap code
// Can use `java -ea` (Java's VM arguments) to Enable Assertions
// These assertions will check for valid heap positions

// Many of these methods are not going to be useful for ExternalSorting...
// Prune those methods out if you don't want to test them.
/**
 * 
 * @author amado
 *
 */
public class MaxHeap {
    private int capacity; // Maximum size of the heap
    private int n; // Number of things currently in heap
    private BpInterface bpool;

    // Constructor supporting preloading of heap contents
    /**
     * 
     * @param h
     * @param heapSize
     * @param capacity
     * @throws IOException
     * @throws NoSuchElementException
     */
    public MaxHeap(BpInterface pool, int heapSize, int capacity)
        throws NoSuchElementException,
        IOException {
// assert capacity <= pool.getlength() : "capacity is beyond array limits";
// assert heapSize <= capacity : "Heap size is beyond max";
        bpool = pool;
        n = heapSize;
        this.capacity = capacity;
        buildHeap();
    }


    // Return position for left child of pos
    /**
     * 
     * @param pos
     * @return
     */
    public static int leftChild(int pos) {
        return 2 * pos + 1;
    }


    // Return position for right child of pos
    /**
     * 
     * @param pos
     * @return
     */
    public static int rightChild(int pos) {
        return 2 * pos + 2;
    }


    // Return position for the parent of pos
    /**
     * 
     * @param pos
     * @return
     */
    public static int parent(int pos) {
        return (pos - 1) / 2;
    }


    // Forcefully changes the heap size. May require build-heap afterwards
    /**
     * 
     * @param newSize
     */
    public void setHeapSize(int newSize) {
        n = newSize;
    }


    // Return current size of the heap
    /**
     * 
     * @return
     */
    public int heapSize() {
        return n;
    }


    // Return true if pos a leaf position, false otherwise
    /**
     * 
     * @param pos
     * @return
     */
    public boolean isLeaf(int pos) {
        return (n / 2 <= pos) && (pos < n);
    }


    // Organize contents of array to satisfy the heap structure
    /**
     * @throws IOException
     * @throws NoSuchElementException
     * 
     */
    public void buildHeap() throws NoSuchElementException, IOException {
        for (int i = parent(n - 1); i >= 0; i--) {
            siftDown(i);
        }
    }


    // Moves an element down to its correct place
    /**
     * 
     * @param pos
     * @throws IOException
     * @throws NoSuchElementException
     */
    public void siftDown(int pos) throws NoSuchElementException, IOException {
        assert (0 <= pos && pos < n) : "Invalid heap position";
        while (!isLeaf(pos)) {
            int child = leftChild(pos);
            if ((child + 1 < n) && isGreaterThan(child + 1, child)) {
                child = child + 1; // child is now index with the smaller value
            }
            if (!isGreaterThan(child, pos)) {
                return; // stop early
            }
            swap(pos, child);
            pos = child; // keep sifting down
        }
    }


    // Moves an element up to its correct place
    /**
     * 
     * @param pos
     * @throws IOException
     * @throws NoSuchElementException
     */
    public void siftUp(int pos) throws NoSuchElementException, IOException {
        assert (0 <= pos && pos < n) : "Invalid heap position";
        while (pos > 0) {
            int parent = parent(pos);
            if (isGreaterThan(parent, pos)) {
                return; // stop early
            }
            swap(pos, parent);
            pos = parent; // keep sifting up
        }
    }


    // Remove and return maximum value
    /**
     * 
     * @return
     * @throws IOException
     * @throws NoSuchElementException
     */
    public Record removeMax() throws NoSuchElementException, IOException {
        assert n > 0 : "Heap is empty; cannot remove";
        n--;
        if (n > 0) {
            swap(0, n); // Swap maximum with last value
            siftDown(0); // Put new heap root val in correct place
        }
        return bpool.getBpRecord(n);
    }


    // does fundamental comparison used for checking heap validity
    /**
     * 
     * @param pos1
     * @param pos2
     * @return
     * @throws IOException
     */
    private boolean isGreaterThan(int pos1, int pos2) throws IOException {
        return bpool.getBpRecord(pos1).compareTo(bpool.getBpRecord(pos2)) > 0;
    }


    /**
     * The sort method in the heap
     * 
     * @param comp
     *            The objects we will be comparing to sort
     * @throws IOException
     * @throws NoSuchElementException
     */
    public void Sort() throws NoSuchElementException, IOException {
        for (int idex = 0; idex < capacity; idex++) {
            removeMax();
        }

    }


    /**
     * 
     * @param pos1
     * @param pos2
     * @throws IOException
     * @throws NoSuchElementException
     */
    public void swap(int pos1, int pos2)
        throws NoSuchElementException,
        IOException {

        // System.out.println(bpool.getBpRecord(pos1).toString());
        // System.out.println(bpool.getBpRecord(pos2).toString());
        Record Temp = new Record(bpool.getBpRecord(pos1).getKey(), bpool
            .getBpRecord(pos1).getValue());
        Record Temp2 = new Record(bpool.getBpRecord(pos2).getKey(), bpool
            .getBpRecord(pos2).getValue());

        // System.out.println(Temp.toString());
// if (bpool.getBpRecord(pos2).getKey() == 20085) {
// System.out.println("p2: " + bpool.getBpRecord(pos2).getValue());
// System.out.println("p1: " + bpool.getBpRecord(pos1).getValue());
// }

        bpool.setRecord(pos1, Temp2);
        bpool.setRecord(pos2, Temp);

        // System.out.println("p2: " + bpool.getBpRecord(pos2).getKey() + ", " +
        // bpool.getBpRecord(pos2).getValue());
        //System.out.println("p1: " + bpool.getBpRecord(pos1).getKey() + ", "
           // + bpool.getBpRecord(pos1).getValue());

        // System.out.println(bpool.getBpRecord(pos1).toString());
        // System.out.println(bpool.getBpRecord(pos2).toString());

    }

}
