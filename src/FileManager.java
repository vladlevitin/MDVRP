import Objects.Chromosome;
import Objects.Customer;
import Objects.Depot;
import Objects.Individual;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {


    public void ImportData(String filename, Individual individual)  throws FileNotFoundException {




        //Boundaries for the cordinates of the customers and depors
        int max_cordinate=0;
        int min_cordinate=0;




        Scanner scanner= new Scanner(new File(filename));

        String[] list = scanner.nextLine().split(" "); //split all the lines into a list

        int n_vechicles = Integer.parseInt(list[0]);

        ArrayList<Integer> duration_list=new ArrayList<Integer>();
        ArrayList<Integer> max_load_vechicle_list=new ArrayList<Integer>();

        for(int i=0; i<Integer.parseInt(list[2]); i++){
            int duration=scanner.nextInt();
            int max_load_vechicle=scanner.nextInt();

            duration_list.add(duration);
            max_load_vechicle_list.add(max_load_vechicle);

        }


        for(int i=0; i<Integer.parseInt(list[1]); i++){
            //initialize customers
            int ID = scanner.nextInt();
            int x_cordinate = scanner.nextInt();
            int y_cordinate = scanner.nextInt();
            int duration=scanner.nextInt();
            int demand=scanner.nextInt();

            Customer customer= new Customer(ID, x_cordinate, y_cordinate, duration, demand);

            individual.addCustomer(customer);

            max_cordinate = Math.max(max_cordinate, Math.max(x_cordinate, y_cordinate));
            min_cordinate = Math.min(min_cordinate, Math.min(x_cordinate, y_cordinate));

            scanner.nextLine();

        }
        for(int i=0; i<Integer.parseInt(list[2]); i++){
            int ID = scanner.nextInt();
            int x_cordinate=scanner.nextInt();
            int y_cordinate=scanner.nextInt();

            int duration=duration_list.get(i);
            int max_load_vechicle=max_load_vechicle_list.get(i);

            Depot depot=new Depot(ID,x_cordinate,y_cordinate,duration,max_load_vechicle, n_vechicles);

            individual.addDepot(depot);

            max_cordinate = Math.max(max_cordinate, Math.max(x_cordinate, y_cordinate));
            min_cordinate = Math.min(min_cordinate, Math.min(x_cordinate, y_cordinate));

            scanner.nextLine();
        }
        individual.max_cordinate=max_cordinate;
        individual.min_cordinate=min_cordinate;
        scanner.close();
    }

    public void nearestDepot(Individual individual){
        for (Customer customer : individual.customers){
            Double min_distance=999.0;
            Depot closest_Depot = null;
            for (Depot depot: individual.depots){
                Double distance=Math.sqrt(Math.pow((customer.x_cordinate-depot.x_cordinate),2) +Math.pow((customer.y_cordinate-depot.y_cordinate),2));

                if(distance<min_distance){
                    min_distance=distance;
                    closest_Depot=depot;

                }

            }

            closest_Depot.addCustomer(customer);



        }


    }

    public void exportSolution(Individual individual, String filename) throws IOException {
        File file = new File(filename);


        FileWriter fileWriter=new FileWriter(file);

        double fitness= individual.getFitness();
        System.out.println(fitness+"\n");

        fileWriter.write(fitness+"\n");

        for(int i=0; i<individual.routes.size();i++){

            for(int j=0; j<individual.routes.get(i).size(); j++){

                 double distance=individual.routes.get(i).get(j).distance;
                 int vechicle_load=individual.routes.get(i).get(j).vechicle_load;

                 System.out.println(distance+"\t"+vechicle_load+"\n");

                 fileWriter.write((i+1)+"\t"+(j+1)+"\t"+distance+"\t"+vechicle_load+"\t0");

                 for (int k=0;k<individual.routes.get(i).get(j).customers.size();k++){
                     fileWriter.write(" "+individual.routes.get(i).get(j).customers.get(k).ID);

                     System.out.println(individual.routes.get(i).get(j).customers.get(k).ID+"\n");
                 }
                 System.out.println("\n");
                 fileWriter.write(" 0 \n");
            }

        }

        fileWriter.close();


    }



}
