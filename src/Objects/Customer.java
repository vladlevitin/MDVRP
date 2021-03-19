package Objects;

public class Customer {
    public int ID;
    public int x_cordinate;
    public int y_cordinate;
    public int duration;
    public int demand;

    public Customer(int ID,int x_cordinate, int y_cordinate, int duration, int demand){
        this.ID=ID;
        this.x_cordinate=x_cordinate;
        this.y_cordinate=y_cordinate;
        this.duration=duration;
        this.demand=demand;
    }

    public String toString(){
        return "Customer ID "+Integer.toString(this.ID)+" - (x:"+Integer.toString(this.x_cordinate)+",y:"+Integer.toString(this.y_cordinate)+")";
    }
}

