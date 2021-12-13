import com.codingame.gameengine.runner.SoloGameRunner;
import com.codingame.gameengine.runner.dto.GameResult;

import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class Individual {
    // Parameters of an individual.
    public double [] values, variances;
    public double fit;

    // Initialize the individual with random numbers both in the values and variances.
    public Individual(int sizeIndividual, int agente, int minVarianza, int maxVarianza) {
        // Initialize the arrays.
        
        this.values = new double[sizeIndividual];
        this.variances = new double[sizeIndividual];

        // Set random values.
        for (int i = 0; i < sizeIndividual; i++) {
            this.values[i] = Math.abs(Math.random() * 9000);
            //Math.random()*6 + 1   // Esto da valores entre 1.0 y 7.0 excluido el 7.0
            this.variances[i] = Math.random() * maxVarianza + minVarianza;
        }

        changeFile(this.values, agente);
        this.fit = obtainFit(agente);
    }

    // Initialize a new individual in relation to the values of the father.
    public Individual (double [] values, double[] variances, int agente) {
        // Same variances.
        this.values = Arrays.copyOf(values, values.length);
        this.variances = Arrays.copyOf(variances, variances.length);

        // Set new values.
        Random r = new Random();
        for( int i = 0; i < this.values.length; i++){
            this.values[i] += r.nextGaussian() * this.variances[i];
            this.values[i] = Math.abs(this.values[i]);
        }

        changeFile(this.values, agente);
        this.fit = obtainFit(agente);
    }

    public Individual (Individual child) {
        this.values = Arrays.copyOf(child.values, child.values.length);
        this.variances = Arrays.copyOf(child.variances, child.variances.length);
        this.fit = child.fit;
    }

    // Function to change the file of the agent.
    public void changeFile(double[] values, int agente) {
        switch (agente) {
                case 1: FileHandler1 handler1 = new FileHandler1(values);
                // Change file with the desired values.
                try {
                    handler1.changeFile();
                } catch(java.io.IOException e){}
                break;
                case 2: FileHandler2 handler2 = new FileHandler2(values);
                // Change file with the desired values.
                try {
                    handler2.changeFile();
                } catch(java.io.IOException e){}
                break;
            }
    }
    
    //Override to strin method
    public String toString() {
        return  "Values: " + Arrays.toString(this.values)+ "\n" +
                "Variances: " + Arrays.toString(this.variances) + "\n" +
                "Fit: " + this.fit + "\n-----------------------------------------------------------------------\n";  
       }  

    // Function to obtain the fit of the individual compiling the game.
    public double obtainFit(int agente) {
        int maps = 7;
        double result = 0;
        for (int i = 0; i < maps; i++) {
            // Compile the game.
            SoloGameRunner gameRunner = new SoloGameRunner();
            
            switch (agente){
                case 1: gameRunner.setAgent(Agent1.class);
                break;
                case 2: gameRunner.setAgent(Agent2.class);
                break;
            }

            // Set a test case.
            gameRunner.setTestCase("test"+i+".json");

            // Simulate the game.
            GameResult sim = gameRunner.simulate();

            // Get result from the output.
            String res = sim.metadata.split(":\"")[1].split("\"}")[0];

            // Transform the result to a double number.
            result += Double.parseDouble(res);
        }

        return result/maps;
    }

}
