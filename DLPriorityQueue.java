/**
 *  @author Leland Conn
 *
 *   This class implements all the methods in the PriorityQueueADT.java interface
 *   and it stores the data items of the priority queue in a doubly linked list.
 *  
 */

public class DLPriorityQueue<T> implements PriorityQueueADT<T>  {
	 
	 /**
	  * front is a reference to the first node of the doubly linked list.
	  * rear is a reference to the last node of the doubly linked list.
	  * count is the number of data items in the priority queue.
	  */
	 private PriorityNode<T> front;
	 private PriorityNode<T> rear;
	 private int count;
	 
	 /**
	  * Creates an empty priority queue.
	  */
	 public DLPriorityQueue() {
		 this.count = 0;
		 this.front = null;
		 this.rear = null;
	 }
	 
	 /**
	  * Adds to the priority queue the given dataItem with its associated priority.
	  * The new data item is added to the rear of the doubly linked list. 
	  */
	 public void enqueue(T dataItem, double priority) {
		 PriorityNode<T> newNode = new PriorityNode<T>(dataItem, priority);
		 if (count > 0) {
			 rear.setNext(newNode);
			 newNode.setPrevious(rear);
			 newNode.setNext(null);
			 rear = newNode;
		 }
		 else {
			 rear = newNode;
			 front = newNode;
		 }
		 count++;
	 }
	 
	 /**
	  * Removes and returns the data item at the front of the priority queue.
	  */
	 public T dequeue() throws EmptyPriorityQueueException {
		 if (count == 0) {
			 throw new EmptyPriorityQueueException("Queue is full.");
		 }
		 T result = front.getDataItem();
		 front = front.getNext();
		 front.setPrevious(null);
		 count--;
		 if (count == 0) {
			 rear = null;
		 }
		 return result;
	 }
	 
	 /**
	  * Returns the priority of the specified dataItem.
	  * @param dataItem is the data item of the desired node.
	  * @return the priority of the node with the specified data item
	  * @throws InvalidDataItemException if the given data item is not in the priority queue
	  */
	 public double getPriority(T dataItem) throws InvalidDataItemException {
		 PriorityNode<T> current = front;
		 while ((current.getDataItem() != dataItem) && (current.getNext() != null)) {
			 current = current.getNext();
		 }
		 
		 if (current.getDataItem() == null) {
			throw new InvalidDataItemException("Data item not in the priority queue.");
		 }
		 else {
			 return current.getPriority();
		 }
	 }
	 
	 /**
	  * Changes the priority of the given element to the new value. 
	  * An InvalidDataItemException is thrown if the given dataItem
	  * is not in the priority queue.
	  */
	 public void changePriority(T dataItem, double newPriority) throws InvalidDataItemException {
		 PriorityNode<T> current = front;
		 try {
			 while ((!current.getDataItem().equals(dataItem)) && (!current.equals(null))) {
			 current = current.getNext();
			 }
		 }
		 catch (NullPointerException n) {
			 throw new InvalidDataItemException("Data item not in the priority queue.");
		 }
		 
		 if (current.getDataItem().equals(dataItem)) {
			 current.setPriority(newPriority);
		 }
		 else {
			 throw new InvalidDataItemException("Data item not in the priority queue.");		 }
	 }
	 
	 /**
	  * Removes and returns the data item in the priority queue with smallest priority.
	  * An EmptyPriorityQueueException is thrown if the priority queue is empty.  
	  * @return the data item with the smallest priority
	  */
	 public T getSmallest() throws EmptyPriorityQueueException {
		 PriorityNode<T> current = front;
		 PriorityNode<T> smallest = front;
		 
		 if (count == 0) {
			 throw new EmptyPriorityQueueException("Empty priority queue caused error.");
		 }
		 
		 if (count == 1) {
			 T result = front.getDataItem();
			 rear = null;
			 front = null;
			 count--;
			 return result;
		 }
		 while (current != null) {
			 if (current.getPriority() < smallest.getPriority()) {
				smallest = current;
			 }
			 current = current.getNext();
		 }
		 T result = smallest.getDataItem();
		 if (smallest.getPrevious() != null) {
			 (smallest.getPrevious()).setNext(smallest.getNext());
	     }
	 	 else {
	 		 front = smallest.getNext();
	 	 }
		 if (smallest.getNext() != null) {
			 (smallest.getNext()).setPrevious(smallest.getPrevious());
		 }
		 else {
			 rear = smallest.getPrevious();
		 }
		 count--;
		 return result;
	 }
	 
	 /**
	  * Returns true if the priority queue is empty and it returns false otherwise.
	  * @return status of the priority queue being empty.
	  */
	 public boolean isEmpty() {
		 if (count == 0) {
			 return true;
		 }
		 return false;
	 }
	 
	 /**
	  * Returns the number of data items in the priority queue.
	  * @return number of data items in priority queue.
	  */
	 public int numItems() {
		 return count;
	 }
	 
	 /**
	  * Returns a String representation of the priority queue.
	  * This method just invokes the toString method from 
	  * each data item in the priority queue and concatenates the strings.
	  */
	 public String toString() {
		 String statement = "";
		 PriorityNode<T> current = front;
		 while (current != null) {
			 String s = current.getDataItem().toString();
			 statement = statement.concat(s);
			 current = current.getNext();
		 }
		 return statement;
	 }
	 
	 /**
	  * This method returns true if any of the adjacent cells to the
	  * current one are a tower cell, and it will return false otherwise.
	  * @param cell is the cell where the drone currently is.
	  * @return true if an adjacent cell is a tower; false otherwise.
	  */
	 public boolean interference(MapCell cell) {
		 for (int i = 0; i < 6; i++) {
			 if (cell.getNeighbour(i) != null) {
				 if (cell.getNeighbour(i).isTower()) {
					 return true;
				 }
		     }
	      }
		  return false;
	 }
}
