/**
 *  @author Leland Conn
 *  
 *  In this class an algorithm is implemented to compute a shortest path
 *  from the UWO store cell to the customer cell.
 *  
 * */

import java.io.FileNotFoundException;
import java.io.IOException;

public class FindShortestPath {

	public static void main(String[] args) {
		try {
			//An empty priority queue is created. 
			 DLPriorityQueue<MapCell> queue = new DLPriorityQueue<MapCell>();
			 
			 //The initial cell is found from the Map object representing the map.
			 Map map = new Map(args[0]);
			 MapCell start = map.getUWOstore();
			 
			 //The initial cell is added to the priority queue
			 start.markEnqueued();
			 queue.enqueue(start, 0);
			 boolean endFound = false;
			 int lengthOfPath = 0;
			 
			 //While the priority queue is not empty, and the customer cell has not
			 //been found perform the following tasks.
			 while (!queue.isEmpty() && !endFound) {
				 //Remove smallest(cell with the smallest priority) from the priority queue
				 MapCell smallest = queue.getSmallest();
				 smallest.markDequeued();
				 if (smallest.isCustomer()) {
					 endFound = true;
					 lengthOfPath = smallest.getDistanceToStart() + 1;
				 }
				 //If smallest has a cellular tower in it or if any of the neighboring
				 //cells has a tower, then smallest cannot be part of the solution.
				 else if (smallest.isTower() || queue.interference(smallest)) {
					 endFound = false;
				 }
				 else {
					 //Consider each one of the neighboring cells of smallest that is not null,
					 //is not of type no-flying, and has not been marked as dequeued.
					 for (int i = 0; i < 6; i++) {
						 if (smallest.getNeighbour(i) != null && !smallest.getNeighbour(i).isNoFlying() && !smallest.getNeighbour(i).isMarkedDequeued()) {
							 int dist = 1 + smallest.getDistanceToStart();
							 MapCell cell = smallest.getNeighbour(i);
							 //If the distance between the neighbor cell and the initial cell is greater than
							 //the distance from smallest to the initial cell + 1, then do the following.
							 if (cell.getDistanceToStart() > dist) {
								 //Set the distance of the neighbor cell to the 
								 //initial cell to the distance distance from smallest to the initial cell + 1.
								 cell.setDistanceToStart(dist);
								 //Set smallest as the predecessor of the neighbor cell in the path to the UWO store.
								 //This is necessary to allow the algorithm to reconstruct the path from the initial cell to the destination once
								 //the destination has been reached.
								 cell.setPredecessor(smallest);
							 }
							 
							 //Variable path is the distance from the neighbor cell to the initial cell + Euclidean distance 
							 //from the neighbor cell to the destination cell.
							 double path = cell.getDistanceToStart() + cell.euclideanDistToDest(map);
							 // If the neighbor cell is marked as enqueued and variable path is less than the priority of the neighbor cell
							 //then do the following.
							 if (cell.isMarkedEnqueued() && path < queue.getPriority(cell)) {
								 //Update the priority of the neighbor cell to variable path.
								 queue.changePriority(cell, path);
							 }
							 // If the neighbor cell is not marked as enqueued, then do the following tasks.
							 if (!cell.isMarkedEnqueued()) {
								 //Add the neighbor cell to the priority queue with priority equal to its distance to the 
								 //initial cell plus the Euclidean distance to the destination. Then mark the neighbor cell as enqueued. 
								 queue.enqueue(cell, path);
								 cell.markEnqueued();
							 }
						 }
					 }
				 }
			 }
			 //If the customer cell is reached print a statement describing how many cells it took to get there.
			 if (endFound) {
				 System.out.println("The destination was reached using a path of length " + lengthOfPath + " cells.");
			 }
			 //If the customer cell is not reached print a statement that explains it was not reached.
			 else {
				 System.out.println("The destination was not reached.");
			 }
		 }
		 catch (IllegalArgumentException a) {
			 System.out.println("The argument provided is not valid.");
		 }
		 catch (InvalidElementException el) {
			 System.out.println("An element provided is deemed invalid.");
		 }
		 catch (InvalidNeighbourIndexException n) {
			 System.out.println("The index of a neighbour cell is invalid.");
		 }
		 catch (InvalidMapException e) {
			 System.out.println("The map provided is invalid.");
		 }
		 catch (FileNotFoundException f) {
			 System.out.println("File not found.");
		 }
		 catch (IOException i) {
			 System.out.println("An input/output caused an error.");
		 }
		 catch (EmptyPriorityQueueException q) {
			 System.out.println(q);
		 }
		 catch(InvalidDataItemException d) {
			 System.out.println(d);
		 }
	}
}
