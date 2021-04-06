package Graphics;

import Objects.Depot;
import Objects.VechicleRoute;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class canvas extends Canvas {

            public ArrayList<ArrayList<VechicleRoute>> routes;
            public ArrayList<Depot> depots;
            public double max_cordinate;
            public double min_cordinate;
            public double boarder = 5;



            public void drawPoint(Graphics g, int x_cordinate, int y_cordinate, Color color, int size){
                g.setColor(color);
                // transform from min-max point coordinates to screen coordinates
                // - size/2 to get the center of the point at the coordinate
                g.fillOval((x_cordinate + (int)(-min_cordinate +boarder)) * this.getWidth() / (int)(max_cordinate - min_cordinate +2*boarder)-size/2,
                        this.getHeight() - (y_cordinate + (int)(-min_cordinate +boarder)) * this.getHeight() / (int)(max_cordinate - min_cordinate +2*boarder) - size/2,
                        size,
                        size);
            }

            public void drawLine(Graphics g, int x_cordinate_1, int y_cordinate_1, int x_cordinate_2, int y_cordinate_2, Color color) {
            g.setColor(color);

            // x*this.getWidth()/100 and this.getHeight()-y*this.getHeight()/100 - Remove?
            // to transform from 0-100 coordinates to screen coordinates - Remove?
            g.drawLine((x_cordinate_1 + (int)(-min_cordinate +boarder)) * this.getWidth() / (int)(max_cordinate - min_cordinate +2*boarder),
                this.getHeight() - (y_cordinate_1 + (int)(-min_cordinate +boarder)) * this.getHeight() / (int)(max_cordinate - min_cordinate +2*boarder),
                (x_cordinate_2 + (int)(-min_cordinate +boarder)) * this.getWidth() / (int)(max_cordinate - min_cordinate +2*boarder),
                this.getHeight() - (y_cordinate_2 + (int)(-min_cordinate +boarder)) * this.getHeight() / (int)(max_cordinate - min_cordinate +2*boarder));


            }

            public void paint(Graphics g)
            {

                ArrayList<Color> colors= new ArrayList<Color>();
                colors.add(Color.GREEN); colors.add(Color.ORANGE); colors.add(Color.RED);colors.add(Color.CYAN);colors.add(Color.MAGENTA);colors.add(Color.YELLOW); colors.add(Color.GRAY);



                for(int i=0; i<depots.size();i++){
                    drawPoint(g,depots.get(i).x_cordinate,depots.get(i).y_cordinate,Color.BLACK,10);
                }

                for (int j=0; j<routes.size();j++){

                    for (int k=0; k<routes.get(j).size();k++){

                        for (int l=0;l<routes.get(j).get(k).customers.size();l++){
                            int x_cordinate=routes.get(j).get(k).customers.get(l).x_cordinate;
                            int y_cordinate=routes.get(j).get(k).customers.get(l).y_cordinate;

                            int depot_x_cordinate=routes.get(j).get(k).depot.x_cordinate;
                            int depot_y_cordinate=routes.get(j).get(k).depot.y_cordinate;

                            drawPoint(g,x_cordinate,y_cordinate,Color.BLUE, 7);

                            if(l==0 || l==(routes.get(j).get(k).customers.size()-1)){
                                drawLine(g,x_cordinate,y_cordinate,depot_x_cordinate,depot_y_cordinate,colors.get(k%7));
                            }


                            if(l>0){
                                int x_cordinate_prev=routes.get(j).get(k).customers.get(l-1).x_cordinate;
                                int y_cordinate_prev=routes.get(j).get(k).customers.get(l-1).y_cordinate;
                                drawLine(g, x_cordinate_prev,y_cordinate_prev,x_cordinate, y_cordinate, colors.get(k%7));
                            }


                        }

                    }
                }

            }



}
