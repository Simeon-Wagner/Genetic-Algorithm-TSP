package geneticAlgorithm;

public class Reproduction {
    private Individual mother;
    private Individual father;

    public Reproduction (Individual mother, Individual father){
        this.mother = mother;
        this.father = father;
    }

    public Individual [] onePointCrossover (){
        int length = mother.getGenotype().length;
        Integer [] genotypeSon = new Integer [length];
        Integer [] genotypeDaughter = new Integer [length];

        int onePoint = (int) (Math.random()*length);

        while (onePoint == length || onePoint == 0){
            onePoint = (int) (Math.random()*length);
        }


        int indexDaughter = onePoint+1;
        int indexSon = onePoint+1;

        System.arraycopy(mother.getGenotype(),0, genotypeDaughter, 0, onePoint+1);
        System.arraycopy(father.getGenotype(),0, genotypeSon, 0, onePoint+1);

        for (int i = 0; i < length; i++) {
            if(! containsCity(genotypeDaughter,father.getGenotype()[i])) {
                genotypeDaughter[indexDaughter] = father.getGenotype()[i];
                indexDaughter++;
            }
            if(! containsCity(genotypeSon, mother.getGenotype()[i])){
                genotypeSon[indexSon] = mother.getGenotype()[i];
                indexSon++;
            }
        }
        Individual daughter = new Individual(genotypeDaughter);
        Individual son = new Individual(genotypeSon);

        Individual [] children = { daughter, son};

        return  children;
    }

    private boolean containsCity (Integer [] genotype, Integer city) {
        for (int i = 0; i < genotype.length; i++) {
            if(genotype[i] == null){
                return false;
            }
            if(genotype[i] == city){
                return true;
            }
        }
        return true;
    }

    public Individual getFather() {
        return father;
    }

    public void setFather(Individual father) {
        this.father = father;
    }

    public Individual getMother() {
        return mother;
    }

    public void setMother(Individual mother) {
        this.mother = mother;
    }

    /*
    public static void main(String[] args) {
        Integer [] gen = {6,5,8,7,9,2,4};
        Integer [] gen1 = {5,4,2,6,8,9,7};
        Individual a = new Individual(gen);
        Individual b = new Individual(gen1);
        Reproduction rep = new Reproduction(a, b);
        Individual [] children  = rep.onePointCrossover();
        Arrays.stream(children[0].getGenotype()).forEach( x -> System.out.print(x + " "));
        System.out.println();
        Arrays.stream(children[1].getGenotype()).forEach( x -> System.out.print(x + " "));
    }
     */
}
