import Objects.Individual;
import Objects.VechicleRoute;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception{
        //Individual individual=new Individual();
        //FileManager fileManager=new FileManager();


        //fileManager.ImportData(filename,individual);
        String fileName = "Testing_Data/Data_Files/p01";

        // Initialize the customers and depots from file
        FileManager manager = new FileManager();
        Individual individual=new Individual();

        manager.ImportData(fileName,individual);

        manager.nearestDepot(individual);

        System.out.println(individual.customers);
        System.out.println(individual.depots);

        int j=3; //depot number
        System.out.println(individual.depots.get(j));
        System.out.println(individual.depots.get(j).customers);
        System.out.println(individual.depots.get(j).customers.size());



        RouteScheduler scheduler=new RouteScheduler();
        ArrayList<VechicleRoute> routes=scheduler.PhaseOneSchedule(individual.depots.get(j));

        for(int i=0; i<routes.size();i++) {
            System.out.println(routes.get(i).customers.size());
            System.out.println(routes.get(i).customers);
            System.out.println(routes.get(i).vechicle_load);
            System.out.println(routes.get(i).duration);
        }






    }
}
