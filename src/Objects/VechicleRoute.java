package Objects;


import java.util.ArrayList;

//route assigned to each vechicle

public class VechicleRoute {
    public ArrayList<Customer> customers=new ArrayList<Customer>();
    public int vechicle_load=0;
    public double distance =0;
    public Depot depot;





    public VechicleRoute(Depot depot){
        this.depot=depot;
    }

    public String toString(){
        return depot.toString();
    }


}
