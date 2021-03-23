import Objects.Individual;

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
        System.out.println(individual.depots.get(3).customers.size());




    }
}
