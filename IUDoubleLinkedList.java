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
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
        // // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'remove'");

        //from @watermelon2718...ignore this haha
        // E element = (E)(new Object());
        // return element;
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
        return new DLLIterator();
    }

	@Override
	public ListIterator<E> listIterator() {
		return new DLLListIterator();
	}

	@Override
	public ListIterator<E> listIterator(int startingIndex) {
        return new DLLListIterator(startingIndex);
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

//TODO: @watermelon2718 Pasted from Wk 12 Demo
    private E removeElement(int index) {
        //WHAT IF element DNE?
        if (index == NOT_FOUND) {throw new NoSuchElementException(); }
        E result = this.list[index];

//shift everything
        int ltIndex = index, rtIndex = increment(index);
        while (rtIndex < rear) { //b/c rear is pointing to a null element
            list[ltIndex] = list[rtIndex];
            ltIndex = increment(ltIndex);
            rtIndex = increment(rtIndex);
        }

        rear = decrement(rear);
        //gotta null out the last element
        this.list[rear] = null;
        count--;
        modCount ++;

        return result;
    }

    //TODO: pasted from SLLIterator
    	/** Iterator for IUSingleLinkedList */
	private class DLLIterator implements Iterator<E> {
		private BidirectionalNode<E> previous;
		private BidirectionalNode<E> current;
		private BidirectionalNode<E> next;
		private int iterModCount;
		
		/** Creates a new iterator for the list */
		public DLLIterator() {
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



// ------------------------------ DLL LIST ITERATOR ----------------------------------------------------------------------------------------//

    // --------------------------- LIST ITERATOR -------------------------------------------//

    private class ListCursor {
        private int virtualNextIndex;

        //TODO: what is this
        public ListCursor(int nextVirtualIndex) {
            if (nextVirtualIndex < 0 || nextVirtualIndex > count ) {throw new IndexOutOfBoundsException() ; }
            virtualNextIndex = nextVirtualIndex;
        }

        public int getNextIndex() {
            return virtualNextIndex;
        }

        public int getPreviousIndex() {
            return virtualNextIndex - 1; // TODO: is this still the case? if my cursor sets on the current node
        }

        // public int getNextIndex() {
        //     return getActualIndexFromVirtual(getVirtualNextIndex());
        // }

        // public int getPreviousIndex() {
        //     return getActualIndexFromVirtual(getVirtualPreviousIndex()); //if you like eating your own dog food
        // }

        public void rightShift() {
            if (getNextIndex() == count) return;
            virtualNextIndex++;
        }

        public void leftShift() {
            if (getPreviousIndex() == -1) return;
            virtualNextIndex--;
        }

        // private int getActualIndexFromVirtual(int virtualIndex) {
        //     return (front + virtualIndex) % list.length; // cuz what if we have 100 elements, front = 98, virtualIndex = 4 ... INDEX OUT OF BOUNDS EXCEPTIONS!!!
        // }

    }
    
    private enum ListIteratorState{ PREVIOUS, NEXT, NEITHER }
    
    private class DLLListIterator implements ListIterator<E> {
        private BidirectionalNode<E> previous;
        private BidirectionalNode<E> current;
        private BidirectionalNode<E> next;

        private int currentIndex; // this is the actual index of the next element to be served
        // private int virtualIndex; // this represents what the zero-index value would be...
        // private boolean canRemove; //don't need bc state
        private ListIteratorState state;
        private int listIterModCount;

        private ListCursor cursor; // do I need this?

        public DLLListIterator() {
            this(0);
        }

        
        public DLLListIterator(int index) {
            //current = node at index...start at front and scroll to the right node
            //TODO: should it be current or front?

            //TODO: possibly put this logic in a helper method
            current = front;
            for(int i = 0; i < index; i ++) {
                current = current.getNext();
            }

            currentIndex = index;
            previous = current.getPrevious();
            next = current.getNext();
            state = ListIteratorState.NEITHER;
            listIterModCount = modCount;

            cursor = new ListCursor(index); //TODO: do I need this? 
        }


        //TODO
        @Override
        public boolean hasNext() {
            if (listIterModCount != modCount) { throw new ConcurrentModificationException(); } // fail-fast
            return currentIndex < count - 1;
            // return cursor.getVirtualNextIndex() < count;

        }

        @Override
        public E next() {
            if (!hasNext()) {throw new NoSuchElementException(); }
            
            E item = next.getElement();
            // get(currentIndex + 1); // currentIndex + 1?
            //was cursor.getNextIndex()

            //Right Shift
            cursor.rightShift();
            previous = current;
            current = next;
            next = next.getNext();

            state = ListIteratorState.NEXT;
            return item;
            // indexOf(current.getElement()) -- a way to get the index of the element in current

        }

        @Override
        public boolean hasPrevious() {
            if (listIterModCount != modCount) { throw new ConcurrentModificationException(); } // fail-fast
            return (currentIndex - 1) > -1;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {throw new NoSuchElementException(); }
            E item = previous.getElement();
            // E item = get(currentIndex - 1);
            
            //Left Shift
            cursor.leftShift();
            previous = previous.getPrevious();
            next = current;
            current = previous;

            state = ListIteratorState.PREVIOUS;
            return item;
        }

        @Override
        public int nextIndex() {
            if (listIterModCount != modCount) { throw new ConcurrentModificationException(); } // fail-fast
            return cursor.getNextIndex();
        }

        @Override
        public int previousIndex() {
            if (listIterModCount != modCount) { throw new ConcurrentModificationException(); } // fail-fast
            return cursor.getNextIndex();
        }

        //TODO
        @Override
        public void remove() {
            if (listIterModCount != modCount) { throw new ConcurrentModificationException(); } // fail-fast
            switch(state){
                case NEXT:
                    removeElement(cursor.getPreviousIndex());
                    cursor.leftShift(); 
                    break;
                case PREVIOUS:
                    removeElement(cursor.getNextIndex());
                    break;
                default:
                    throw new IllegalStateException();
            }
            state = ListIteratorState.NEITHER;
            listIterModCount++;
        }

        @Override
        public void set(E element) { 
            switch(state){
                case NEXT:
                    next.setElement(element);
                    break;
                case PREVIOUS:
                    previous.setElement(element);
                    break;
                default:
                    throw new IllegalStateException();
            }
            state = ListIteratorState.NEITHER; // do we need this?
            listIterModCount++;

        }

        @Override
        public void add(E element) {
            BidirectionalNode<E> node = new BidirectionalNode<E>(element);

            //Branch logic: 
            switch(state) {
                case NEXT:
                //insert before next = to RT of cursor
                    node.setPrevious(current.getPrevious());
                    current.setPrevious(node);
                    break;
                case PREVIOUS:
                    //insert after previous = to LT of cursor
                    node.setNext(current.getNext());
                    current.setNext(node);
                    break;
                default:
                    //TODO: what is the default case?
                    throw new IllegalStateException();
            }
            listIterModCount++;
            state = ListIteratorState.NEITHER;
        }

        private void rightShift() {
            
        }


    }
    
}


