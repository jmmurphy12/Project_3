import java.io.IOException;
import java.util.NoSuchElementException;

// Max-heap implementation by Patrick Sullivan, based on OpenDSA Heap code
// Can use `java -ea` (Java's VM arguments) to Enable Assertions
// These assertions will check for valid heap positions

// Many of these methods are not going to be useful for ExternalSorting...
// Prune those methods out if you don't want to test them.

/**
 * Creates the heap data structure
 * 
 * @author Amado Jain
 * @author Josh Murphy
 * @version 2022.10.30
 * 
 */
public class MaxHeap {
    private int capacity; // Maximum size of the heap
    private int n; // Number of things currently in heap
    private BpInterface bpool;

    /**
     * Constructor supporting preloading of heap contents
     * 
     * @param pool
     *            the buffer pool being used
     * @param heapSize
     *            the amount of data that will be in the heap
     * @param capacity
     *            the max number of data in the heap
     * @throws NoSuchElementException
     *             if the element passed into the parameter doesn't exist
     * @throws IOException
     *             if a file error occursA
     */
    public MaxHeap(BpInterface pool, int heapSize, int capacity)
        throws NoSuchElementException,
        IOException {
        bpool = pool;
        n = heapSize;
        this.capacity = capacity;
        buildHeap();
    }


    /**
     * Return position for left child of pos
     * 
     * @param pos
     *            the position of the parent
     * @return
     *         the position of the left child
     */
    public static int leftChild(int pos) {
        return 2 * pos + 1;
    }


    /**
     * Return position for right child of pos
     * 
     * @param pos
     *            the position of the parent
     * @return
     *         the position of the right child
     */
    public static int rightChild(int pos) {
        return 2 * pos + 2;
    }


    /**
     * Return position for the parent of pos
     * 
     * @param pos
     *            the position of a child
     * @return
     *         the position of the parent
     */
    public static int parent(int pos) {
        return (pos - 1) / 2;
    }


    /**
     * Forcefully changes the heap size. May require build-heap afterwards
     * 
     * @param newSize
     *            the new size of the heap
     */
    public void setHeapSize(int newSize) {
        n = newSize;
    }


    /**
     * Return current size of the heap
     * 
     * @return
     *         the heap size
     */
    public int heapSize() {
        return n;
    }


    /**
     * Return true if pos a leaf position, false otherwise
     * 
     * @param pos
     *            the position of the "node"
     * @return
     *         true if it is a leaf, false if not
     */
    public boolean isLeaf(int pos) {
        return (n / 2 <= pos) && (pos < n);
    }


    /**
     * Organize contents of array to satisfy the heap structure
     * 
     * @throws IOException
     *             if a file problem occurs
     * @throws NoSuchElementException
     *             if an element doesn't exist
     * 
     */
    public void buildHeap() throws NoSuchElementException, IOException {
        for (int i = parent(n - 1); i >= 0; i--) {
            siftDown(i);
        }
    }


    /**
     * Moves an element down to its correct place
     * 
     * @param pos
     *            the position of the element being sifted down
     * @throws IOException
     *             if a file error occurs
     * @throws NoSuchElementException
     *             if an element doesn't exist
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


    /**
     * Moves an element up to its correct place
     * 
     * @param pos
     *            the position of the element being sifted up
     * @throws IOException
     *             a file error occurs
     * @throws NoSuchElementException
     *             an element doesn't exist
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


    /**
     * Remove and return maximum value
     * 
     * @return
     *         the record removed
     * @throws IOException
     *             a file error occurs
     * @throws NoSuchElementException
     *             an element doesn't exist
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


    /**
     * does fundamental comparison used for checking heap validity
     * 
     * @param pos1
     *            the position of the first node being compared
     * @param pos2
     *            the position of the second node being compared
     * @return
     *         true if pos1 is greater than pos2, false if not
     * @throws IOException
     *             a file error occurs
     */
    private boolean isGreaterThan(int pos1, int pos2) throws IOException {
        return bpool.getBpRecord(pos1).compareTo(bpool.getBpRecord(pos2)) > 0;
    }


    /**
     * The sort method in the heap
     * 
     * @throws IOException
     *             a file error occurs
     * @throws NoSuchElementException
     *             an element doesn't exist
     */
    public void sort() throws NoSuchElementException, IOException {
        for (int idex = 0; idex < capacity; idex++) {
            removeMax();
        }

    }


    /**
     * Swaps two elements in the file
     * 
     * @param pos1
     *            the position of the first element
     * @param pos2
     *            the position of the second element
     * @throws IOException
     *             a file error occurs
     * @throws NoSuchElementException
     *             an element doesn't exist
     */
    public void swap(int pos1, int pos2)
        throws NoSuchElementException,
        IOException {

        Record temp = new Record(bpool.getBpRecord(pos1).getKey(), bpool
            .getBpRecord(pos1).getValue());
        Record temp2 = new Record(bpool.getBpRecord(pos2).getKey(), bpool
            .getBpRecord(pos2).getValue());

        bpool.setRecord(pos1, temp2);
        bpool.setRecord(pos2, temp);

    }

}
