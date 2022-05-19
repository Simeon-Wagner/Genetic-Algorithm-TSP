package geneticAlgorithm;

import java.util.Arrays;


public class Selection {

    public static Individual getIndividualByIndex (Individual [] population, int index) {
        Arrays.sort(population, (Individual o1, Individual o2) -> {
            return Double.compare(o1.getFitness(), o2.getFitness());
        });
        return population[index];
    }



    //Rouletterad für die jeweilge Herde wird erstellt
    private static int [] createRouletteWheel( Individual[] population) {
        int [] roulette = new int [population.length];
        double sum = getSumFitness(population);
        int sumProportion = 0;
        for (int i = 0; i < population.length; i++) {
            int proportion = (int) (sum/ population[i].getFitness());
            sumProportion += proportion;
            roulette [i] = sumProportion;
        }
        return roulette;
    }

    //Das Rouletterad wird gedreht ein Individuum wird zurückgegeben, es wird nicht überprüft was für ein Individuum es ist
    public static Individual spinWheel(Individual[] population) {
        int [] rouletteWheel = createRouletteWheel(population);
        int index = 0;
        int rouletteBall = (int) (Math.random()*rouletteWheel[rouletteWheel.length-1]);
        int i = 0;
        while ( rouletteBall > rouletteWheel[i] ){
            if(i == 0) index = 0;
            else index = i-1;
            i++;
        }
        return population[index];
    }

    //Das Rouletterad wird gedreht und es wird solange gedreht bis man ein Individuum hat, dass nicht den übergebene Individuum entspricht
    //Sinn dahinter ist man will eine Reproduktion mit den gleichen Individuum vermeiden
    public static Individual getRouletteSelection (Individual [] population, Individual father) {
        Individual mother = spinWheel(population);
        while (father.equals(mother)){
            mother = spinWheel(population);
        }
        return  mother;
    }



    public static double getSumFitness(Individual [] population) {
        double sum = 0;
        for (Individual individual : population) {
            sum += individual.getFitness();
        }
        return sum;
    }
}
