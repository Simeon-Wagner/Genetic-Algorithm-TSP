package geneticAlgorithm;


import Probieren.Vertauschen;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Individual{
    private Integer [] genotype;
    public double fitness;

    public Individual (){}
    public Individual(Integer [] genotype){

        this.genotype = genotype;
        this.fitness = calculateFitness();
    }


    public double calculateFitness() {
        double fitness = 0;
        for (int i = 0; i < genotype.length; i++) {
            if(i < genotype.length-1) {
                fitness += Main.distances[genotype[i]][genotype[i+1]];
            }else{
                fitness += Main.distances[genotype[i]][genotype[0]];
            }
        }
        return fitness;
    }

    public static Individual createIndividual(){
        Integer[] genotype = IntStream.range(0, Main.distances.length).boxed().toArray(Integer[]::new);
        List<Integer> genoList = Arrays.asList(genotype);
        Collections.shuffle(genoList);
        genoList.toArray(genotype);
        return new Individual(genotype);
    }

    public double getFitness(){
        return fitness;
    }
    public void setFitness (){
        this.fitness = calculateFitness();
    }

    public Integer[] getGenotype() {
        return genotype;
    }

    public void setGenotype(Integer [] genotype) {
        this.genotype = genotype;
    }

}
