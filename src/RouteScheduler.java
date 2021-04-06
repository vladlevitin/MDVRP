import Objects.Customer;
import Objects.Depot;
import Objects.Individual;
import Objects.VechicleRoute;

import java.util.ArrayList;


public class RouteScheduler {


    public double eucleadianDistance(int x_1, int y_1, int x_2, int y_2){
        return Math.sqrt(Math.pow((x_1-x_2),2) +Math.pow((y_1-y_2),2));
    }

    //Phase 1 of route scheduling

    public ArrayList<VechicleRoute> PhaseOneSchedule(Depot depot){

        ArrayList<VechicleRoute> vRoutes=new ArrayList<VechicleRoute>();

        VechicleRoute vRoute=new VechicleRoute(depot);
        vRoutes.add(vRoute);

        for (int i=0;i<depot.customers.size();i++){
            double distance=0;

            //check if the number of Vechicles has been exceeded
            if(depot.n_vechicles<vRoutes.size()){
                depot.vehicles_exceeded=true;
            }

            if(vRoute.vechicle_load+depot.customers.get(i).demand<=depot.max_load_vechicle
                    && (depot.max_duration==0 || vRoute.distance +depot.customers.get(i).duration<=depot.max_duration)){

                //check if first customer
                if(vRoute.customers.size()<1){
                    distance=eucleadianDistance(depot.x_cordinate, depot.y_cordinate, depot.customers.get(i).x_cordinate, depot.customers.get(i).y_cordinate);
                    vRoute.distance +=(distance+depot.customers.get(i).duration);

                }
                else{
                    //find the distance between the customer and the depot
                    distance=eucleadianDistance(depot.customers.get(i).x_cordinate, depot.customers.get(i).y_cordinate,
                            vRoute.customers.get(vRoute.customers.size()-1).x_cordinate,vRoute.customers.get(vRoute.customers.size()-1).y_cordinate);
                    vRoute.distance +=(distance+depot.customers.get(i).duration);

                    //checks if last customer
                    if(i==(depot.customers.size()-1)){
                        distance=eucleadianDistance(depot.x_cordinate, depot.y_cordinate, depot.customers.get(i).x_cordinate, depot.customers.get(i).y_cordinate);
                        vRoute.distance +=(distance+depot.customers.get(i).duration);
                    }
                }
                vRoute.customers.add(depot.customers.get(i));
                vRoute.vechicle_load+=depot.customers.get(i).demand;

            }
            //duration or load constraints are not met, add as a last customer
            else{
                //attach the previous customer to the depot
                distance=eucleadianDistance(depot.x_cordinate, depot.y_cordinate,
                        vRoute.customers.get(vRoute.customers.size()-1).x_cordinate,vRoute.customers.get(vRoute.customers.size()-1).y_cordinate);
                vRoute.distance +=distance;



                //Intialize new route and assign new customer
                vRoute=new VechicleRoute(depot);
                vRoutes.add(vRoute);
                distance=eucleadianDistance(depot.x_cordinate, depot.y_cordinate,
                        depot.customers.get(i).x_cordinate, depot.customers.get(i).y_cordinate);
                vRoute.distance +=(distance+depot.customers.get(i).duration);
                vRoute.customers.add(depot.customers.get(i));
                vRoute.vechicle_load+=depot.customers.get(i).demand;
            }
        }

        return vRoutes;
    }

    //Phase 2 helper functions
    public void setVechicleRouteDuration(VechicleRoute vechicleRoute){
        vechicleRoute.distance =0;
        for (int i =0; i<vechicleRoute.customers.size(); i++){
            //first customer
            if(i==0){
                double distance=eucleadianDistance(vechicleRoute.depot.x_cordinate,vechicleRoute.depot.y_cordinate,
                        vechicleRoute.customers.get(i).x_cordinate, vechicleRoute.customers.get(i).y_cordinate);

                vechicleRoute.distance +=(distance+ vechicleRoute.customers.get(i).duration);
            }
            else {
                double distance=eucleadianDistance(vechicleRoute.customers.get(i).x_cordinate, vechicleRoute.customers.get(i).y_cordinate,
                        vechicleRoute.customers.get(i-1).x_cordinate, vechicleRoute.customers.get(i-1).y_cordinate);

                vechicleRoute.distance +=(distance+ vechicleRoute.customers.get(i).duration);

                //last customer
                if(i==vechicleRoute.customers.size()-1){
                    distance=eucleadianDistance(vechicleRoute.depot.x_cordinate,vechicleRoute.depot.y_cordinate,
                            vechicleRoute.customers.get(i).x_cordinate, vechicleRoute.customers.get(i).y_cordinate);

                    vechicleRoute.distance +=distance;
                }

            }
        }
    }

