// edge class
public class Edge {

    // source vertex
    Vertex sVertex;

    // destination vertex
    Vertex dVertex;

    // edge weight
    double eWeight;

    // constructor
    public Edge(Vertex sVertex, Vertex dVertex, double eWeight) {
       this.sVertex = sVertex;
       this.dVertex = dVertex;
       this.eWeight = eWeight;
    }

    // change source vertex
    public void setSVertex(Vertex vertex){
        sVertex = vertex;
    }

    // what is edge's source vertex?
    public Vertex getSVertex(){
        return sVertex;
    }


    // change destination vertex
    public void setDVertex(Vertex vertex){
        dVertex = vertex;
    }

    // what is edge's destination vertex?
    public Vertex getDVertex(){
        return dVertex;
    }


    // set edge weight
    public void setEWeight(double aWeight){
        eWeight = aWeight;
    }


    // what is edge's weight?
    public double getEWeight(){
        return eWeight;
    }

    public String toString(){
        return "sVertex: " + sVertex + " dVertex: " + dVertex + " weight: " + eWeight;
    }

}