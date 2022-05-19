package geneticAlgorithm;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Herd {
    private int populationSize;
    private Individual[] population;
    public static Individual [] alphaIndividuals = new Individual[Main.AMOUNT_OF_HERDS];
    static int newBull = 1;
    int herd;
    int avgFitness;




    public Herd(int populationSize, int herd) {
        this.populationSize = populationSize;
        this.herd = herd;

        population = new Individual[populationSize];
        createIndividuals();
    }

    public void createIndividuals() {
        for (int i = 0; i < populationSize; i++) {

            Integer[] genotype = IntStream.range(0, Main.distances.length).boxed().toArray(Integer[]::new);
            List<Integer> genoList = Arrays.asList(genotype);
            Collections.shuffle(genoList);
            genoList.toArray(genotype);
            population[i] = new Individual(genotype);

        }
    }

    public void reproduce() {
        generationPass();
        alphaIndividuals [herd] = this.population[0];
    }
    public void addNewAlpha () {

        this.population[0] = alphaIndividuals [newBull % Main.AMOUNT_OF_HERDS];
        newBull++;
        //if(newBull == Main.AMOUNT_OF_HERD) newBull++;

    }

    public void generationPass() {

        Reproduction reproduction = new Reproduction(null, null);
        //Individual best = population[0];

        for (int i = 0; i < 1000; i++) {

            Individual father = Selection.getIndividualByIndex(population,0);
            Individual mother = Selection.getRouletteSelection(population, father);
            Individual mother1 = Selection.getIndividualByIndex(population,1);
            Individual father1 = Selection.getRouletteSelection(population, mother1);

            reproduction.setFather(father);
            reproduction.setMother(mother);

            Individual[] children = reproduction.onePointCrossover();

            population[populationSize-1] = children[0];
            population[populationSize-2] = children[1];

            Mutate.mutate(mother);
            Mutate.mutate(father);


            reproduction.setFather(father1);
            reproduction.setMother(mother1);

            Individual[] children1 = reproduction.onePointCrossover();

            population[populationSize-3] = children1[0];
            population[populationSize-4] = children1[1];

            Mutate.mutate(mother1);
            Mutate.mutate(father1);


            /*
            if( !best.equals(population[0]) ){
                best = population[0];
                System.out.println("The fittest in the generation " + i + " has the fitness value: " + population[0].getFitness());
            }

             */

            //Mutate.mutate(children[0]);
            //Mutate.mutate(children[1]);
        }
        Arrays.sort(population, (Individual o1, Individual o2) -> {
            return Double.compare(o1.getFitness(), o2.getFitness());
        });
    }



    public double getAVGFitness() {
        double sum = Selection.getSumFitness(population);
        return sum / populationSize;
    }

    public Individual[] getPopulation() {
        return population;
    }

    public int getPopulationSize() {
        return populationSize;
    }
}
