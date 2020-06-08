import java.util.Random;
//Main class
public class EjemploAG {

    Poblacion population = new Poblacion();
    Individuo fittest;
    Individuo secondFittest;
    int generationCount = 0;

    public static void main(String[] args) {

        Random rn = new Random();

        EjemploAG demo = new EjemploAG();

        //Initialize population
        demo.population.inicializarPob(1000);

        //Calculate fitness of each individual
        demo.population.calculateFitness();

        System.out.println("Generacion: " + demo.generationCount + " Puntaje: " + demo.population.fittest);
        System.out.print("Genes: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(demo.population.getFittest().genes[i]);
        }
        System.out.println("");
        //While population gets an individual with maximum fitness
        while (demo.population.fittest < 5) {
            ++demo.generationCount;

            //Do selection
            demo.seleccion();

            //Do crossover
            demo.cruce();

            //Do mutation under a random probability
            if (rn.nextInt()%7 < 5) {
                demo.mutacion();
            }

            //Add fittest offspring to population
            demo.aniadirHijo();

            //Calculate new fitness value
            demo.population.calculateFitness();

            System.out.println("Generacion: " + demo.generationCount + " Puntaje: " + demo.population.fittest);
            System.out.print("Genes: ");
            for (int i = 0; i < 5; i++) {
                System.out.print(demo.population.getFittest().genes[i]);
            }
            System.out.println("");
        }

        System.out.println("\nSolucion encontrada en la generacion " + demo.generationCount);
        System.out.println("Generacion: " + demo.generationCount + " Puntaje: " + demo.population.fittest);
        System.out.print("Genes: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(demo.population.getFittest().genes[i]);
        }
        System.out.println("");

    }

    //Selection
    void seleccion() {

        //Select the most fittest individual
        fittest = population.getFittest();

        //Select the second most fittest individual
        secondFittest = population.getSecondFittest();
    }

    //Crossover
    void cruce() {
        Random rn = new Random();

        //Select a random crossover point
        int puntoCruce = rn.nextInt(population.individuals[0].geneLength);

        //Swap values among parents
        for (int i = 0; i < puntoCruce; i++) {
            int temp = fittest.genes[i];
            fittest.genes[i] = secondFittest.genes[i];
            secondFittest.genes[i] = temp;

        }

    }

    //Mutation
    void mutacion() {
        Random rn = new Random();

        //Select a random mutation point
        int puntoMutacion = rn.nextInt(population.individuals[0].geneLength);

        //Flip values at the mutation point
        if (fittest.genes[puntoMutacion] == 0) {
            fittest.genes[puntoMutacion] = 1;
        } else {
            fittest.genes[puntoMutacion] = 0;
        }

        puntoMutacion = rn.nextInt(population.individuals[0].geneLength);

        if (secondFittest.genes[puntoMutacion] == 0) {
            secondFittest.genes[puntoMutacion] = 1;
        } else {
            secondFittest.genes[puntoMutacion] = 0;
        }
    }

    //Get fittest offspring
    Individuo hijoMasApto() {
        if (fittest.fitness > secondFittest.fitness) {
            return fittest;
        }
        return secondFittest;
    }


    //Replace least fittest individual from most fittest offspring
    void aniadirHijo() {

        //Update fitness values of offspring
        fittest.calcFitness();
        secondFittest.calcFitness();

        //Get index of least fit individual
        int leastFittestIndex = population.getLeastFittestIndex();

        //Replace least fittest individual from most fittest offspring
        population.individuals[leastFittestIndex] = hijoMasApto();
    }

}


//Individual class
class Individuo {

    int fitness = 0;
    int[] genes = new int[5];
    int geneLength = 5;

    public Individuo() {
        Random rn = new Random();

        //Set genes randomly for each individual
        for (int i = 0; i < genes.length; i++) {
            genes[i] = Math.abs(rn.nextInt() % 2);
        }

        fitness = 0;
    }

    //Calculate fitness
    public void calcFitness() {

        fitness = 0;
        for (int i = 0; i < 5; i++) {
            if (genes[i] == 1) {
                ++fitness;
            }
        }
    }

}

//Population class
class Poblacion {

    int popSize = 10;
    Individuo[] individuals = new Individuo[10];
    int fittest = 0;

    //Initialize population
    public void inicializarPob(int size) {
        for (int i = 0; i < individuals.length; i++) {
            individuals[i] = new Individuo();
        }
    }

    //Get the fittest individual
    public Individuo getFittest() {
        int maxFit = Integer.MIN_VALUE;
        int maxFitIndex = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (maxFit <= individuals[i].fitness) {
                maxFit = individuals[i].fitness;
                maxFitIndex = i;
            }
        }
        fittest = individuals[maxFitIndex].fitness;
        return individuals[maxFitIndex];
    }

    //Get the second most fittest individual
    public Individuo getSecondFittest() {
        int maxFit1 = 0;
        int maxFit2 = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (individuals[i].fitness > individuals[maxFit1].fitness) {
                maxFit2 = maxFit1;
                maxFit1 = i;
            } else if (individuals[i].fitness > individuals[maxFit2].fitness) {
                maxFit2 = i;
            }
        }
        return individuals[maxFit2];
    }

    //Get index of least fittest individual
    public int getLeastFittestIndex() {
        int minFitVal = Integer.MAX_VALUE;
        int minFitIndex = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (minFitVal >= individuals[i].fitness) {
                minFitVal = individuals[i].fitness;
                minFitIndex = i;
            }
        }
        return minFitIndex;
    }

    //Calculate fitness of each individual
    public void calculateFitness() {

        for (int i = 0; i < individuals.length; i++) {
            individuals[i].calcFitness();
        }
        getFittest();
    }

}
