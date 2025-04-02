import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * "Good" implementation of IndexedUnsortedList interface 
 * using Java API LinkedList class.  
 * 
 * @param <E>  - type of elements held in this collection
 * @author CPSC 221
 */
public class GoodList<E> implements IndexedUnsortedList<E> {
	private LinkedList<E> list;
	
	public GoodList() {
		list = new LinkedList<E>();
	}

	@Override
	public void addToFront(E element) {
		list.addFirst(element);
	}

	@Override
	public void addToRear(E element) {
		list.addLast(element);
	}

	@Override
	public void addAfter(E element, E target) {
		int targetIdx = list.indexOf(target);
		if (targetIdx < 0) {
			throw new NoSuchElementException();
		} else {
			list.add(targetIdx+1, element);
		}
	}
	
	@Override
	public void add(int index, E element) {
		list.add(index, element);
	}

	@Override
	public void add(E element) {
		list.add(element);
	}

	@Override
	public E removeFirst() {
		return list.removeFirst();
	}

	@Override
	public E removeLast() {
		return list.removeLast();
	}

	@Override
	public E remove(E element) {
		int idx = list.indexOf(element);
		if (idx < 0) {
			throw new NoSuchElementException();
		}
		E t = list.get(idx);
		list.remove(t);
		return t;
	}

	@Override
	public E remove(int index) {
		return list.remove(index);
	}

	@Override
	public E first() {
		return list.getFirst();
	}

	@Override
	public E last() {
		return list.getLast();
	}

	@Override
	public boolean contains(E target) {
		return list.contains(target);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public void set(int index, E element) {
		list.set(index, element);
	}

	@Override
	public E get(int index) {
		return list.get(index);
	}

	@Override
	public int indexOf(E element) {
		return list.indexOf(element);
	}

	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return list.toString();
	}
}
