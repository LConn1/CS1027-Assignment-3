/**
 *  @author Leland Conn
 *  
 *  This class represents the nodes of the doubly linked
 *  list used to implement the priority queue.
 *  
 * */

public class PriorityNode<T> {

	/**
	 * dataItem is a reference to the data item stored in the node.
	 * next is a reference to the next node in the linked list.
	 * previous is a reference to the previous node in the linked list.
	 * priority is the priority of the data item stored in this node.
	 */
	private T dataItem;
	private PriorityNode<T> next = null;
	private PriorityNode<T> previous = null;
	private double priority;
	
	/**
	 * Creates a node storing the given data and priority.
	 * @param data is a reference to the data item stored in this node.
	 * @param prio is the priority of the data item stored in this node.
	 */
	public PriorityNode(T data, double prio) {
		this.dataItem = data;
		this.priority = prio;
	}
	
	/**
	 * Creates an empty node, with null data and priority zero.
	 */
	public PriorityNode() {
		this.dataItem = null;
		this.priority = 0;
	}
	
	/**
	 * Gets the priority of the object.
	 * @return the priority.
	 */
	public double getPriority() {
		return this.priority;
	}
	
	/**
	 * Gets the the data item of the object
	 * @return the data item.
	 */
	public T getDataItem() {
		return this.dataItem;
	}
	
	/**
	 * Get the PriorityNode following this one in the doubly
	 * linked list.
	 * @return next object in list.
	 */
	public PriorityNode<T> getNext() {
		return next;
	}
	
	/**
	 * Get the PriorityNode before this one in the doubly
	 * linked list.
	 * @return previous object in list.
	 */
	public PriorityNode<T> getPrevious() {
		return previous;
	}
	
	/**
	 * Set the data item of the PriorityNode.
	 * @param newDataItem is the new data item replacing the old one stored
	 * in the PriorityNode.
	 */
	public void setDataItem(T newDataItem) {
		this.dataItem = newDataItem;
	}
	
	/**
	 * Set the next PriorityNodeObject in the doubly linked list
	 * @param newNode is the node that will be set as the next node.
	 */
	public void setNext(PriorityNode<T> newNode) {
		this.next = newNode;
	}
	
	/**
	 * Set the previous PriorityNodeObject in the doubly linked list
	 * @param newNode is the node that will be set as the previous node.
	 */
	public void setPrevious(PriorityNode<T> newNode) {
		this.previous = newNode;
	}
	
	/**
	 * Set the priority of the node.
	 * @param newPrio is the new priority of the node.
	 */
	public void setPriority(double newPrio) {
		this.priority = newPrio;
	}
}
