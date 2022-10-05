package geneticAlgorithm;

import graphics.Frame;

import java.util.ArrayList;
import java.util.Arrays;

public class IslandHandler {
    public static  final int COWS_CHANGE_HERD = 1;
    public static final int AMOUNT_OF_HERDS = 100;

    public ArrayList<Individual []> alphaIndividuals = new ArrayList<>();
    int switchBull = 1;
    Herd [] herds;
    public static Individual  bestInIsland = Individual.createIndividual();


    public static ArrayList <Individual> bestIndidivduals = new ArrayList<>();

    public IslandHandler (){
        herds = createHerds();
    }


    public Herd[] createHerds() {
        Herd [] herds= new Herd[AMOUNT_OF_HERDS];
        for (int herdNr = 0; herdNr < AMOUNT_OF_HERDS; herdNr++) {
            herds [herdNr] = new Herd(Main.POPULATION_SIZE, herdNr);
        }
        return herds;
    }

    public void reproduce ( int generations, int generationalCyclesOnIsland, Frame frame) {
        for (int i = 0; i < generationalCyclesOnIsland; i++) {
            for (Herd pop : herds ) {
                pop.reproduce(generations);
                //Nach einer bestimmten Anzahl an Generation verlassen die Besten Individuen die Herde um sich einer neuen anzuschließen
               halfOfHerdLeaves(pop);
            }

            Individual best = this.getBest();
            frame.setBest(best);
            frame.repaint(1000);
            if(AMOUNT_OF_HERDS != 1) {
                for (Herd pop : herds) {
                    herdMigration(pop);
                }
            }

        }
    }

    public void halfOfHerdLeaves(Herd herd){

        int genotypeLength = herd.getPopulation()[0].getGenotype().length;
        Individual [] bestOfEachHerd = new Individual[COWS_CHANGE_HERD];
        for (int i = 0; i < COWS_CHANGE_HERD; i++) {
            Integer [] genotype = new Integer[genotypeLength];
            System.arraycopy(herd.getPopulation()[i].getGenotype(),0,genotype,0,genotypeLength);
            bestOfEachHerd [i] = new Individual(genotype);
        }
        alphaIndividuals.add(herd.herdNr, bestOfEachHerd);
    }
    public void herdMigration (Herd herd) {
        int genotypeLength = herd.getPopulation()[0].getGenotype().length;
        for (int i = 0; i < COWS_CHANGE_HERD; i++) {
            Integer [] genotype = new Integer[genotypeLength];
            System.arraycopy( alphaIndividuals.get(switchBull % AMOUNT_OF_HERDS)[i].getGenotype(), 0, genotype,0, genotypeLength );
            herd.getPopulation()[i] = new Individual( genotype );
        }
        switchBull++;
    }


    public Individual getBest(){
        Individual [] bests = new Individual[herds.length];
        for (int i = 0; i < herds.length; i++) {
            bests [i] = herds[i].bestInHerd;
        }

        Arrays.sort(bests, (Individual o1, Individual o2) -> {
            return Double.compare(o1.getFitness(), o2.getFitness());
        });
        return bests[0];
    }


}
