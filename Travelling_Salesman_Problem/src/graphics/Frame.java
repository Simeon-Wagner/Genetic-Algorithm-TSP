package graphics;

import javax.swing.*;
import calculate.DistanceCalculator;
import geneticAlgorithm.Individual;

import java.awt.*;
import java.util.ArrayList;

import static calculate.DistanceCalculator.createDistanceMatrix;

public class Frame extends JFrame {


    public static void main(String[] args) {
        DistanceCalculator.createDistanceMatrix();

        //Frame frame = new Frame();
    }
    Panel panel;
    Individual best;
    public Frame(Individual bes){

        this.best = best;
        this.panel = new Panel(best);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(panel);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);


    }

    public void setBest(Individual best) {

        this.best = best;
        panel.setBest(best);
    }
}
