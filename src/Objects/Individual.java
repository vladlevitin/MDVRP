package Objects;

import java.util.ArrayList;

public class Individual {

    Chromosome chromosome = new Chromosome();

    public double fitness=0;
    public int max_cordinate=0;
    public int min_cordinate=0;

    public ArrayList<Customer> customers=new ArrayList<Customer>();
    public ArrayList<Depot> depots=new ArrayList<Depot>();
    public ArrayList<ArrayList<VechicleRoute>> routes=new ArrayList<ArrayList<VechicleRoute>>();

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

    public double getFitness(){
        double fit_val=0;
        for(int i=0;i<routes.size();i++){
            for(int j=0; j<routes.get(i).size();j++){
                fit_val+=routes.get(i).get(j).distance;
            }
        }
        return fit_val;
    }

}
