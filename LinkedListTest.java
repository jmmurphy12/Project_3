import java.util.NoSuchElementException;
import student.TestCase;

/**
 * Tests the LinkedList class
 * 
 * @author Amado Jain
 * @author Josh Murphy
 * @version 2022.10.30
 * 
 */
public class LinkedListTest extends TestCase {
    private LinkedList<String> list;

    /**
     * test the clear method
     */
    public void testclear() {
        list = new LinkedList<String>();
        String name = "amado";
        list.insert(name);
        list.clear();
        assertEquals(0, list.length());
    }


    /**
     * test the insert method
     */
    public void testinsert() {
        list = new LinkedList<String>();
        String name = "hi and amado ";
        list.insert(name);
        list.insert(name);
        assertEquals(2, list.length());
        list.clear();
        list.insert(name);
        assertEquals(1, list.length());
        list.insert(name);
        list.insert(name);
        list.insert(name);
        list.insert(name);
        list.insert(name);
        list.insert(name);
        list.insert(name);
        list.insert(name);
        list.insert(name);
        list.insert(name);
        list.insert(name);
        list.insert(name);
        list.insert(name);
        list.insert(name);
        list.insert(name);
        list.insert(name);
        assertEquals(17, list.length());

    }


    /**
     * test the gethead method
     */
    public void testgetHead() {
        list = new LinkedList<String>();
        String secname = "hi ";
        list.insert(secname);
        assertEquals(null, list.getHead().element());
    }


    /**
     * test the getnext method
     */
    public void testgetnext() {
        list = new LinkedList<String>();
        String secname = "hi ";
        list.insert(secname);
        assertEquals(null, list.getNext().element());
    }


    /**
     * test the remove method
     */
    public void testremove() {
        list = new LinkedList<String>();
        String name = "amado ";
        String secname = "hi ";
        list.insert(secname);
        assertFuzzyEquals("hi", list.remove());
        list.insert(secname);
        list.insert(name);
        assertFuzzyEquals("amado", list.remove());
        list.clear();
        try {
            list.remove();
        }
        catch (NoSuchElementException e) {
            System.out.println("remove() in LList has current of "
                + " and size of " + list.length()
                + " that is not a a valid element");
            System.out.println(e.getMessage());
        }
        // ----------------------
        list.insert(secname);
        assertFuzzyEquals("hi", list.remove());

    }


    /**
     * test the movetoStart method
     */
    public void testmoveToStart() {
        list = new LinkedList<String>();
        String name = "amado";
        list.insert(name);
        list.moveToStart();
        list.append(name);
        assertFalse(list.isAtEnd());
    }


    /**
     * test the movetoEnd method
     */
    public void testmoveToEnd() {
        list = new LinkedList<String>();
        String name = "amado";
        list.insert(name);
        list.moveToEnd();
        list.append(name);
        assertEquals("amado", list.getValue());

    }


    /**
     * test the next method
     */
    public void testnext() {
        list = new LinkedList<String>();
        String name = "amado";
        list.insert(name);
        list.append(name);
        list.next();
        assertSame("amado", list.getValue());
        list.clear();
        list.next();
    }


    /**
     * test the length method
     */
    public void testlength() {
        list = new LinkedList<String>();
        String name = "amado ";
        list.insert(name);
        assertEquals(1, list.length());

    }


    /**
     * test the isAtEnd method
     */
    public void testisAtEnd() {
        list = new LinkedList<String>();
        String name = "amado ";
        list.insert(name);
        assertFalse(list.isAtEnd());
        list.clear();
        assertTrue(list.isAtEnd());

    }


    /**
     * test the prev method
     */
    public void testprev() {
        list = new LinkedList<String>();
        String name = "amado";
        String nametwo = "hi";
        String namethree = "hello";
        list.append(name);
        list.append(nametwo);
        list.append(namethree);
        list.next();
        list.next();
        list.prev();
        assertEquals(1, list.currPos());
        list.next();
        list.clear();
        list.prev();
        assertEquals(0, list.currPos());
    }


    /**
     * test the moveTOpos method
     */
    public void testmoveToPos() {
        list = new LinkedList<String>();
        String name = "amado";
        String nametwo = "hi";
        String namethree = "hello";
        list.append(name);
        list.append(nametwo);
        list.append(namethree);
        list.next();
        list.moveToPos(2);
        assertEquals(2, list.currPos());
        list.moveToPos(-2);
        assertEquals(2, list.currPos());
        list.moveToPos(10000);
        assertEquals(2, list.currPos());

    }


    /**
     * test the getValue method
     */
    public void testgetValue() {
        list = new LinkedList<String>();
        String name = "amado";
        String secname = "hi";
        list.insert(name);
        assertEquals("amado", list.getValue());
        list.insert(secname);
        assertFuzzyEquals("hi", list.getValue());
        list.clear();
        try {
            list.getValue();
        }
        catch (NoSuchElementException e) {
            System.out.println("getvalue() in LList has current of " + list
                .isAtEnd() + " and size of " + list.length()
                + " that is not a a valid element");
            System.out.println(e.getMessage());
        }
    }


    /**
     * test the isEmpty method
     */
    public void testisEmpty() {
        list = new LinkedList<String>();
        String name = "hi and amado ";
        list.insert(name);
        assertFalse(list.isEmpty());
        LinkedList<String> sectester = new LinkedList<String>();
        assertTrue(sectester.isEmpty());
    }

}
