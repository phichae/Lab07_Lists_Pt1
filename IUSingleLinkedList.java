import java.util.*;

/**
 * Single-linked node implementation of IndexedUnsortedList.
 * An Iterator with working remove() method is implemented, but
 * ListIterator is unsupported.
 * 
 * @author 
 * 
 * @param <E> type to store
 */
public class IUSingleLinkedList<E> implements IndexedUnsortedList<E> {
	private LinearNode<E> front, rear;
	private int count;
	private int modCount;
	
	/** Creates an empty list */
	public IUSingleLinkedList() {
		front = rear = null;
		count = 0;
		modCount = 0;
	}

	@Override
	public void addToFront(E element) {
		// TODO 
		
	}

	@Override
	public void addToRear(E element) {
		// TODO 
		
	}

	@Override
	public void add(E element) {
		// TODO 
		// add to front ig?
		
	}

	@Override
	public void addAfter(E element, E target) {
		// TODO 
		// indexOf for the target element
		// feed into master add
		
	}

	@Override
	public void add(int index, E element) {
		// TODO @watermelon2718
		//the master class

		// get element E(0) at the desired index
		// assign my node's E(1) next to the next of E
		//THEN assign E(0)'s next to my node E(1)
		//count ++
		// modCount ++ (maybe?)

		
	}

	@Override
	public E removeFirst() {
		// TODO 
		return null;
	}

	@Override
	public E removeLast() {
		// TODO 
		return null;
	}

	@Override
	public E remove(E element) {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		LinearNode<E> current = front, previous = null;
		while (current != null && !current.getElement().equals(element)) {
			previous = current;
			current = current.getNext();
		}
		// Matching element not found
		if (current == null) {
			throw new NoSuchElementException();
		}
		return removeElement(previous, current);		
	}

	@Override
	public E remove(int index) {
		// TODO 
		return null;
	}

	@Override
	public void set(int index, E element) {
		// TODO 
		
	}

	@Override
	public E get(int index) {
		// TODO 
		return null;
	}

	@Override
	public int indexOf(E element) {
		// TODO 
		return 0;
	}

	@Override
	public E first() {
		// TODO 
		return null;
	}

	@Override
	public E last() {
		// TODO 
		return null;
	}

	@Override
	public boolean contains(E target) {
		// TODO 
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO 
		return false;
	}

	@Override
	public int size() {
		// TODO 
		return 0;
	}

	@Override
	public String toString() {
		// TODO
		return "";
	}

	private E removeElement(LinearNode<E> previous, LinearNode<E> current) {
		// Grab element
		E result = current.getElement();
		// If not the first element in the list
		if (previous != null) {
			previous.setNext(current.getNext());
		} else { // If the first element in the list
			front = current.getNext();
		}
		// If the last element in the list
		if (current.getNext() == null) {
			rear = previous;
		}
		count--;
		modCount++;

		return result;
	}

	@Override
	public Iterator<E> iterator() {
		return new SLLIterator();
	}

	/** Iterator for IUSingleLinkedList */
	private class SLLIterator implements Iterator<E> {
		private LinearNode<E> previous;
		private LinearNode<E> current;
		private LinearNode<E> next;
		private int iterModCount;
		
		/** Creates a new iterator for the list */
		public SLLIterator() {
			previous = null;
			current = null;
			next = front;
			iterModCount = modCount;
		}

		@Override
		public boolean hasNext() {
			// TODO 
			return false;
		}

		@Override
		public E next() {
			// TODO 
			return null;
		}
		
		@Override
		public void remove() {
			// TODO
		}
	}

	// IGNORE THE FOLLOWING CODE
	// DON'T DELETE ME, HOWEVER!!!
	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}
}
