package calculate;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DistanceCalculator {
    public static ArrayList <Point> cities = new ArrayList<>();


    public static double [] [] createDistanceMatrix() {
        readFile();
        double [] [] distanceMatrix = new double [cities.size()] [cities.size()];

        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix[0].length; j++) {
                Point firstPoint = cities.get(i);
                Point secondPoint = cities.get(j);
                double distance = Point2D.distance(firstPoint.getX(), firstPoint.getY(), secondPoint.getX(), secondPoint.getY());
                distanceMatrix [i] [j] = distance;
            }
        }
        return distanceMatrix;
    }

    private static void readFile() {
        try {
            String path = "Travelling_Salesman_Problem/source/att48.tsp";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

            int index = 0;
            String input = bufferedReader.readLine();

            while (input != null) {

                if (index > 5 && index < 54) {
                    String[] split = input.split("[ ]+");
                    cities.add(new Point(Integer.valueOf(split[1]), Integer.valueOf(split[2])));
                }
                index++;
                input = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
