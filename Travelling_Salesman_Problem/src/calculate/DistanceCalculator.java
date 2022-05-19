package calculate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DistanceCalculator {
    static ArrayList <City> cities = new ArrayList<>();


    public static double [] [] createDistanceMatrix() {
        readFile();
        double [] [] distanceMatrix = new double [cities.size()] [cities.size()];

        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix[0].length; j++) {
                double distance = calculateDistance(cities.get(i), cities.get(j));
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

            while(input != null ){

                if( input != null && index > 5 && index < 54 ){
                    String [] split = input.split("[ ]+");
                    cities.add( new City(split[0], split[1], split[2]) );
                }
                index ++;
                input = bufferedReader.readLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }



    //Optimierungsidee: die Hauptdiagonale ist immer gleich null und die obere Hälfte ist die Untere gespiegelt.
    //Man kann somit Rechenzeit einsparen.
    private static double calculateDistance(City a, City b){
        double xlength = b.getX() - a.getX();
        double ylength = b.getY() - a.getY();

        return Math.sqrt(Math.pow(xlength,2) + Math.pow(ylength,2));
    }


    /*
    public static void main(String[] args) {

        cities.add(new calculate.City("1", "2", "3"));
        cities.add(new calculate.City("2", "5", "6"));
        cities.add(new calculate.City("3", "1", "2"));
        cities.add(new calculate.City("4", "4", "5"));


        double [][] matrix = createDistanceMatrix();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%5f",  matrix[i][j]);
                System.out.print("  ");
            }
            System.out.println();
        }
    }
    */
}
