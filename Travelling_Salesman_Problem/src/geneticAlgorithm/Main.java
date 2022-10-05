package geneticAlgorithm;

import calculate.DistanceCalculator;
import graphics.Frame;


public class Main {
    public static double[][] distances;
    public final static int POPULATION_SIZE = 10;

    public static Individual best;
    public static void main(String[] args) {

        distances = DistanceCalculator.createDistanceMatrix();
        best = Individual.createIndividual();
        Frame frame = new Frame(best);
        double sum = 0;
        for (int j = 0; j < 1; j++) {
            System.out.println("Island " + j);
            IslandHandler island = new IslandHandler();
            island.reproduce(5, 400, frame);
            //sum += island.getBest().getFitness();
            if(best.getFitness() == island.getBest().getFitness()) {
                best = island.getBest();
                System.out.println("You're best Individual has the Fitness of: " + best.getFitness());
                frame.setBest(best);
                frame.repaint(1000);
            }
        }
        //System.out.println("Average: " + sum/50);

    }
}


    /*
       Dies ist das Optimum das für diese Aufgabe erreicht werden kann
       Integer [] genotyp ={
               1, 8, 38, 31, 44, 18, 7, 28, 6, 37, 19, 27, 17, 43, 30, 36, 46, 33, 20, 47, 21, 32,39, 48, 5, 42, 24, 10, 45, 35, 4, 26,
               2,29,34,41,16, 22, 3, 23, 14, 25,13,11, 12,15,40,9
       };
       for (int i = 0; i < genotyp.length; i++) {
           genotyp [i] --;
       }
       Individual individual = new Individual(genotyp);
       System.out.println(individual.getFitness());

        */



