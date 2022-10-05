package graphics;

import calculate.DistanceCalculator;
import geneticAlgorithm.Individual;
import geneticAlgorithm.Main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panel extends JPanel {
    public final int WIDTH  = 800;
    public final int HEIGHT = 500;
    static ArrayList<Point> coordinates = new ArrayList<>();
    Individual best;
    Panel (Individual best){

        this.best = best;
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));

        getCoordinatePoints();

    }

    public void paintComponent (Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setStroke(new BasicStroke(5));

        graphics2D.setPaint(Color.blue);

        for (Point point: coordinates ) {
            //System.out.println("This is x: " + point.x + "\nThis is y: " + point.y);
            graphics2D.drawLine(point.x, point.y,point.x, point.y);

        }


        graphics2D.setStroke(new BasicStroke(2));

        graphics2D.setPaint(Color.black);

        if(best != null){
            Integer [] genotype = best.getGenotype();
            int length = genotype.length;
            for (int i = 0; i < length; i++) {
                if(i < length-1){
                    Point p = coordinates.get(genotype[i]);
                    Point p1 = coordinates.get(genotype[i+1]);
                    graphics2D.drawLine(p.x, p.y, p1.x, p1.y);
                }else{
                    Point p = coordinates.get(genotype[i]);
                    Point p1 = coordinates.get(genotype[0]);
                    graphics2D.drawLine(p.x, p.y, p1.x, p1.y);
                }
            }
            graphics2D.drawString("Fitness: " + best.getFitness(), 50,50);


        }




    }



    private void getCoordinatePoints() {
        Dimension d = this.getPreferredSize();
        int height = (int) d.getHeight();
        int width = (int) d.getWidth();
        System.out.println(width + " " + height);
        for (Point p : DistanceCalculator.cities) {


            //We have to adapt x and y to the new coordinate system
            int x = (int) p.getX();
            int y = (int) p.getY();



            x = 50 + x /15;
            y = -50 + height - (y /15);

            System.out.println("This is x: " + x + "\nThis is y: " + y);

            Point coordinate = new Point(x,y);
            coordinates.add(coordinate);
        }
    }

    public void setBest(Individual best) {
        this.best = best;
    }
}
