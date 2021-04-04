import Objects.Depot;
import Objects.VechicleRoute;
import java.io.File;
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


            if(vRoute.vechicle_load+depot.customers.get(i).demand<=depot.max_load_vechicle
                    && (depot.max_duration==0 || vRoute.duration+depot.customers.get(i).duration<=depot.max_duration)){

                //check if first customer
                if(vRoute.customers.size()<1){
                    distance=eucleadianDistance(depot.x_cordinate, depot.y_cordinate, depot.customers.get(i).x_cordinate, depot.customers.get(i).y_cordinate);
                    vRoute.duration+=distance;

                }
                else{
                    //find the distance between the customer and the depot
                    distance=eucleadianDistance(depot.customers.get(i).x_cordinate, depot.customers.get(i).y_cordinate,
                            vRoute.customers.get(vRoute.customers.size()-1).x_cordinate,vRoute.customers.get(vRoute.customers.size()-1).y_cordinate);
                    vRoute.duration+=distance;

                    //checks if last customer
                    if(i==(depot.customers.size()-1)){
                        distance=eucleadianDistance(depot.x_cordinate, depot.y_cordinate, depot.customers.get(i).x_cordinate, depot.customers.get(i).y_cordinate);
                        vRoute.duration+=distance;
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
                vRoute.duration+=distance;



                //Intialize new route and assign new customer
                vRoute=new VechicleRoute(depot);
                vRoutes.add(vRoute);
                distance=eucleadianDistance(depot.x_cordinate, depot.y_cordinate,
                        depot.customers.get(i).x_cordinate, depot.customers.get(i).y_cordinate);
                vRoute.duration+=distance;
                vRoute.customers.add(depot.customers.get(i));
                vRoute.vechicle_load+=depot.customers.get(i).demand;
            }
        }

        return vRoutes;
    }


    //Phase 2 of route scheduling
    public void PhaseTwoSchedule(ArrayList<VechicleRoute> vRoutes){

    }




}
