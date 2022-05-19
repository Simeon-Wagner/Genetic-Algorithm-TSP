package geneticAlgorithm;


public class Individual{
    private Integer [] genotype;
    private double fitness;

    public Individual(Integer [] genotype){
        this.genotype = genotype;
        fitness = calculateFitness();
    }

    public double calculateFitness () {
        double fitness = 0;
        for (int i = 0; i < genotype.length; i++) {
            if(i < genotype.length-1){
                fitness += Main.distances[genotype[i]][genotype[i+1]];
            }else{
                fitness += Main.distances[genotype[i]][genotype[0]];
            }
        }
        return fitness;
    }

    public double getFitness() {
        return calculateFitness();
    }

    public Integer[] getGenotype() {
        return genotype;
    }
    public void setGenotype(Integer [] genotype) {
        this.genotype = genotype;
    }

}
