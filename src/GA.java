import Objects.Customer;
import Objects.Individual;
import Objects.Depot;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GA {

    public ArrayList<Individual> generateInitialPopulation(Individual intialIndividual, int populationSize){

        RouteScheduler scheduler=new RouteScheduler();
        ArrayList<Individual> population = new ArrayList<Individual>();
        population.add(intialIndividual);

        for (int i=0; i<populationSize;i++){
            Individual individual =new Individual();
            individual.depots=intialIndividual.depots;
            individual.customers=intialIndividual.customers;
            individual.min_cordinate=intialIndividual.min_cordinate;
            individual.max_cordinate=intialIndividual.max_cordinate;

            for(int j=0; j<individual.depots.size();j++){
                Collections.shuffle(individual.depots.get(j).customers); //shuffle the ordering of customers
            }

            scheduler.RouteScheduler(individual); //create routes for the new individual
            individual.fitness=individual.getFitness();
            individual.setChromosome();
            population.add(individual);
        }
        return population;
    }

    public double weightedfitness(Individual individual, double alpha, double beta){
        return alpha*individual.getVechicles()+beta*individual.getVechicles();
    }
    /*
    public void pareto_ranking(int population_size){
        int current_rank=1;
        int N=population_size;
        int m=N;

        while(N!=0){
            for (int i=0; i<m;i++){

            }
        }

    }
    */


    public Individual selectFitestIndividual(ArrayList<Individual> individuals){
        Individual fittestIndividual=individuals.get(0);
        for(Individual individual:individuals){
            if(individual.getFitness()<fittestIndividual.getFitness()){
                fittestIndividual=individual;
            }


        }
        return fittestIndividual;
    }

    public Individual tournamentSelection(ArrayList<Individual> tournamentSet, double r_bound){
        double r=Math.random();
        //Forecast element = Collections.max(forecasts, Comparator.comparingInt(Forecast::getTemperature));
        if(r<r_bound){
            //return fittest individual
            Individual individual= Collections.min(tournamentSet, Comparator.comparingDouble(Individual::getFitness));
            return individual;
        }
        else{
            //return random individual

            int random_int = generateRandomInt(0,(tournamentSet.size()-1));
            return tournamentSet.get(random_int);
        }

    }

    public ArrayList<Individual> sortPopulationFitness(ArrayList<Individual> population){
        ArrayList<Individual> sortedPopulation =(ArrayList<Individual>) population.clone();
        sortedPopulation.sort(Comparator.comparing(individual -> individual.getFitness()));
        return sortedPopulation;
    }

    public int generateRandomInt(int min, int max){

        int index = (int)Math.floor(Math.random()*(max-min+1)+min);

        return index;

    }
    public Customer selectCustomer(int ID, ArrayList<Customer> customers){
        for (Customer customer:customers){
            if(customer.ID==ID){
                return customer;
            }
        }
        return null;
    }
    //arrange the depot based on the chromosome
    public void adjustDepotsToChromosome(Individual individual){

        ArrayList<Depot> depots=new ArrayList<Depot>();


        for(int i=0; i<individual.chromosome.size();i++){

            Depot depot=new Depot(individual.depots.get(i).ID,individual.depots.get(i).x_cordinate,individual.depots.get(i).y_cordinate,
                    individual.depots.get(i).max_duration,individual.depots.get(i).max_load_vechicle,individual.depots.get(i).n_vechicles);


           for(int j=0; j<individual.chromosome.get(i).size();j++){
               Integer customerID = individual.chromosome.get(i).get(j);
               Customer customer=selectCustomer(customerID, individual.customers);
               depot.customers.add(customer);

           }
           depots.add(depot);

       }
        //set the new depot configuration
        individual.depots=depots;


    }

    public ArrayList<Individual> BestCostRouteCrossOver(Individual parent1, Individual parent2){

        ArrayList<Individual> children=new ArrayList<Individual>();

        //randomly select depot


        int depot_index = generateRandomInt(0, parent1.depots.size()-1);

        Depot depot = parent1.depots.get(depot_index);

        ArrayList<Integer> gene1Parent = parent1.chromosome.get(depot_index);
        ArrayList<Integer> gene2Parent = parent2.chromosome.get(depot_index);

        //randomly generate indexes
        int index_1 = generateRandomInt(0,gene1Parent.size());
        int index_2 = generateRandomInt(0,gene2Parent.size());

        //recombine the the chromosomes for child 1
        ArrayList<Integer> gene1Child=new ArrayList<Integer>(gene1Parent.subList(Math.min(index_1, index_2), Math.max(index_1, index_2)));

        for(Integer i:gene1Parent){
            if(!gene1Child.contains(i)){
               gene1Child.add(i);
            }
        }

        ArrayList<ArrayList<Integer>> chromosome1Child=new ArrayList<ArrayList<Integer>>(parent1.chromosome);

        chromosome1Child.set(depot_index,gene1Child);




        Individual child1=new Individual();

        child1.chromosome=chromosome1Child;

        ArrayList<Depot> child1Depots=new ArrayList<Depot>(parent1.depots);
        child1.depots=child1Depots;

        ArrayList<Customer> child1Customers=new ArrayList<Customer>(parent1.customers);
        child1.customers=child1Customers;

        child1.max_cordinate=parent1.max_cordinate;
        child1.min_cordinate=parent1.min_cordinate;



        adjustDepotsToChromosome(child1);

        RouteScheduler sheduler=new RouteScheduler();

        sheduler.RouteScheduler(child1);

        child1.fitness=child1.getFitness();



        children.add(child1);

        //recombining procedure for child 2

        ArrayList<Integer> gene2Child=new ArrayList<Integer>(gene2Parent.subList(Math.min(index_1, index_2), Math.max(index_1, index_2)));

        for(Integer i:gene2Parent){
            if(!gene2Child.contains(i)){
                gene2Child.add(i);
            }
        }

        ArrayList<ArrayList<Integer>> chromosome2Child =new ArrayList<ArrayList<Integer>>(parent2.chromosome);

        chromosome2Child.set(depot_index,gene2Child);



        Individual child2=new Individual();

        child2.chromosome=chromosome2Child;

        child2.max_cordinate=parent2.max_cordinate;
        child2.min_cordinate=parent2.min_cordinate;

        ArrayList<Depot> child2Depots=new ArrayList<Depot>(parent2.depots);
        child2.depots=child2Depots;

        ArrayList<Customer> child2Customers=new ArrayList<Customer>(parent2.customers);
        child2.customers=child2Customers;


        adjustDepotsToChromosome(child2);


        sheduler.RouteScheduler(child2);

        child2.fitness=child2.getFitness();

        children.add(child2);

        return children;
    }


















}