    //set the load of vechicle route
    public void setVechicleRouteLoad(VechicleRoute vechicleRoute){
        vechicleRoute.vechicle_load=0;
        for( int i=0; i<vechicleRoute.customers.size();i++){
            vechicleRoute.vechicle_load+=vechicleRoute.customers.get(i).demand;
        }

    }

    public boolean validConstraint(VechicleRoute vechicleRoute, Customer customer){
        if(vechicleRoute.vechicle_load+customer.demand<=vechicleRoute.depot.max_load_vechicle) {
            if (vechicleRoute.depot.max_duration == 0 || (vechicleRoute.distance + customer.duration <= vechicleRoute.depot.max_duration)) {
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }

    }

    //Phase 2 of route scheduling
    public void PhaseTwoSchedule(ArrayList<VechicleRoute> vRoutes){

        for (int i=0; i<vRoutes.size()-1; i++){

            VechicleRoute vechicleRouteOne=vRoutes.get(i);
            VechicleRoute vechicleRouteTwo=vRoutes.get(i+1);

            //System.out.println("route selected");

            ArrayList<Customer> customersRouteOne=new ArrayList<Customer>(vechicleRouteOne.customers);
            ArrayList<Customer> customersRouteTwo=new ArrayList<Customer>(vechicleRouteTwo.customers);

            // get the last customer from first vechicle route
            Customer customer=vechicleRouteOne.customers.get(vechicleRouteOne.customers.size()-1);



            if(customersRouteOne.size()>0 && validConstraint(vechicleRouteTwo, customer)==true){

                //System.out.println("constraint met");

                VechicleRoute newVechicleRouteOne=new VechicleRoute(vechicleRouteOne.depot);
                VechicleRoute newVechicleRouteTwo=new VechicleRoute(vechicleRouteTwo.depot);

                customersRouteOne.remove(customersRouteOne.size()-1);
                customersRouteTwo.add(0,customer);

                newVechicleRouteOne.customers=customersRouteOne;
                newVechicleRouteTwo.customers=customersRouteTwo;

                setVechicleRouteDuration(newVechicleRouteOne);
                setVechicleRouteDuration(newVechicleRouteTwo);

                setVechicleRouteLoad(newVechicleRouteOne);
                setVechicleRouteLoad(newVechicleRouteTwo);

                //check if the new distance is less and if so replace the old routes with the new once
                double new_distance=newVechicleRouteOne.distance +newVechicleRouteTwo.distance;
                //System.out.println("New distance: "+new_distance);
                double old_distance=vechicleRouteOne.distance +vechicleRouteTwo.distance;
                //System.out.println("Old distance: "+old_distance);

                if(new_distance<old_distance){
                    //System.out.println("Route modified");
                    vRoutes.set(i,newVechicleRouteOne);
                    vRoutes.set(i+1, newVechicleRouteTwo);
                }


            }








        }
    }

    public void RouteScheduler(Individual individual){
        individual.routes=new ArrayList<ArrayList<VechicleRoute>>();
        for (int i=0;i<individual.depots.size();i++){
            ArrayList<VechicleRoute> depotRoutes=PhaseOneSchedule(individual.depots.get(i));

            //can be used to eliminate individuals with exceeded vehicles (measured by routes) per depot
            if(individual.depots.get(i).vehicles_exceeded==true){
                individual.vechiles_exceeded=true;
            }
            PhaseTwoSchedule(depotRoutes);
            individual.routes.add(depotRoutes);
        }
    }






}
