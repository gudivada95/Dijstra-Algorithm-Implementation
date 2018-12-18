
public final class VertexList {

	// references the node at the front of queue
	private Node firstNode;

	// references node at the back of queue
	private Node lastNode;

	// size of vertex list
	private int length = 0;
  	
  	// zero-parameter constructor
	public VertexList() {
		firstNode = null;
		lastNode = null;
		length = 0;
	}

	public int size(){
		return length;
	}


	// given a vertex number, returns the corresponding vertex
	public Vertex info(int vNumber){
		Node tempNode = firstNode;
		while ( (tempNode != null) && (tempNode.data.getVNumber() != vNumber) ){
			tempNode = tempNode.next;
		}

		// node with vertex number = vNumber exists
		if (tempNode != null){
			return tempNode.data;
		}
		else{
			return null;
		}
	}


	// update keyVal and pred for a given vertex
	public void update(int vNumber, double keyVal, int pred){
		Node tempNode = firstNode;
		while ( (tempNode != null) && (tempNode.data.getVNumber() != vNumber) ){
			tempNode = tempNode.next;
		}
		if (tempNode != null){
			tempNode.data.keyVal = keyVal;
			tempNode.data.pred = pred;
		}
	}


	// does a given vertex exists in the list?
	// if exists returns true, else returns false
	public boolean exists (Vertex aVertex){
		boolean found = false;
		Node tempNode = firstNode;
		while (tempNode != null && !found){
			if (tempNode.data.getVNumber() == aVertex.getVNumber()){
				found = true;
			}
			else{
				tempNode = tempNode.next;
			}
		}
		if (found)
			return true;
		else
			return false;
	}


	// add an element to the end of the list
	public void add(Vertex newVertex) {

		// create a new node, store the value in data field,
		// and store null in the next field
		Node newNode = new Node(newVertex, null);
		
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

		// list size has increased by one
		length++;

	} // public void add



	// given a vertex, deletes it from the vertex list if exists
	public Vertex delete(Vertex aVertex){
		if (length == 0)
			return null;
		else if (length == 1){
			if (firstNode.data.getVNumber() == aVertex.getVNumber()){
				// node exists and delete it
				Vertex tempVertex = firstNode.data;
				firstNode = null;
				lastNode = null;
				length = 0;
				return tempVertex;
			}
			else
				return null;
		}
		else { // general case
			Node tempNode = firstNode;
			Node trailingNode = null;
			while ( (tempNode != null) && (tempNode.data.getVNumber() != aVertex.getVNumber()) ){
				trailingNode = tempNode;
				tempNode = tempNode.next;
			}

			if (tempNode == null)
				return null;
			else{
				if (trailingNode != null){
					trailingNode.next = tempNode.next;
					length--;
					return tempNode.data;
				}
				else{ // node to be deleted is first node
					Vertex tempVertex = firstNode.data;
					firstNode = firstNode.next;
					length--;
					return tempVertex;
				}
			}
		} // end general case
	}


	// print elements in the vertex list
	public void print(){
		Node tempNode = firstNode;
		while (tempNode != null){
			Vertex aVertex = tempNode.getData();
			System.out.println(aVertex.getVNumber() + " " + aVertex.getKeyVal() + " " + aVertex.getPred());
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
		private Vertex data;

		// reference to the node that follows (this node)
		private Node next; 

		// two-parameter constructor
		// @param data is the value to be stored in the new node
		// @param next is the reference to an existing node, which 
		// will follow the node that is being created
		public Node(Vertex data, Node next) {
			this.data = data;
			this.next = next;	
		}


		// like a normal get method, returns the data field 
		// of the node under consideration
		public Vertex getData() {
			return data;
		}


		// change the data field value of the node under consideration
		public void setData(Vertex aVertex) {
			data = aVertex;
		}


		// given a node, returns the reference to the node
		// which follows the given node
		public Node getNextNode() {
			return next;
		}

	} // private class Node

} // public final class VertexList