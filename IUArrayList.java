import java.util.*;

/**
 * Array-based implementation of IndexedUnsortedList.
 * 
 * @author 
 *
 * @param <E> type to store
 */
public class IUArrayList<E> implements IndexedUnsortedList<E> {
	private static final int DEFAULT_CAPACITY = 10;
	private static final int NOT_FOUND = -1;
	
	private E[] array;
	private int rear;
	private int modCount; // DO NOT REMOVE ME
	private int count;
	
	/** Creates an empty list with default initial capacity */
	public IUArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	/** 
	 * Creates an empty list with the given initial capacity
	 * @param initialCapacity
	 */
	@SuppressWarnings("unchecked")
	public IUArrayList(int initialCapacity) {
		array = (E[])(new Object[initialCapacity]);
		rear = 0;
		modCount = 0; // DO NOT REMOVE ME
	}
	
	/** Double the capacity of array */
	private void expandCapacity() {
		array = Arrays.copyOf(array, array.length*2);
	}

	@Override
	public void addToFront(E element) {
		if (array.length == this.size()) {
			expandCapacity();
		} 
		for (int i = rear; i > 0; i-- ) {
			array[i] = array[i - 1];
		}
		array[0] = element;
		rear++;
		count++;
		modCount++; // DO NOT REMOVE ME
	}

	@Override
	public void addToRear(E element) {
		if (array.length == this.size()) {
			expandCapacity();
		} 
		array[rear] = element;
		rear++;
		count++;
		modCount++; // DO NOT REMOVE ME
	}

	@Override
	public void add(E element) {
		// TODO 
		if (array.length == this.size()) {
			expandCapacity();
		} 
		array[rear] = element;
		rear++;
		count++;
		modCount++; // DO NOT REMOVE ME
	}

	@Override
	public void addAfter(E element, E target) {
		// TODO 
		int targetIndex = indexOf(target);
		if (targetIndex == NOT_FOUND) { throw new NoSuchElementException(); }
		if (size() == array.length) {
			expandCapacity();
		}
		for(int i = size(); i > (targetIndex); i--) {
			array[i+1] = array[i];
		}
		array[targetIndex+1] = element;
		rear++;
		count++;
		modCount++; // DO NOT REMOVE ME
	}

	@Override
	public void add(int index, E element) {
		// TODO 
		if (index < 0 || index > size()) { throw new IndexOutOfBoundsException(); }
		if (size() == array.length) {
			expandCapacity();
		}
		for(int i = size(); i >= (index); i--) {
			array[i+1] = array[i];
		}
		array[index] = element;
		rear++;
		count++;
		modCount++; // DO NOT REMOVE ME
	}

	@Override
	public E removeFirst() {
		// TODO @watermelon2718
		if (size() == 0) {
			throw new NoSuchElementException();
		}
		modCount++; // DO NOT REMOVE ME
		return remove(array[0]);
	}

	@Override
	public E removeLast() {
		// TODO @watermelon2718
		if (size() == 0) {
			throw new NoSuchElementException();
		}
		modCount++; // DO NOT REMOVE ME
		return remove(array[rear-1]);
	}

	@Override
	public E remove(E element) {
		//TODO @watermelon2718
		// int index = indexOf(element);
		// return remove(index);
		int index = indexOf(element);
		if (index == NOT_FOUND) {
			throw new NoSuchElementException();
		}
		E temp = array[index];
		array[index] = null;

		for (int i = index; i < rear; i++) {
			array[i] = array[i+1];
		}
		rear--;
		array[rear] = null;
		count--;
		modCount++;
		return temp;
	}

	@Override
	public E remove(int index) {
		// TODO @watermelon2718
		if (index < 0 || index >= size()) { throw new IndexOutOfBoundsException(); }
		return remove(array[index]);
	}

	@Override
	public void set(int index, E element) {
		// TODO MD
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		array[index] = element;
		modCount++; // DO NOT REMOVE ME
	}

	@Override
	public E get(int index) {
		// TODO MD
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		return array[index];
	}
	
	@Override
	public int indexOf(E element) {
		int index = NOT_FOUND;
		
		if (!isEmpty()) {
			int i = 0;
			while (index == NOT_FOUND && i < rear) {
				if (element.equals(array[i])) {
					index = i;
				} else {
					i++;
				}
			}
		}
		
		return index;
	}

	@Override
	public E first() {
		if (size() == 0) { throw new NoSuchElementException("List is empty"); }
		return array[0];
	}

	@Override
	public E last() { 
		if (size() == 0) { throw new NoSuchElementException("List is empty"); }
		return array[rear-1];
	}

	@Override
	public boolean contains(E target) {
		return (indexOf(target) != NOT_FOUND);
	}

	// @Override
	public boolean isEmpty() {
		return size() == 0; 
	}

	// @Override
	public int size() {
		return count;
	}

	@Override
	public String toString() {
		String result = "[";
		int index = this.rear-1;

		for (int i = 0; i < size(); i++) {
			if (i > 0 ) {
				result += ", ";
			}
			result += array[index];
			index--;   // not entirely sure this works
		}
		return result + "]";
	}

	// IGNORE THE FOLLOWING COMMENTED OUT CODE UNTIL LAB 10
	// DON'T DELETE ME, HOWEVER!!!
	public Iterator<E> iterator() {
		// return new IUArrayListIterator(); // UNCOMMENT ME IN LAB 10
		return null; // REMOVE ME IN LAB 10
	}

	// UNCOMMENT THE CODE BELOW IN LAB 10
	
	// private class IUArrayListIterator implements Iterator<E> {

	// 	private int iterModCount, current;
	// 	private boolean canRemove;

	// 	public IUArrayListIterator() {
	// 		iterModCount = modCount;
	// 		current = 0;
	// 		canRemove = false;
	// 	}

	// 	@Override
	// 	public boolean hasNext() {
    //         if (iterModCount != modCount) {
    //             throw new ConcurrentModificationException();
    //         }
    //         return current < rear;
	// 	}

	// 	@Override
	// 	public E next() {
    //         if (!hasNext()) {
    //             throw new NoSuchElementException();
    //         }
    //         E item = array[current];
	// 		current++;
    //         canRemove = true;
    //         return item;
	// 	}

	// 	@Override
	// 	public void remove() {
    //         if (iterModCount != modCount) {
    //             throw new ConcurrentModificationException();
    //         }
    //         if (!canRemove) {
    //             throw new IllegalStateException();
    //         }
    //         // remove the element in the array at index current-1
    //         // presumably decrement the rear
    //         // presumably the modCount is getting incremented
	// 		// all indices have to back up by one
	// 		current--;
	// 		rear--;
	// 		// shift elements to the left
	// 		for (int i = current; i < rear; i++) {
	// 			array[i] = array[i + 1];
	// 		}
	// 		array[rear] = null;
	// 		modCount++;
	// 		iterModCount++;
	// 		// Can only remove the LAST "seen" element
	// 		// set back to a non-removal state 
    //         canRemove = false;
	// 	}
		
	// }

	// IGNORE THE FOLLOWING CODE
	// DON'T DELETE ME, HOWEVER!!!
	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int startingIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
