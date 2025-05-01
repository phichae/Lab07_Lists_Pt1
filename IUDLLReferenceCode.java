import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IUDLLReferenceCode {
    //Iterator - TODO: merge w/ ListIterator
    private class IteratorTemp implements Iterator<E> {
        private BidirectionalNode<E> previous;
		private BidirectionalNode<E> current;
		private BidirectionalNode<E> next;

        private int iterModCount;
        private int currentIndex; // this is the actual index of the next element to be served
        // private int virtualIndex; // this represents what the zero-index value would be...
        private boolean canRemove;

        // private IteratorTemp() {
        //     //TODO: incorporate startingIndex logic
        //     previous = null;
        //     current = front;
        //     next = front.getNext();

        //     iterModCount = modCount;
        //     currentIndex = 0;
        //     canRemove = false;
        // }

        // @Override
        // public boolean hasNext() {
        //     if (iterModCount != modCount) { throw new ConcurrentModificationException(); } // fail-fast
        //     return currentIndex < count;
        // }

        // @Override
        // public E next() {
        //     if (!hasNext()) { throw new NoSuchElementException(); }
        //     E item = get(currentIndex);
        //     currentIndex++;
        //     canRemove = true;
        //     //array - TODO: @watermelon2718 delete
        //     // E item = list[current];
        //     // current = increment(current);
        //     // virtualIndex++;
        //     // canRemove = true;
        //     return item;
        // }

        // public boolean hasPrevious() {
        //     //TODO
        //     return false;
        // }

        // public E previous() {
        //     //TODO
        //     return current.getElement();
        // }

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

        // //TODO: do we need this?
        // public void set() {


        // }

        // //TODO: do we need this?
        // public void add(){

        // }
    }

}
