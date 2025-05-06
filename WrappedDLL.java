import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Wraps the java.util.LinkedList as a IndexedUnsortedList implementation.
 * Wrapped iterators do not throw ConcurrentModificationExceptions as expected,
 * but all list methods and other iterator functionality appear to be working
 * as necessary.
 * 
 * @author mvail
 *
 * @param <T> type to store
 */
public class WrappedDLL<T> implements IndexedUnsortedList<T> {
	private LinkedList<T> list;
	
	public WrappedDLL() {
		list = new LinkedList<T>();
	}

	@Override
	public void addToFront(T element) {
		list.addFirst(element);	
	}

	@Override
	public void addToRear(T element) {
		list.add(element);
	}

	@Override
	public void addAfter(T element, T target) {
		int targetIndex = list.indexOf(target);
		if (targetIndex < 0) {
			throw new NoSuchElementException("LinkedList");
		}
		list.add(list.indexOf(target) + 1, element);
	}

	@Override
	public T removeFirst() {
		try {
			return list.removeFirst();
		} catch (NoSuchElementException e) {
			throw new IllegalStateException("LinkedList");
		}
	}

	@Override
	public T removeLast() {
		try {
			return list.removeLast();
		} catch (NoSuchElementException e) {
			throw new IllegalStateException("LinkedList");
		}
	}

	@Override
	public T remove(T element) {
		int index = list.indexOf(element);
		if (index < 0) {
			throw new NoSuchElementException("LinkedList");
		}
		return list.remove(index);
	}

	@Override
	public T first() {
		try {
			return list.getFirst();
		} catch (NoSuchElementException e) {
			throw new IllegalStateException("LinkedList");
		}
	}

	@Override
	public T last() {
		try {
			return list.getLast();
		} catch (NoSuchElementException e) {
			throw new IllegalStateException("LinkedList");
		}
	}

	@Override
	public boolean contains(T target) {
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
	public Iterator<T> iterator() {
		return list.iterator();
	}

	@Override
	public void add(int index, T element) {
		list.add(index, element);
	}

	@Override
	public void set(int index, T element) {
		list.set(index, element);
	}

	@Override
	public void add(T element) {
		list.add(element);
	}

	@Override
	public T get(int index) {
		return list.get(index);
	}

	@Override
	public int indexOf(T element) {
		return list.indexOf(element);
	}

	@Override
	public T remove(int index) {
		return list.remove(index);
	}

	@Override
	public ListIterator<T> listIterator() {
		return list.listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		return list.listIterator(startingIndex);
	}
	
	public String toString() {
		return list.toString();
	}
}
