import java.util.*;

/**
 *  Interface for an Iterable, Indexed, Unsorted List ADT.
 *  Iterators and ListIterators provided by the list are required
 *  to be "fail-fast" and throw ConcurrentModificationException if
 *  the iterator detects any change to the list from another source. 
 *  Note: "Unsorted" only means that it is not inherently maintained
 *  in a sorted order. It may or may not be sorted.
 *
 * @author CS 221
 */
public interface IndexedUnsortedList<E> extends Iterable<E>
{
    /**  
     * Adds the specified element to the front of this list. 
     *
     * @param element the element to be added to the front of this list    
     */
    public void addToFront(E element);  

    /**  
     * Adds the specified element to the rear of this list. 
     *
     * @param element the element to be added to the rear of this list    
     */
    public void addToRear(E element); 

    /**  
     * Adds the specified element to the rear of this list. 
     *
     * @param element  the element to be added to the rear of the list    
     */
    public void add(E element);

    /**  
     * Adds the specified element after the specified target. 
     *
     * @param element the element to be added after the target
     * @param target  the target is the item that the element will be added after
     * @throws NoSuchElementException if target element is not in this list
     */
    public void addAfter(E element, E target);
    
    /**  
     * Inserts the specified element at the specified index. 
     * 
     * @param index   the index into the array to which the element is to be inserted.
     * @param element the element to be inserted into the array
     * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt; size)
     */
    public void add(int index, E element);

    /**  
     * Removes and returns the first element from this list. 
     * 
     * @return the first element from this list
     * @throws NoSuchElementException if list contains no elements
     */
    public E removeFirst();

    /**  
     * Removes and returns the last element from this list. 
     *
     * @return the last element from this list
     * @throws NoSuchElementException if list contains no elements
     */
    public E removeLast();

    /**  
     * Removes and returns the first element from the list matching the specified element.
     *
     * @param element the element to be removed from the list
     * @return removed element
     * @throws NoSuchElementException if element is not in this list
     */
    public E remove(E element);

    /**  
     * Removes  and returns the element at the specified index. 
     *
     * @param index the index of the element to be retrieved
     * @return the element at the given index
     * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt;= size)
     */
    public E remove(int index); 
        
    /**  
     * Sets the element at the specified index. 
     *
     * @param index   the index into the array to which the element is to be set
     * @param element the element to be set into the list
     * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt;= size)
     */
    public void set(int index, E element);

    /**  
     * Returns a reference to the element at the specified index. 
     *
     * @param index  the index to which the reference is to be retrieved from
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt;= size)
     */
    public E get(int index);

    /**  
     * Returns the index of the first element from the list matching the specified element. 
     *
     * @param element  the element for the index is to be retrieved
     * @return the integer index for this element or -1 if element is not in the list    
     */
    public int indexOf(E element);

    /**  
     * Returns a reference to the first element in this list. 
     *
     * @return a reference to the first element in this list
     * @throws NoSuchElementException if list contains no elements
     */
    public E first();

    /**  
     * Returns a reference to the last element in this list. 
     *
     * @return a reference to the last element in this list
     * @throws NoSuchElementException if list contains no elements
     */
    public E last();

    /**  
     * Returns true if this list contains the specified target element. 
     *
     * @param target the target that is being sought in the list
     * @return true if the list contains this element, else false
     */
    public boolean contains(E target);

    /**  
     * Returns true if this list contains no elements. 
     *
     * @return true if this list contains no elements
     */
    public boolean isEmpty();

    /**  
     * Returns the number of elements in this list. 
     *
     * @return the integer representation of number of elements in this list
     */
    public int size();

    /**  
     * Returns a string representation of this list. 
     *
     * @return a string representation of this list
     */
    public String toString();

    /**  
     * Returns an Iterator for the elements in this list. 
     *
     * @return an Iterator over the elements in this list
     */
    public Iterator<E> iterator();

    /**  
     * Returns a ListIterator for the elements in this list. 
     *
     * @return a ListIterator over the elements in this list
     *
     * @throws UnsupportedOperationException if not implemented
     */
    public ListIterator<E> listIterator();

    /**  
     * Returns a ListIterator for the elements in this list, with
     * the iterator positioned before the specified index. 
     *
     * @param startingIndex the index to start at
     * @return a ListIterator over the elements in this list
     *
     * @throws UnsupportedOperationException if not implemented
     */
    public ListIterator<E> listIterator(int startingIndex);
}


//To create the Javadoc files do: javadoc *.java -d docs