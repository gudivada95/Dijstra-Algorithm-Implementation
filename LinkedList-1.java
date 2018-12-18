
// for managing adjacency lists

public final class LinkedList {

	// references the node at the front of queue
	private Node firstNode;

	// references node at the back of queue
	private Node lastNode;
  	
  	// zero-parameter constructor
	public LinkedList() {
		firstNode = null;
		lastNode = null;
	}
	
	// add an element to the beginning of the list
	public void addFirst(Edge newEdge) {
		// create a new node, store the value in data field,
		// and store null in the next field
		Node newNode = new Node(newEdge, null);
		
		// if the list is empty, then this is the first 
		// element in the list
		if (isEmpty()){
			// first node is the new node
			firstNode = newNode;
			lastNode = newNode;
		}
		else {
			// otherwise, the newly created node becomes the first node
			// set the next field value of new node to the current first node, and
			// set the new node as the first node
			newNode.next = firstNode;
			firstNode = newNode;
			
			// last node remains the same
		}
	} // public void addFirst


	// add an element to the end of the list
	public void addLast(Edge newEdge) {

		// create a new node, store the value in data field,
		// and store null in the next field
		Node newNode = new Node(newEdge, null);
		
		// if the list is empty, then this is the first 
		// element in the list
		if (isEmpty()){
			// first node is the new node
			firstNode = newNode;
			lastNode = newNode;
		}
		else {
			// otherwise, the newly created node becomes the last node
			// set the next field value of the last node to the new node
			// set the new node as the last node
			lastNode.next = newNode;
			lastNode = newNode;
			
			// first node remains the same
		}
	} // public void addLast


	// returns list size
	public int size(){
		// list is empty
		if (firstNode == null){
			return 0;
		}
		// list has only one element
		else if (firstNode.getNextNode() == null){
			return 1;
		}
		// list has more than one element
		else {
			// for traversing through the list
			int index = 1;
			// references an arbitrary element in the list
			Node tempNode = firstNode;
			while (tempNode.getNextNode() != null){
				tempNode = tempNode.getNextNode();
				index++;
			}
			return index;
		}
	} // public int size()


	// returns a specified number edge
	public Edge getEdge(int edgeNum){
		if (edgeNum > size()){
			System.out.println("Edge number " + edgeNum + " does not exist");
			return null;
		}
		else{
			Node tempNode = firstNode;
			int index = 1;
			while (index < edgeNum){
				tempNode = tempNode.getNextNode();
				index++;
			}
			return tempNode.getData();
		}
	}


	// print elements in the list
	public void printElements(){
		Node tempNode = firstNode;
		while (tempNode != null){
			Edge anEdge = tempNode.getData();
			System.out.println(anEdge.getSVertex() + " is connected to " + anEdge.getDVertex() + ", edge weight = " + anEdge.getEWeight());
			tempNode = tempNode.next;
		}
	}
		

	// for list to be empty, both the first node and the
	// last node must be null
	public boolean isEmpty() {
		return (firstNode == null) && (lastNode == null);
	}
	

	// clearing a list is essentially setting the first
	// node and last node values to null
	public void clear() {
		firstNode = null;	
		lastNode = null;
	}


	// internal class, private to LinkedList class
	private class Node {

		// data field of Node
		private Edge data;

		// reference to the node that follows (this node)
		private Node next; 

		// two-parameter constructor
		// @param data is the value to be stored in the new node
		// @param next is the reference to an existing node, which 
		// will follow the node that is being created
		public Node(Edge data, Node next) {
			this.data = data;
			this.next = next;	
		}


		// like a normal get method, returns the data field 
		// of the node under consideration
		public Edge getData() {
			return data;
		}


		// change the data field value of the node under consideration
		public void setData(Edge newData) {
			data = newData;
		}


		// given a node, returns the reference to the node
		// which follows the given node
		public Node getNextNode() {
			return next;
		}

	} // private class Node

} // public final class LinkedList