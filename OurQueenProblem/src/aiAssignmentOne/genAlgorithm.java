package aiAssignmentOne;
import java.util.Arrays;
import java.util.Comparator;
public class genAlgorithm {


    public int[] solve(int n, int populationSize, double mutationProbability, double crossoverProbability, double elitismPercentage, int numOfGenerations) {

        populationSize = populationSize - (populationSize % 2); // each one should get a mate.
                                          //this is population size mod 2 (gets the remainder)
                                          //this makes population size an even number
        int numOfElites = (int) (populationSize*elitismPercentage);

        int[][] population = generatePopulation(n, populationSize); //generates specified amount of random setups

        int maxFitness = getMaxFitness(n);  //gets max fitness per specified population


        for (int x = 0; x < numOfGenerations; x++) {  //for all generations

            population = getSelectedPopulation(population); //get the best population out of all generations

            population = handleCrossovers(population, n, crossoverProbability);   //crossover random columns with eachother (by pairs of 2)

            for (int i = 0; i < populationSize; i++) {      //for all the population size
                if (getFitness(population[i]) == maxFitness) //check the fitness
                    return population[i];}                   //if we achieved max possible fitness return this board setup

            for (int i = 0; i <= populationSize-numOfElites; i++){ //for all population except elites
                population[i] = tryToMutate(population[i], mutationProbability); //try to mutate
                if (getFitness(population[i]) == maxFitness)  //if mutation gave best possible fitness
                    return population[i];                     //return this board setup

            }

        }

        return null;
    }

    private int[][] handleCrossovers(int[][] population, int n, double crossoverProbability) {
        for (int i = 0; i < population.length; i += 2) {  //every 2nd board setup
            if (satisfyProb(crossoverProbability)){
                int crossoverPos = (int) (Math.random() * n); //get random column form 0 to n

                for (int j = 0; j < crossoverPos; j++) {   //crosses over one random column setup with next
                    int tmp = population[i][j];            //board setup. Leaves everything else the same
                    population[i][j] = population[i+1][j]; //does it for all the population
                    population[i+1][j] = tmp;
                }
            }
        }
        return population;
    }

    private int[][] getSelectedPopulation(int[][] population) {
        Arrays.sort(population, Comparator.comparingInt(this::getFitness)); 

        return population;   //returns the population with best fitness
    }

    private int[] tryToMutate(int[] r, double mutationProbability) {
        if (satisfyProb(mutationProbability)) //if mutation is done
            r[(int)(Math.random()*r.length)] = (int)(Math.random()*r.length); //choose random point and change with another
                                                                              //random thing
        return r;
    }

    private boolean satisfyProb(double prob) { //mutation probability between 0-1
        return prob >= Math.random();  //returns if mutation is done or not (TRUE OR FALSE)
    }                                  //the closer the probability is to 1, the more likely it to be TRUE

    private int getFitness(int[] r) {
        return getMaxFitness(r.length) - SolverUtils.getHeuristicCost(r);  //gets the fitness by subtracting best possible by current
    }

    private int getMaxFitness(int n) {
        return n*(n-1)/2;  //almost linear function for getting the max fitness according to population size
    }                      //the bigger the population, the better max fitness can be

    private int[] generateChromosome(int n) {
        return SolverUtils.generateRandomState(n); //generates a random board setup
    }

    private int[][] generatePopulation(int n, int populationSize) {
        int[][] population = new int[populationSize][];
        for (int i = 0; i < populationSize; i++)
            population[i] = generateChromosome(n); //generate as much random setups as population size

        return population;
    }
}
