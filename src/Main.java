import Graphics.Display;
import Objects.Individual;
import Objects.VechicleRoute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws Exception{
        //Individual intialIndividual=new Individual();
        //FileManager fileManager=new FileManager();

        int population_size=1000;
        int generations=1000;
        int nElites=100;
        double r=0.8;

        //fileManager.ImportData(filename,intialIndividual);
        String fileName = "Testing_Data/Data_Files/p01";

        // Initialize the customers and depots from file
        FileManager manager = new FileManager();
        Individual intialIndividual=new Individual();

        manager.ImportData(fileName,intialIndividual);

        manager.nearestDepot(intialIndividual);

        intialIndividual.setChromosome();
        //System.out.println(intialIndividual.chromosome);
        /*
        System.out.println(intialIndividual.customers);
        System.out.println(intialIndividual.depots);

        int j=1; //depot number
        System.out.println("Depot Number: "+j);
        System.out.println(intialIndividual.depots.get(j));
        System.out.println(intialIndividual.depots.get(j).customers);
        System.out.println(intialIndividual.depots.get(j).customers.size());
        */


        RouteScheduler scheduler=new RouteScheduler();


        scheduler.RouteScheduler(intialIndividual);

        intialIndividual.fitness=intialIndividual.getFitness();

        GA ga=new GA();


        ArrayList<Individual> population= ga.generateInitialPopulation(intialIndividual, population_size);

        /*
        ArrayList<Individual>  filtered_population=new ArrayList<>();

        while(filtered_population.size()<population_size) {

            System.out.println(filtered_population.size());

            for (Individual individual : population) {
                System.out.println(individual.vechiles_exceeded);
                if (individual.vechiles_exceeded == false) {
                    filtered_population.add(individual);
                }
            }
            population= ga.generateInitialPopulation(intialIndividual, population_size);
        }

        population=filtered_population;
        */

        Double lowestFitVal=intialIndividual.fitness;

        for(int g=0; g<generations;g++){
            System.out.println("Generation: "+(g+1));
            //sort population by fitness val
           population=ga.sortPopulationFitness(population);

           ArrayList<Individual> parents = new ArrayList<>();

           for(int j=0; j< nElites;j++){
               parents.add(population.get(j));
           }
            //System.out.println("Parents Selected: "+(nElites));


           ArrayList<Individual> all_children = new ArrayList<>();

           for(int k=0; k<population_size; k++){

               ArrayList<Individual> tournamentSet1 = new ArrayList<Individual>();
               Individual parent1=parents.get(ga.generateRandomInt(0,parents.size()-1));
               Individual parent2=parents.get(ga.generateRandomInt(0,parents.size()-1));
               tournamentSet1.add(parent1);
               tournamentSet1.add(parent2);

               //System.out.println("Tournament set 1: "+(tournamentSet1.size()));

               Individual winning_parent_1=ga.tournamentSelection(tournamentSet1,r);

               //System.out.println("Winning parent 1 fitness: "+winning_parent_1.fitness);

               ArrayList<Individual> tournamentSet2 = new ArrayList<Individual>();

               Individual parent3=parents.get(ga.generateRandomInt(0,parents.size()-1));
               Individual parent4=parents.get(ga.generateRandomInt(0,parents.size()-1));

               tournamentSet2.add(parent3);
               tournamentSet2.add(parent4);

               //System.out.println("Tournament set 2: "+(tournamentSet2.size()));

               Individual winning_parent_2=ga.tournamentSelection(tournamentSet2,r);

               //System.out.println("Winning parent 2 fitness: "+winning_parent_2.fitness);


               ArrayList<Individual> children=ga.BestCostRouteCrossOver(winning_parent_1, winning_parent_2);

               all_children.addAll(children);


           }


           //add children to the population
           population.addAll(all_children);

           population=ga.sortPopulationFitness(population);

           ArrayList<Individual> new_population=new ArrayList<Individual>();

            for(int q=0; q<population_size ;q++){
                new_population.add(population.get(q));
            }

            population=new_population;

            //select new fittest individual
            if(lowestFitVal > ga.selectFitestIndividual(population).fitness){
                lowestFitVal=ga.selectFitestIndividual(population).fitness;
                //System.out.println("Lowest fitness: "+);
            }
            System.out.println("Lowest fitness value: "+lowestFitVal);


        }

        Individual fittestIndividual=ga.selectFitestIndividual(population);

        scheduler.RouteScheduler(fittestIndividual);

        manager.exportSolution(fittestIndividual,"Testing_Data/Solution_Files/solution.txt");


        Display display=new Display(fittestIndividual);

        System.out.println();
        



        //System.out.println(intialIndividual.routes);

        //ArrayList<Individual>  sorted_population=ga.sortPopulationFitness(population);

        //System.out.println(sorted_population.get(0).fitness);
        //System.out.println(sorted_population.get(1).fitness);









        //Individual final_individual= Collections.min(population, Comparator.comparingDouble(Individual::getFitness));




        /*
        ArrayList<VechicleRoute> routes=scheduler.PhaseOneSchedule(intialIndividual.depots.get(j));

        System.out.println("First Phase:");

        for(int i=0; i<routes.size();i++) {
            System.out.println("Route: "+i);
            System.out.println(routes.get(i).customers.size());
            System.out.println(routes.get(i).customers);
            System.out.println(routes.get(i).vechicle_load);
            System.out.println(routes.get(i).distance);
        }

        scheduler.PhaseTwoSchedule(routes);
        System.out.println("\n");
        System.out.println("Second Phase:");

        for(int i=0; i<routes.size();i++) {
            System.out.println("Route: "+i);
            System.out.println(routes.get(i).customers.size());
            System.out.println(routes.get(i).customers);
            System.out.println(routes.get(i).vechicle_load);
            System.out.println(routes.get(i).distance);
        }
        */







    }
}
