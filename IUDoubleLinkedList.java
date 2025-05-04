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
        BidirectionalNode<E> newNode = new BidirectionalNode<E>(element);

        if(front == null) {
            front = rear = newNode;
        } else {

            if (rear == null) {
                rear = front;
            }
            
            newNode.setPrevious(rear);
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
            if (front != null) {
                front.setPrevious(newNode);
            }
            front = newNode;
            if (front.getNext() == null) {  
                rear = newNode;
            }
        } else {
            BidirectionalNode<E> current = front;
            for (int i = 0; i < index-1 && current != null; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            newNode.setPrevious(current);
            if (current.getNext() != null) {
                current.getNext().setPrevious(newNode);
            }
            current.setNext(newNode);
            if (newNode.getNext() == null) {
                rear = newNode;
            }
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
		BidirectionalNode<E> current = front, previous = null;
		while (current != null) {
            if (current.getElement().equals(element)) {
                BidirectionalNode<E> next = current.getNext();
                return removeElement(previous, current, next);
            }
			previous = current;
			current = current.getNext();
            // next = next.getNext();
		}
		throw new NoSuchElementException();
	}

	@Override
	public E remove(int index) {
		// if (isEmpty()) { throw new NoSuchElementException(); }
		if(index < 0 || index >= size()) { throw new IndexOutOfBoundsException(); }
				
		BidirectionalNode<E> current = front, previous = null, next = current.getNext();

		for(int i = 0; i < index; i++) {
			previous = current;
			current = next;
            next = next.getNext();
		}
		return removeElement(previous, current, next);
	}

	@Override
	public void set(int index, E element) {
		// TODO 
		if (index < 0 || index >= count) { throw new IndexOutOfBoundsException(); }
		BidirectionalNode<E> current = front;
		int i = 0;
		while(i < index) {
			current = current.getNext();
			i++;
		}
		current.setElement(element);
        modCount++;
	}

	@Override
	public E get(int index) {
		if (index < 0 || index >= count) { throw new IndexOutOfBoundsException(); }
		BidirectionalNode<E> current = front;
		int i = 0;
		while(i < index) {
			current = current.getNext();
			i++;
		}
		return current.getElement();
	}

    public int indexOf(E element) {
		//TODO
		BidirectionalNode<E> current = front;
		for(int i = 0; i < count; i++) {
			if (current.getElement().equals(element)) {
				return i;
    		}
			current = current.getNext();
		}
		return -1;
	}

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
        //this works
        // return get(count - 1);

        //for some reason, rear is null in the single element list

        return rear.getElement();
    }

    @Override
    public boolean contains(E target) {
        if (isEmpty()) {
            return false;
        }
        BidirectionalNode<E> current = front;
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
        return size() == 0;
    }

    @Override
    public int size() {
        return count;
    }


	@Override
	public String toString() {
		BidirectionalNode<E> current = front;
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

    @Override
    public Iterator<E> iterator() {
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
    private E removeElement(BidirectionalNode<E> previous, BidirectionalNode<E> current, BidirectionalNode<E> next) {
		// Grab element
		E result = current.getElement();
		// If not the first element in the list
		if (previous != null) {
			previous.setNext(next);
		} else { // If the first element in the list
			front = next;
		}

        if (next != null) {
            next.setPrevious(previous);
        } else { // If the last element in the list
            rear = previous;
        }
		count--;
		modCount++;

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

            if (current == null) {
                current = next;
                next = next.getNext();                
            } else {
                previous = current;
                current = next;
                next = next.getNext();
            }

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

			if (current == front) {
                front = next;
                if (front != null) front.setPrevious(null);
            } else if (previous != null) {
                previous.setNext(next);
            }

            if (next != null) {
                next.setPrevious(previous);
            } else {
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
    
    private enum ListIteratorState{ PREVIOUS, NEXT, NEITHER }
    
    private class DLLListIterator implements ListIterator<E> {
        private BidirectionalNode<E> previous;
        private BidirectionalNode<E> current;
        private BidirectionalNode<E> next;

        private int nextIndex;
        private ListIteratorState state;
        private int listIterModCount;
        
        public DLLListIterator() {
            this(0);
        }

        
        public DLLListIterator(int index) {
            //TODO: possibly put this logic in a helper method
            if (index < 0 || index > size()) { throw new IndexOutOfBoundsException(); }

            next = front;
            for(int i = 0; i < index; i++) {
                next = next.getNext();
            }

            previous = (next != null) ? next.getPrevious() : rear;
            nextIndex = index;
            // if (next != null) {
            //     current = next.getPrevious();
            // } else {
            //     current = null;
            // }

            // if (current != null) {
            //     previous = current.getPrevious();
            // } else {
            //     previous = null;
            // }
            state = ListIteratorState.NEITHER;
            listIterModCount = modCount;
        }


        //TODO
        @Override
        public boolean hasNext() {
            if (listIterModCount != modCount) { throw new ConcurrentModificationException(); } // fail-fast
            return next != null;
        }

        @Override
        public E next() {
            if (!hasNext()) { throw new NoSuchElementException(); }
            
            E item = next.getElement();
            // get(currentIndex + 1); // currentIndex + 1?
            //was cursor.getNextIndex()

            //Right Shift
            current = next;
            previous = current;
            next = next.getNext(); // is it ok if it's null?
            nextIndex++;

            state = ListIteratorState.NEXT;
            return item;
            // indexOf(current.getElement()) -- a way to get the index of the element in current

        }

        @Override
        public boolean hasPrevious() {
            if (listIterModCount != modCount) { throw new ConcurrentModificationException(); } // fail-fast
            return previous != null;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) { throw new NoSuchElementException(); }
            E item = previous.getElement();
            // E item = get(currentIndex - 1);
            
            //Left Shift
            next = current;
            current = previous;
            previous = previous.getPrevious();
            nextIndex--;

            state = ListIteratorState.PREVIOUS;
            return item;
        }

        @Override
        public int nextIndex() {
            if (listIterModCount != modCount) { throw new ConcurrentModificationException(); } // fail-fast
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            if (listIterModCount != modCount) { throw new ConcurrentModificationException(); } // fail-fast
            return nextIndex-1;
        }

        //TODO
        @Override
        public void remove() {
            if (listIterModCount != modCount) { throw new ConcurrentModificationException(); } // fail-fast
            switch(state){
                case NEXT:
                    // removeElement(cursor.getPreviousIndex());
                    IUDoubleLinkedList.this.remove(nextIndex-1);
                    break;
                case PREVIOUS:
                    IUDoubleLinkedList.this.remove(nextIndex);
                    break;
                default:
                    throw new IllegalStateException();
            }
            state = ListIteratorState.NEITHER;
            listIterModCount++;
        }

        @Override
        public void set(E element) { 
            if (listIterModCount != modCount) { throw new ConcurrentModificationException(); } // fail-fast
            switch(state){
                case NEXT:
                    current.setElement(element);
                    break;
                case PREVIOUS:
                    current.setElement(element);
                    break;
                default:
                    throw new IllegalStateException();
            }
            state = ListIteratorState.NEITHER; // do we need this?
            modCount++;
            listIterModCount++;

        }

        @Override
        public void add(E element) {
            if (listIterModCount != modCount) { throw new ConcurrentModificationException(); } // fail-fast
            // BidirectionalNode<E> node = new BidirectionalNode<E>(element);

            //Adds to the LT of the cursor = before next
            if (current == null) {
                addToFront(element);
            } else {
                addAfter(element, previous.getElement());
                //was current.getElement
            }
            
            listIterModCount++;
            state = ListIteratorState.NEITHER;
        }


    }

    
}