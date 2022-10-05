package geneticAlgorithm;

import java.util.Arrays;

public class Herd {
    private int populationSize;
    private Individual[] population;
    int herdNr;
    Individual bestInHerd;


    public Herd(int populationSize, int herdNr) {
        this.populationSize = populationSize;
        this.herdNr = herdNr;
        population = new Individual[populationSize];
        createPopulation();

        bestInHerd = Individual.createIndividual();

        Arrays.sort(population, (Individual o1, Individual o2) -> {
            return Double.compare(o1.getFitness(), o2.getFitness());
        });
    }

    public void createPopulation() {
        for (int i = 0; i < populationSize; i++) {
            population[i] = Individual.createIndividual();
        }
    }

    public void reproduce(int generations) {
        for (int i = 0; i < generations; i++) {
            //Bestes Individuum wird ausgesucht um sich mittels eines per Rouletteselektion ausgesuchtes
            //Individuum zu reproduzieren
            for (int j = 0; j < 2; j++) {
                Individual father = Selection.getRouletteSelection(population,new Individual()); // Das beste Individuum wird aus der Population/Herde ausgesucht.
                Individual mother = Selection.getRouletteSelection(population, father);
                Individual[] children = Reproduction.onePointCrossover(mother, father);
                Mutate.mutate(mother);
                Mutate.mutate(father);
                Mutate.mutate(children[0]);
                Mutate.mutate(children[1]);
                population[populationSize - 1] = children[1];
                population[populationSize - 2] = children[0];
                Arrays.sort(population, (Individual o1, Individual o2) -> {
                    return Double.compare(o1.getFitness(), o2.getFitness());
                });

                if(population[0].getFitness() < bestInHerd.getFitness()){
                    Integer [] newGenotype = new Integer [population[0].getGenotype().length];
                    System.arraycopy(population[0].getGenotype(),0, newGenotype,0,population[0].getGenotype().length);
                    bestInHerd = new Individual(newGenotype);
                }
            }
        }
    }

    public Individual[] getPopulation() {
        return population;
    }

    public int getPopulationSize() {
        return populationSize;
    }
}
