import java.util.NoSuchElementException;

/**
 * The linkedlist class
 * 
 * @author Jmmurphy
 * @version 10.22.2022
 *
 * @param <E>
 *            the generic for the list
 */
public class LinkedList<E> {
    private Link<E> head;
    private Link<E> tail;
    private Link<E> curr;
    private int listSize;

    /**
     * The constructor for the linked list
     * 
     * @param size
     *            The size of the list
     */
    public LinkedList(int size) {
        this();
    }


    /**
     * The constructor for the linked list
     * 
     */
    public LinkedList() {
        clear();
    }


    /**
     * The clear method for the linked list
     * 
     */
    public void clear() {
        tail = new Link<E>(null);
        curr = tail;
        head = new Link<E>(tail);
        listSize = 0;
    }


    /**
     * The insert method for the linked list
     * 
     * @param it
     *            the element to insert
     * @return a boolean depending on the insert value
     * 
     */
    public boolean insert(E it) {
        curr.setNext(new Link<E>(curr.element(), curr.next()));
        curr.setElement(it);
        if (tail == curr) {
            tail = curr.next();
        }
        listSize++;
        return true;
    }


    /**
     * The getter head method for the linked list
     * 
     * @return the head
     * 
     */
    public Link<E> getHead() {
        return head;
    }


    /**
     * The append for the linked list
     * 
     * @param it
     *            The element to append
     * @return the boolean value depend the element
     */
    public boolean append(E it) {
        tail.setNext(new Link<E>(null));
        tail.setElement(it);
        tail = tail.next();
        listSize++;
        return true;
    }


    /**
     * The remove method for the linked list
     * 
     * @return the element we removed
     * @throws NoSuchElementException
     */
    public E remove() throws NoSuchElementException {
        if (curr == tail) {
            throw new NoSuchElementException("remove() in LList has current of "
                + curr + " and size of " + listSize
                + " that is not a a valid element");
        }
        E it = curr.element();
        curr.setElement(curr.next().element());
        if (curr.next() == tail) {
            tail = curr;
        }
        curr.setNext(curr.next().next());
        listSize--;
        return it;
    }


    /**
     * The movetoStart method for the linked list
     * 
     */
    public void moveToStart() {
        curr = head.next();
    }


    /**
     * The moveToEnd method for the linked list
     * 
     */
    public void moveToEnd() {
        curr = tail;
    }


    /**
     * The addtoFront method adds nodes to the beignind of the linked
     * list
     * 
     * @param it
     *            the element to insert into the front
     */
    public void addtoFront(E it) {
        moveToStart();
        insert(it);
    }


    /**
     * Removes the last nodes
     */
    public E removelast() {
        moveToPos(listSize - 1);
        return remove();
    }


    /**
     * 
     */

    /**
     * The prev method for the linked list
     * 
     */
    public void prev() {
        if (head.next() == curr) {
            return;
        }
        Link<E> temp = head;

        while (temp.next() != curr) {
            temp = temp.next();
        }
        curr = temp;
    }


    /**
     * Moves most recently used data to the front
     */
    public void LRU(E element) {
        moveToStart();
        while (curr != null) {
            if (curr.element().equals(element)) {
                remove();
                addtoFront(element);
                return;
            }
            next();
        }

    }


    /**
     * The next method for the linked list
     * 
     */
    public void next() {
        if (curr != tail) {
            curr = curr.next();
        }
    }


    /**
     * The getNext method for the linked list
     * 
     * @return the currentnext value
     */
    public Link<E> getNext() {
        return curr.next();

    }


    /**
     * The length method for the linked list
     * 
     * @return the int length value
     */
    public int length() {
        return listSize;
    }


    /**
     * The currPos method for the linked list
     * 
     * @return the postion int at
     */
    public int currPos() {
        Link<E> temp = head.next();
        int i;
        for (i = 0; curr != temp; i++) {
            temp = temp.next();
        }
        return i;
    }


    /**
     * The currPos method for the linked list
     * 
     * @param pos
     *            the position to move
     * @return the postion to move too
     */
    public boolean moveToPos(int pos) {
        if ((pos < 0) || (pos > listSize)) {
            return false;
        }
        curr = head.next();
        for (int i = 0; i < pos; i++) {
            curr = curr.next();
        }
        return true;
    }


    /**
     * The is At End method
     * 
     * @return the tail
     */
    public boolean isAtEnd() {
        return curr == tail;
    }


    /**
     * The method gets the value of the current element
     * 
     * @return the current element
     * @throws NoSuchElementException
     */
    public E getValue() throws NoSuchElementException {
        if (curr == tail) // No current element
        {
            throw new NoSuchElementException(
                "getvalue() in LList has current of " + curr + " and size of "
                    + listSize + " that is not a a valid element");
        }
        return curr.element();
    }


    /**
     * Sees if the list is empty
     * 
     * @return true if empty, false if not
     */
    public boolean isEmpty() {
        return listSize == 0;
    }
}




/**
 * 
 * @author Jmmurphy
 * @version 10.5.2022
 *
 * @param <E>
 *            the element in the list
 */
class Link<E> {
    private E e;
    private Link<E> n;

    /**
     * 
     * @param it
     *            the value for the link list
     * @param inn
     *            the elment for the linked list
     */
    public Link(E it, Link<E> inn) {
        e = it;
        n = inn;
    }


    /**
     * 
     * @param inn
     *            the elment for the linked list
     */
    public Link(Link<E> inn) {
        e = null;
        n = inn;
    }


    /**
     * The elment method for link
     * 
     * @return the element
     */
    public E element() {
        return e;
    }


    /**
     * The set element method
     * 
     * @param it
     *            The element
     * @return the element
     */
    public E setElement(E it) {
        e = it;
        return e;
    }


    /**
     * The set element method
     *
     * @return next element
     */

    public Link<E> next() {
        return n;
    }


    /**
     * The set element method
     * 
     * @param inn
     *            the element
     * @return set element
     */
    public Link<E> setNext(Link<E> inn) {
        n = inn;
        return n;
    }
}
