package geneticAlgorithm;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Reproduction {


    public static Individual [] onePointCrossoverReverse (Individual mother, Individual father){

        int length = mother.getGenotype().length;

        Integer [] genotypeSon = new Integer [length];
        Integer [] genotypeDaughter = new Integer [length];

            int onePoint = getRandomPoint(length);

            int indexDaughter = 0;
            int indexSon = 0;
            System.arraycopy(mother.getGenotype(), 0, genotypeDaughter, onePoint, length-onePoint);
            System.arraycopy(father.getGenotype(), 0, genotypeSon, onePoint , length-onePoint);

            for (int i = 0; i < length; i++) {
                if (!containsCity(genotypeDaughter, father.getGenotype()[i])) {

                    genotypeDaughter[indexDaughter] = father.getGenotype()[i];
                    indexDaughter++;
                }
                if (!containsCity(genotypeSon, mother.getGenotype()[i])) {
                    genotypeSon[indexSon] = mother.getGenotype()[i];
                    indexSon++;
                }
            }
        Individual daughter = new Individual(genotypeDaughter);
        Individual son = new Individual(genotypeSon);
        Individual [] children = { daughter, son };

        return  children;
    }

    public static Individual [] onePointCrossover (Individual mother, Individual father){

        int length = mother.getGenotype().length;

        Integer [] genotypeSon = new Integer [length];
        Integer [] genotypeDaughter = new Integer [length];

        int onePoint = getRandomPoint(length);

        int indexDaughter = onePoint + 1;
        int indexSon = onePoint + 1;

        System.arraycopy(mother.getGenotype(), 0, genotypeDaughter, 0, onePoint+1);
        System.arraycopy(father.getGenotype(), 0, genotypeSon, 0 , onePoint+1);

        for (int i = 0; i < length; i++) {
            if (!containsCity(genotypeDaughter, father.getGenotype()[i])) {

                genotypeDaughter[indexDaughter] = father.getGenotype()[i];
                indexDaughter++;
            }
            if (!containsCity(genotypeSon, mother.getGenotype()[i])) {
                genotypeSon[indexSon] = mother.getGenotype()[i];
                indexSon++;
            }
        }
        Individual daughter = new Individual(genotypeDaughter);
        Individual son = new Individual(genotypeSon);
        Individual [] children = { daughter, son };

        return  children;
    }

    private static boolean containsCity (Integer [] genotype, Integer city) {
        for (int i = 0; i < genotype.length; i++) {
            if(genotype[i] == city){
                return true;
            }
        }
        return false;
    }

    private static int getRandomPoint(int length){
        int onePoint = (int) (Math.random() * length);
        //Falls der OnePoint gleich Null ist oder gleich der Länge des Arrays würde der Crossover keinen Sinn ergeben
        //Deswegen wird es solange wiederholt bis dieser ungleich der Länge oder ungleich Null ist.
        while (onePoint == length || onePoint == 0) {
            onePoint = (int) (Math.random() * length);
        }
        return onePoint;
    }

    public static void main(String[] args) {
        Integer [] a = {9,8,7,6,5,4,3,2,1};
        Integer [] b = {1,2,3,4,5,6,7,8,9};

        Individual aInd = new Individual(a);
        Individual bInd = new Individual(b);

        Individual [] children = onePointCrossover(aInd,bInd);
        System.out.println("Mother");
        Arrays.stream(aInd.getGenotype()).forEach(e -> System.out.print(e + " "));
        System.out.println("\n Father");
        Arrays.stream(bInd.getGenotype()).forEach(e -> System.out.print(e + " "));
        System.out.println("\n Children 1");
        Arrays.stream(children[0].getGenotype()).forEach(e -> System.out.print(e + " "));
        System.out.println("\n Children 2");
        Arrays.stream(children[1].getGenotype()).forEach(e -> System.out.print(e + " "));
    }
}
