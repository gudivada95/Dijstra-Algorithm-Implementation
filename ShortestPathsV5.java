/*
Author: Akhil Gudivada
This is the shortest path program using Dijstra's algorithm
To run: Place all files (6 of them) in one directory and use the compile all command (javac *)
Then run the main program (ShortestPathV5.java)
Enter information as given in Assignment 4 handout.
number of verticies
number of edges
For each edge: starting vertex, ending vertex, weight


Note the 6 files that should be there are: Edge.java, Heap.java, Vertex.java, VertexList.java, LinkedList.java, and ShortestPathV5.java
**/

import java.io.*; 

// for ArrayList
import java.util.*; 

// for stdin
import java.util.Scanner; 


public class ShortestPathsV5 {

	public static void main(String[] args){

        // adjacency list for representing directed graph
        LinkedList[] adjList;

        // create a scanner class instance for reading from stdin
        Scanner sc = new Scanner(System.in);
        
        // read number of vertices
        int numVertices = sc.nextInt();
        // System.out.println("Number of vertices: " + numVertices);


        // read number of edges
        int numEdges = sc.nextInt();
        // System.out.println("Number of edges: " + numEdges);

        // declare and initialize variables
        Vertex sVertex = null;
        Vertex dVertex = null;
        double eWeight = 0F;

        // temp variable to hold vertices
        Vertex aVertex;

        // temp variable to hold lowest/minimum cost vertex
        Vertex mVertex;

        // beginning vertex of an edge
        Vertex bVertex;

        // ending vertex of an edge
        Vertex eVertex;

        // temp variable to hold an edge
        Edge anEdge = null;

        // create a heap for priority queue
        Heap heap = new Heap(numVertices);

        // create adjacency list data stricture
        // number of adjacency lists = number of vertices
        // in the following, need numVertices + 1 as we count vertices from 1, not zero
        adjList = new LinkedList[numVertices + 1];

        // initialize adjacency list of each vertex
        for (int index = 1; index <= numVertices; index++) {
            adjList[index] = new LinkedList();
        }


        // source and destination vertices
        int v1, v2;

        // build adjacency list
        for (int index = 1; index <= numEdges; index++){
            v1 = sc.nextInt();
            v2 = sc.nextInt();

            // if source (that is vertex 1), initialize its key value to 0
            if (v1 == 1)
                sVertex = new Vertex (v1, 0, 0);
            else // otherwise, key value = Double.MAX_VALUE;
                sVertex = new Vertex (v1, Double.MAX_VALUE, 0);
            
            dVertex = new Vertex (v2, Double.MAX_VALUE, 0);

            eWeight = sc.nextDouble();

            anEdge = new Edge(sVertex, dVertex, eWeight);
            adjList[sVertex.getVNumber()].addLast(anEdge);
        }


        // add the source vertex to the heap/priority queue
        // set keyValue to 0 and predecessor to 0
        // add source vertex to the heap
        heap.addElementAtEnd(new Vertex(1, 0, 0));

        // initially, the exploredVertices set is null
        VertexList exploredVertices = new VertexList();

        // initially, all vertices are not yet explored
        // unExploredVertices will contain all vertices in the graph
        // set keyVal to  Double.MAX_VALUE and pred = 0
        // only for the start vertex, set keyVal = 0
        VertexList unExploredVertices = new VertexList();

        // exception for the start vertex
        unExploredVertices.add(new Vertex(1, 0, 0));

        // add remaining vertices to unExploredVertices
        for (int index = 2; index <= numVertices; index++){
            unExploredVertices.add(new Vertex(index, Double.MAX_VALUE, 0));
        }

        // list of direct edges from the selected min keyVal vertex
        List<Edge> edgeSet = new ArrayList<Edge>(numEdges);

        // adjacency list corresponding to the selected min keyVal vertex
        LinkedList aList;

        // for debugging purpose
        int cycleNum = 0;

        while (unExploredVertices.size() >= 1){

            // delete lowest keyVal vertex from the heap/priority queue
            mVertex = heap.deleteMin();

            // find edge set of the lowest keyVal vertex
            // these are the edges that emanate from the lowest keyVal vertex
            aList = adjList[mVertex.getVNumber()];

            for (int i = 1; i <= aList.size(); i++){
                anEdge = aList.getEdge(i);

                // to the edge set, add only those edges whose destination
                // vertices are not in the exploredVertices set
                if (!exploredVertices.exists(anEdge.getDVertex()))
                    edgeSet.add(anEdge);
            }

            // update key values of destination/tail end vertices in the edge set
            // process one edge at a time
            for (Edge e : edgeSet){
                // source/head vertex of the edge
                sVertex = e.getSVertex();

                // destination/tail vertex of the edge
                dVertex = e.getDVertex();
                
                // weight of the edge
                eWeight = e.getEWeight();

                // update key value and predecessor information
                // for relevant vertices in unExploredVertices set
                bVertex = unExploredVertices.info(sVertex.getVNumber());
                // System.out.println("\nbVertex: " + bVertex);
                eVertex = unExploredVertices.info(dVertex.getVNumber());
                // System.out.println("\neVertex: " + eVertex);

                if (eVertex.getKeyVal() > (bVertex.getKeyVal() + eWeight) ){

                    // update eVertex's keyVal and pred
                    unExploredVertices.update( eVertex.getVNumber(), bVertex.getKeyVal() + eWeight, bVertex.getVNumber() );

                    eVertex.setKeyVal(bVertex.getKeyVal() + eWeight);
                    eVertex.setPred(bVertex.getVNumber());

                    // add vertex to the heap. check if the vertex is already in the heap
                    // if so, delete the current instance and add the new instance
                    int j = heap.indexOfVertex(eVertex);
                    if (j != 0) // vertex already in the heap
                        heap.deleteElementAtIndex(j);

                    // add the updated vertex to the heap;
                    heap.addElementAtEnd(eVertex);
                }
            }

            // clear edge set for processing next unexplored vertex
            edgeSet.clear();

            // delete mVertex from the unExploredVertices set
            unExploredVertices.delete(mVertex);

            // add mVertex to the exploredVertices set
            exploredVertices.add(mVertex);
            
        }

        int[] pathArray = new int[numVertices+1];
        int pathArrayLength = 0;

        for (int index = 2; index <= exploredVertices.size(); index++){
            aVertex = exploredVertices.info(index);
            System.out.println("\nVertex: " + aVertex.getVNumber());
            System.out.println("Shortest distance: " + aVertex.getKeyVal());
            pathArrayLength = 0;
            pathArray[++pathArrayLength] = aVertex.getVNumber();
            while (aVertex.getPred() != 1){
                pathArray[++pathArrayLength] = aVertex.getPred();
                aVertex = exploredVertices.info(aVertex.getPred());
            }

            // add start vertex to the shortest path
            pathArray[++pathArrayLength] = 1;
            
            // vertex numbers in the shortest path are in reverse order
            // un-reverse them
            System.out.print("The shortest path is: ");
            for (int k = pathArrayLength; k > 0; k--)
                System.out.print(pathArray[k] + " ");
            System.out.println();
        }

	} // end main()
}
