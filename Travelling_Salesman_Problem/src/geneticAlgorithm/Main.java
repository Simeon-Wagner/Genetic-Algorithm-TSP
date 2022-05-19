package geneticAlgorithm;

import calculate.DistanceCalculator;

import java.util.Arrays;

public class Main {
    public static double [] [] distances;
    public static final int AMOUNT_OF_HERDS = 10;

    public static void main(String[] args) {
        distances = DistanceCalculator.createDistanceMatrix();
        Herd [] herds = new Herd[AMOUNT_OF_HERDS];

        for (int i = 0; i < AMOUNT_OF_HERDS; i++) {
            herds [i] = new Herd(10, i);
        }
        for (int i = 0; i < 50; i++) {
            for (Herd pop : herds ) {
                pop.reproduce();
            }
            for (int j = 0; j < herds.length; j++) {
                herds[j].addNewAlpha();
            }
            printAlphas( i );
        }

        Arrays.sort(Herd.alphaIndividuals,(Individual o1, Individual o2) -> {
            return Double.compare(o1.getFitness(), o2.getFitness());
        });
        System.out.println(Herd.alphaIndividuals[0].getFitness());

    }
    public static void printAlphas(int generation){

        System.out.print(generation + ": ");
        Arrays.stream(Herd.alphaIndividuals).forEach(e -> {
            System.out.print((int) e.getFitness() + " ");
        });
        System.out.println();

    }

    //The Idea is to create a certain amount of islands in which the population reproduces on its own
    //After a certain amount of reproduction the best of the Islands are chosen and put on an Island.
    public static void islandEvolution (int amountOfIslands){

    }
    // There are two or more Population, that interchange their population at a specific time.
    public static void nomadicEvolution () {

    }

    public static void simpleEvolution(){
      //  Population pop = new Population();
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
}
