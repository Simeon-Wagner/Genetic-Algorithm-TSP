package geneticAlgorithm;


public class Mutate {

    static final double PROBABILITY = 0.01;

    public static void mutate (Individual  individual) {

        boolean mutationOcurred = false;
        for (int i = 0; i < individual.getGenotype().length; i++) {
            double randomNr = Math.random();
            if (randomNr < PROBABILITY) {
                int toBeMutated = (int) (Math.random()*individual.getGenotype().length);
                Integer temp = individual.getGenotype()[i];
                individual.getGenotype()[i] = individual.getGenotype()[toBeMutated];
                individual.getGenotype()[toBeMutated] = temp;
                mutationOcurred = true;
            }
        }
        if(mutationOcurred){
            individual.setFitness();
        }
    }
}
