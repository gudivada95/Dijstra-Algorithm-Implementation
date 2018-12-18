
// vertex class
public class Vertex {

	// vertex id
    int vNumber;

    // key value associated with a vertex
    double keyVal;

    // through which edge did we get to this vertex?
    // need this for enumerating shortest path
    int pred;


    // constructor
    public Vertex(int vNumber, double aKeyVal, int pred) {
       this.vNumber = vNumber;
       this.keyVal = aKeyVal;
       this.pred = pred;

    }

    // set vertex number
    public void setVNumber(int vNumber) {
        this.vNumber = vNumber;
    }

    // get vertex number
    public int getVNumber(){
        return vNumber;
    }


    // set key value associated with the vertex
    public void setKeyVal(double aKeyVal){
        this.keyVal = aKeyVal;
    }


    // get key value associated with vertex
    public double getKeyVal(){
        return keyVal;
    }


    // set edge that provided minimum key value for the vertex
    public void setPred(int aVertex){
        this.pred = aVertex;
    }

    // get priorVertexNumber associated with vertex
    public int getPred(){
        return pred;
    }


    // print vertex
    public String toString(){
        return "vertex: " + vNumber + " key value: " + keyVal + " pred: " + pred;
    }

}