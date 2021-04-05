import Graphics.Display;
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
        /*
        System.out.println(individual.customers);
        System.out.println(individual.depots);

        int j=1; //depot number
        System.out.println("Depot Number: "+j);
        System.out.println(individual.depots.get(j));
        System.out.println(individual.depots.get(j).customers);
        System.out.println(individual.depots.get(j).customers.size());
        */


        RouteScheduler scheduler=new RouteScheduler();


        scheduler.RouteScheduler(individual);

        System.out.println(individual.routes);

        manager.exportSolution(individual,"Testing_Data/Solution_Files/p1.txt");


        /*
        ArrayList<VechicleRoute> routes=scheduler.PhaseOneSchedule(individual.depots.get(j));

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

        Display display=new Display(individual);





    }
}
