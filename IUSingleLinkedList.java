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
			front = rear = newNode;
		} else {
			rear.setNext(newNode);
			rear = newNode;
		}
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
		//TODO: @watermelon2718 should it be index > size?
		if (index < 0 || index > size()) {throw new IndexOutOfBoundsException(); }
		// TODO
		LinearNode<E> newNode = new LinearNode<E>(element); 
		if (index == 0) {
			newNode.setNext(front);
			front = newNode;
			if (size() == 0) {
				rear = newNode;
			}
		} else {
		//get element E(0) at desired index
		// front.getNext() till we get to the element?
			LinearNode<E> current = front;

			for(int i = 0; i < index-1 && current != null; i++) {
				current = current.getNext();
			}
			// current = newNode;
			// newNode.setNext(current.getNext());
			// current.setNext(newNode);
			newNode.setNext(current.getNext());
			current.setNext(newNode);


		}
		
		count++;
		modCount++;
	}

	@Override
	public E removeFirst() {
		if (isEmpty()) { throw new NoSuchElementException(); }
		return remove(0);

		// return remove(front.getElement());
	}

	@Override
	public E removeLast() {
		if (isEmpty()) { throw new NoSuchElementException(); }
		return remove(count - 1);
		// return remove(rear.getElement());
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
		// if (isEmpty()) { throw new NoSuchElementException(); }
		if(index < 0 || index >= size()) { throw new IndexOutOfBoundsException(); }
		//TODO: @watermelon2718 - should it be index > size?
		
		



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
		if (index < 0 || index >= count) { throw new IndexOutOfBoundsException(); }
		LinearNode<E> current = front;
		int i = 0;
		while(i < index) {
			current = current.getNext();
			i++;
		}
		current.setElement(element);
	}

	@Override
	public E get(int index) {
		if (index < 0 || index >= count) { throw new IndexOutOfBoundsException(); }
		LinearNode<E> current = front;
		int i = 0;
		while(i < index) {
			current = current.getNext();
			i++;
		}
		return current.getElement();
	}

	@Override
	public int indexOf(E element) {
		//TODO
		LinearNode<E> current = front;
		for(int i = 0; i < count; i++) {
			if (current.getElement().equals(element)) {
				return i;
    		}
			current = current.getNext();
		}
		return -1;
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
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return next != null;
		}

		@Override
		public E next() {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			previous = current;
			current = next;
			next = next.getNext();
			return current.getElement();
		}
		
		@Override
		public void remove() {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if (current == null) {
				throw new IllegalStateException();
			}

			if(current == front) {
				front = next;
			} else {
				previous.setNext(next);
			}
			
			if (next == null) {
				rear = previous;
			}

			current = null;
			count--;
			iterModCount++;
			modCount++;
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