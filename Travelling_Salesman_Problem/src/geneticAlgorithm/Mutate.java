package geneticAlgorithm;

public class Mutate {
    public static void mutate (Individual  individual) {

        for (int i = 0; i < individual.getGenotype().length; i++) {
            double randomNr = Math.random();
            if (randomNr < 0.015) {
                int toBeMutated = (int) (Math.random()*individual.getGenotype().length);
                Integer temp = individual.getGenotype()[i];
                individual.getGenotype()[i] = individual.getGenotype()[toBeMutated];
                individual.getGenotype()[toBeMutated] = temp;
            }
        }
    }
}
