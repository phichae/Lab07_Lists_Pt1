/**
 * BidirectionalNode represents a node in a linked list.
 *
 * @author lsevigny
 * @version 4.0
 */
public class BidirectionalNode<E> {
	private BidirectionalNode<E> next;
	private BidirectionalNode<E> previous;
	private E element;

	/**
  	 * Creates an empty node.
  	 */
	public BidirectionalNode() {
		previous = null;
		next = null;
		element = null;
	}

	/**
  	 * Creates a node storing the specified element.
 	 *
  	 * @param elem
  	 *            the element to be stored within the new node
  	 */
	public BidirectionalNode(E elem) {
		previous = null;
		next = null;
		element = elem;
	}

	/**
 	 * Returns the node that precedes this one.
  	 *
  	 * @return the node that precedes the current one
  	 */
	  public BidirectionalNode<E> getPrevious() {
		return previous;
	}

	/**
 	 * Sets the node that precedes this one.
 	 *
 	 * @param node
 	 *            the node to be set to precede the current one
 	 */
	  public void setPrevious(BidirectionalNode<E> node) {
		previous = node;
	}

	/**
 	 * Returns the node that follows this one.
  	 *
  	 * @return the node that follows the current one
  	 */
	public BidirectionalNode<E> getNext() {
		return next;
	}

	/**
 	 * Sets the node that follows this one.
 	 *
 	 * @param node
 	 *            the node to be set to follow the current one
 	 */
	public void setNext(BidirectionalNode<E> node) {
		next = node;
	}

	/**
 	 * Returns the element stored in this node.
 	 *
 	 * @return the element stored in this node
 	 */
	public E getElement() {
		return element;
	}

	/**
 	 * Sets the element stored in this node.
  	 *
  	 * @param elem
  	 *            the element to be stored in this node
  	 */
	public void setElement(E elem) {
		element = elem;
	}

	@Override
	public String toString() {
		return "Element: " + element.toString() + " Has previous: " + (previous != null) + " Has next: " + (next != null);
	}
}

