package Graphics;

import Objects.Depot;
import Objects.VechicleRoute;

import java.awt.*;
import java.util.ArrayList;

public class canvas extends Canvas {

            public ArrayList<ArrayList<VechicleRoute>> routes;
            public ArrayList<Depot> depots;
            public double max_cordinate;
            public double min_cordinate;
            public double boarder = 5;

            //List<Color> depotColors = new ArrayList<Color>(Arrays.asList(Color.blue, Color.red, Color.green, Color.black, Color.yellow, Color.pink, Color.cyan, Color.magenta, Color.orange, Color.lightGray));

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

            // x*this.getWidth()/100 and this.getHeight()-y*this.getHeight()/100
            // to transform from 0-100 coordinates to screen coordinates
            g.drawLine((x_cordinate_1 + (int)(-min_cordinate +boarder)) * this.getWidth() / (int)(max_cordinate - min_cordinate +2*boarder),
                this.getHeight() - (y_cordinate_1 + (int)(-min_cordinate +boarder)) * this.getHeight() / (int)(max_cordinate - min_cordinate +2*boarder),
                (x_cordinate_2 + (int)(-min_cordinate +boarder)) * this.getWidth() / (int)(max_cordinate - min_cordinate +2*boarder),
                this.getHeight() - (y_cordinate_2 + (int)(-min_cordinate +boarder)) * this.getHeight() / (int)(max_cordinate - min_cordinate +2*boarder));
            }

            public void paint(Graphics g)
            {
                for(int i=0; i<depots.size();i++){
                    drawPoint(g,depots.get(i).x_cordinate,depots.get(i).y_cordinate,Color.black,7);
                }

                for (int j=0; j<routes.size();j++){

                    for (int k=0; k<routes.get(j).size();k++){

                        for (int l=0;l<routes.get(j).get(k).customers.size();l++){
                            int x_cordinate=routes.get(j).get(k).customers.get(l).x_cordinate;
                            int y_cordinate=routes.get(j).get(k).customers.get(l).y_cordinate;

                            drawPoint(g,x_cordinate,y_cordinate,Color.BLUE, 7);

                            if(l>0){
                              int x_cordinate_prev=routes.get(j).get(k).customers.get(l).x_cordinate;
                              int y_cordinate_prev=routes.get(j).get(k).customers.get(l).y_cordinate;
                            }


                        }

                    }
                }

            }



}
