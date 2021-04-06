package Objects;

import java.util.ArrayList;

public class Individual {

    public ArrayList<ArrayList<Integer>> chromosome=new ArrayList<ArrayList<Integer>>();

    public double fitness=0;
    public int max_cordinate=0;
    public int min_cordinate=0;

    public boolean vechiles_exceeded=false;

    public ArrayList<Customer> customers=new ArrayList<Customer>();
    public ArrayList<Depot> depots=new ArrayList<Depot>();


    public ArrayList<ArrayList<VechicleRoute>> routes=new ArrayList<ArrayList<VechicleRoute>>();

    public Individual(){

    }



    public void setChromosome(){
        for(int i=0; i<depots.size();i++){
            ArrayList<Integer> gene=new ArrayList<Integer>();
            for(int j=0; j< depots.get(i).customers.size();j++){
                gene.add(depots.get(i).customers.get(j).ID);
            }
            this.chromosome.add(gene);
        }
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

    public int getVechicles(){
        int vechicles=0;
        for (int i=0; i<routes.size();i++){
            vechicles+=routes.get(i).size();
        }
        return vechicles;
    }

    public double getWeightedFitness(double alpha, double beta){
        return alpha*this.getVechicles()+beta*this.getVechicles();
    }

}
