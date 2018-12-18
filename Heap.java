

// Heap class for implementing a priority queue
public class Heap {

	// an array to maintain a heap
	private Vertex [] heap;

	// heap capacity
	private int heapCapacity;

	// heap current size
	private int heapSize = 0;

	// constructor
	public Heap (int capacity){

		// max capacity of heap
		heapCapacity = capacity;

		// we allocate one more element so that heap elements
		// occupy indices 1 through heap size
		heap = new Vertex[heapCapacity+1];
	}

	// what is heap capacity?
	public int getHeapCapacity(){
		return heapCapacity;
	}

	// what is current heap size?
	public int getHeapSize(){
		return heapSize;
	}

	// is heap full?
	public boolean isFull(){
		return (heapSize == heapCapacity);
	}


	// is heap empty?
	public boolean isEmpty(){
		return (heapSize == 0);
	}


	// print heap elements
	public void print(){
		for (int index = 1; index <= heapSize; index++)
			System.out.println(heap[index]);
	}


	// add vertex v to heap as the last element
	public void addElementAtEnd(Vertex vertex){
		// System.out.println("\nAdding to heap " + vertex);
		// if heap did not reach capacity
		if (heapSize < heapCapacity){
			heap[++heapSize] = vertex;
			heapifyUp(heapSize);
		}
	}


	// given a vertex in the heap, returns its index
	// if the vertex is not in the heap, returns 0
	public int indexOfVertex (Vertex aVertex){

		// vertex not yet found
		boolean found = false;

		// looking for the index of this vertex
		int vertexToFind = aVertex.getVNumber();

		// System.out.println("heap size: " + heapSize);

		int atIndex = 0;
		for (int index = 1; index <= heapSize; index++){
			if ( heap[index].getVNumber() == vertexToFind ){
				found = true;
				atIndex = index;
				break;
			}
		}
		return atIndex;
	}


	// delete heap element at a given index
	public void deleteElementAtIndex (int index){

		// replace the deleted element with the very last element
		heap[index] = heap[heapSize];

		// we have one element less in the heap now
		heapSize--;

		// root element is being deleted
		if (index == 1){
			heapifyDown(1);
		}
		else {
			int parentIndex = index/2;

			// the replacement node key value is greater than its parent
			// the heap above the replacement node if fine, adjust the heap
			// below the replacement node 
			if ( heap[index].getKeyVal() > heap[parentIndex].getKeyVal() ) {
				heapifyDown(index);
			}
			else {
				// adjust the heap above the replacement node
				heapifyUp(index);
			}
		}
	}


	// algorithm on page 61 of textbook 
	// heapifyUp process for moving heap element at  
	// index i toward the root
	public void heapifyUp(int childIndex){

		// index of parent
		int parentIndex;

		// temp variable for swapping heap elements
		Vertex tempVertex;

		// if heap has more than one element
		if (childIndex > 1) {
			// find parent's index
			parentIndex = childIndex/2;
			
			// swap heap entries heap[i] and heap[j]
			double d1 = heap[childIndex].getKeyVal();
			double d2 = heap[parentIndex].getKeyVal();

			if (d1 < d2){
				tempVertex = heap[childIndex];
				heap[childIndex] = heap[parentIndex];
				heap[parentIndex] = tempVertex;
				heapifyUp(parentIndex);
			}
		}
	}



	// algorithm on page 63 of textbook 
	public void heapifyDown(int i) {

		if (heapSize == 1)
			return;

		// heap size
		int n = heapSize;

		// initialize to avoid compiler error
		int j = 1;

		if (2*i > n)
			return;
		else if (2*i < n) {
			int left = 2*i;
			int right = 2*i+1;
			if ( heap[left].getKeyVal() < heap[right].getKeyVal() )
				j = 2*i;
			else
				j = 2*i+1;
		}
		else if (2*i == n){
			j = 2*i;
		}

		// System.out.println("\nValue of j is: " + j);

		if (heap[j].getKeyVal() < heap[i].getKeyVal()){
			Vertex tempVertex = heap[i];
			heap[i] = heap[j];
			heap[j] = tempVertex;

			heapifyDown(j);
		}

	}

	// remove the element that has the minimum key value from the heap
	// and return the element
	public Vertex deleteMin(){

		// if empty heap, there is nothing to return
		if (isEmpty()) {
			System.out.println("Heap is empty.");
            return null;
		}
		else{
			// minimum value is at heap array index = 1
			Vertex minKeyVertex = heap[1];
			heap[1] = heap[heapSize];
			heapSize--;
			heapifyDown(1);

			// System.out.println("Minimum key value is: " + minKeyVertex.getKeyValue());
			return minKeyVertex;
		}
	}

}