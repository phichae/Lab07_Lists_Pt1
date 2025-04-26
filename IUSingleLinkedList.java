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
		add(0, element);
	}

	@Override
	public void addToRear(E element) {
		// TODO 
		add(element);
	}

	@Override
	public void add(E element) {
		// TODO 
		LinearNode<E> newNode = new LinearNode<E>(element);
		if (front == null) {
			front = newNode;
		}

		LinearNode<E> current = front;
		while(current != null) {
			current = current.getNext();
		}
		current = newNode;
		count++;
		modCount++;
	}

	@Override
	public void addAfter(E element, E target) {
		if (!this.contains(target)) { throw new NoSuchElementException(); }
		// TODO 
		//indexOf for the target element
		// feed into master add
		add(indexOf(target)+1, element);
		
	}

	@Override
	public void add(int index, E element) {
		if (index < 0 || index >= size()) {throw new IndexOutOfBoundsException(); }
		// TODO
		LinearNode<E> newNode = new LinearNode<E>(element); 
		if (index == 0) {
			newNode.setNext(front);
			front = newNode;
		}
		//get element E(0) at desired index
		// front.getNext() till we get to the element?
		LinearNode<E> current = front;

		for(int i = 0; i < index-1 && current != null; i++) {
			current = current.getNext();
		}

		newNode.setNext(current.getNext());
		current.setNext(newNode);
		count++;
		modCount++;
	}

	@Override
	public E removeFirst() {
		return remove(front.getElement());
	}

	@Override
	public E removeLast() {
		return remove(rear.getElement());
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
		if (isEmpty()) { throw new NoSuchElementException(); }
		if(index < 0 || index >= size()) { throw new IndexOutOfBoundsException(); }

		LinearNode<E> current = front, previous = null;

		for(int i = 0; i < index; i++) {
			previous = current;
			current = current.getNext();
		}
		return removeElement(previous, current);
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
		//TODO
		return 0;
	}

	@Override
	public E first() {
		if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return front.getElement();
	}

	@Override
	public E last() {
		if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return rear.getElement();
	}

	@Override
	public boolean contains(E target) {
		LinearNode<E> current = front;
		while (current != null) {
    		if (current.getElement().equals(target)) {
        		return true;
    		}
    		current = current.getNext();
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO MD
		return size() == 0;
	}

	@Override
	public int size() {
		// TODO MD
		return count;
	}

	@Override
	public String toString() {
		// TODO MD
		LinearNode<E> current = front;
		String result = "[";
		for (int i = 0; i < count; i++) {
			if (i > 0) {
        			result += ", ";
            		}
			result += current.getElement();
			current = current.getNext();
		}
		result += "]";
		return result;
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