package Graphics;

import Objects.Individual;

import javax.swing.*;
import java.awt.*;

public class Display extends JFrame {

    canvas canvas=new canvas();

    public Display(Individual individual){
        canvas.routes=individual.routes;
        canvas.max_cordinate=individual.max_cordinate;
        canvas.min_cordinate=individual.min_cordinate;
        canvas.depots=individual.depots;

        setLayout(new BorderLayout());
        setSize(900, 900);
        setTitle("Graphical Solution");
        add( canvas);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setVisible(true);


    }
}
