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
		front.setElement(element);
	}

	@Override
	public void addToRear(E element) {
		// TODO 
		add(element);
	}

	@Override
	public void add(E element) {
		// TODO 
		// add to rear

		//rear.setnext = new rear
		// rear = new rear
		add((count-1), element);

		//TODO: how do I grab the node for the new element? maybe happens in the add method
		// rear.setNext(element);
		rear.setElement(element);
		
	}

	@Override
	public void addAfter(E element, E target) {
		if (!this.contains(target)) {throw new NoSuchElementException(); }
		// TODO 
		//indexOf for the target element
		// feed into master add
		add(indexOf(target), element);
		
	}

	@Override
	public void add(int index, E element) {
		if (index < 0 || index > (count - 1)) {throw new IndexOutOfBoundsException(); }
		// TODO 
		// the master class

		//new LinearNode w/ the element we're adding
		LinearNode<E> node = new LinearNode<E>(element);
		//get element E(0) at desired index
		// front.getNext() till we get to the element?
		LinearNode<E> current = front, previous = null;

		for(int i = 0; i < index; i++) {
			previous = current;
			current = current.getNext();
		}
	
		node.setNext(current);
		previous.setNext(node);

		// LinearNode<E> nodeToReplace = front;
		// LinearNode<E> previousNode = null;

		// while (nodeToReplace.getElement() != get(index)) {
		// 	previousNode = nodeToReplace;
		// 	nodeToReplace = nodeToReplace.getNext();
		// }
		// assign my node (E1)'s next to the next of E
		// node.setNext(nodeToReplace);

		// // THEN assign E(0)'s next to my node (E1)
		// previousNode.setNext(node);

		// //TODO
		// get((indexOf((E)nodeToReplace.getElement())) - 1);

		// count++
		count++;
		// modCount ++ (maybe??)
		modCount++;
		
	}

	@Override
	public E removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return remove(front.getElement());
	}

	@Override
	public E removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
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
