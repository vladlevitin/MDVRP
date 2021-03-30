package Objects;

import java.util.ArrayList;

public class Individual {

    Chromosome chromosome = new Chromosome();

    public double fitness=0;
    public int max_cordinate=0;
    public int min_cordinate=0;

    public ArrayList<Customer> customers=new ArrayList<Customer>();
    public ArrayList<Depot> depots=new ArrayList<Depot>();

    public Individual(){

    }

    public void addCustomer(Customer customer){
        this.customers.add(customer);
    }

    public void addDepot(Depot depot){
        this.depots.add(depot);
    }

    public void setChromosome(Chromosome chromosome){
        this.chromosome=chromosome;
    }

}
