import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IUDoubleLinkedList<E> implements IndexedUnsortedList<E> { 

    private int count, modCount;
    private BidirectionalNode<E> front, rear;

    public IUDoubleLinkedList() {
        front = rear = null;
        count = modCount = 0;
    }

    @Override
    public void addToFront(E element) {
        add(0, element);
    }

    @Override
    public void addToRear(E element) {
        add(element);
    }

    @Override
    public void add(E element) {
        BidirectionalNode<E> newNode = new BidirectionalNode<>(element);
        if(front == null) {
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
        if (!contains(target)) { throw new NoSuchElementException(); }
        add(indexOf(target)+1, element);
    }

    @Override
    public void add(int index, E element) {
        BidirectionalNode<E> newNode = new BidirectionalNode<>(element);
        if (index < 0 || index > size()) { throw new IndexOutOfBoundsException(); }
        if (index == 0) {
            newNode.setNext(front);
            front = newNode;
            if (isEmpty()) {
                rear = newNode;
            }
        } else {
            BidirectionalNode<E> current = front;
            for (int i = 0; i < index-1 && current != null; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            newNode.setPrevious(current.getPrevious());
            current.setNext(newNode);
        }
        count++;
        modCount++;
    }

    @Override
    public E removeFirst() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFirst'");
    }

    @Override
    public E removeLast() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeLast'");
    }

    @Override
    public E remove(E element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public E remove(int index) {
        // // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'remove'");
        E element = (E)(new Object());
        return element;
    }

    @Override
    public void set(int index, E element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'set'");
    }

    @Override
    public E get(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public int indexOf(E element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'indexOf'");
    }

    @Override
    public E first() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'first'");
    }

    @Override
    public E last() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'last'");
    }

    @Override
    public boolean contains(E target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

    public String toString() {
        //TODO Ellyn-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toString'");
    }

    @Override
    public Iterator iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    @Override
    public ListIterator listIterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    @Override
    public ListIterator listIterator(int startingIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    //TODO: edit @watermelon2718
    private E removeElement(BidirectionalNode<E> previous, BidirectionalNode<E> current) {
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


//TODO: Pasted from WK 13 demo @watermelon2718

    private class ListIterator implements Iterator<E> {
        private BidirectionalNode<E> previous;
		private BidirectionalNode<E> current;
		private BidirectionalNode<E> next;

        private int iterModCount;
        private int currentIndex; // this is the actual index of the next element to be served
        // private int virtualIndex; // this represents what the zero-index value would be...
        private boolean canRemove;

        private ListIterator() {
            previous = null;
            current = front;
            next = front.getNext();

            iterModCount = modCount;
            currentIndex = 0;
            canRemove = false;
        }

        @Override
        public boolean hasNext() {
            if (iterModCount != modCount) { throw new ConcurrentModificationException(); } // fail-fast
            return currentIndex < count;
        }

        @Override
        public E next() {
            if (!hasNext()) { throw new NoSuchElementException(); }
            E item = get(currentIndex);
            currentIndex++;
            canRemove = true;
            //array - TODO: @watermelon2718 delete
            // E item = list[current];
            // current = increment(current);
            // virtualIndex++;
            // canRemove = true;
            return item;
        }

        public boolean hasPrevious() {
            //TODO
        }

        public E previous() {
            //TODO
        }

        public void remove() {// TODO: needs to branch based on which direction we just moved the iterator

            if (iterModCount != modCount) { throw new ConcurrentModificationException(); } // fail-fast
            if (!canRemove) { throw new IllegalStateException(); }

            currentIndex--; // ???
            
            if(currentIndex == 0) {
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
            canRemove = false;
        }    

        //TODO: do we need this?
        public void set() {


        }

        //TODO: do we need this?
        public void add(){

        }
    }
}
