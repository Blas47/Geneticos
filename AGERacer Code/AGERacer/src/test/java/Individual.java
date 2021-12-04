import com.codingame.gameengine.runner.SoloGameRunner;
import com.codingame.gameengine.runner.dto.GameResult;

import java.util.ArrayList;
import java.util.Random;

public class Individual {
    // Parameters of an individual.
    public double [] values, variances;
    public double fit;

    // Initialize a new individual in relation to the values of the father.
    public Individual (double [] values, double[] variances) {
        // Same variances.
        this.variances = variances;
        this.values = values;

        // Set new values.
        Random r = new Random();
        for( int i = 0; i < this.values.length; i++){
            this.values[i] += r.nextGaussian() * this.variances[i];
        }
        changeFile(this.values);
        this.fit = obtainFit();
    }

    // Initialize the individual with random numbers either in the values and variances.
    public Individual(int sizeIndividual) {
        // Initialize the arrays.
        this.values = new double[sizeIndividual];
        this.variances = new double[sizeIndividual];

        // Set random values.
        for (int i = 0; i < sizeIndividual; i++) {
            this.values[i] = Math.random() * 18359;
            this.variances[i] = Math.random() * 2500;
        }
    }

    // Function to change the file of the agent.
    public void changeFile(double[] values) {
        // Change file with the desired values.
        FileHandler handler = new FileHandler(values);
        try {
            handler.changeFile();
        } catch(java.io.IOException e){}
    }

    // Function to obtain the fit of the individual compiling the game.
    public double obtainFit() {
        // Compile the game.
        SoloGameRunner gameRunner = new SoloGameRunner();

        // Set the player.
        gameRunner.setAgent(Agent1.class);
        //gameRunner.addAgent(Agent1.class);

        // Set a test case.
        gameRunner.setTestCase("test0.json");

        // Simulate the game.
        GameResult sim = gameRunner.simulate();

        // Get result from the output.
        String res = sim.metadata.split(":\"")[1].split("\"}")[0];

        // Transform the result to a double number.
        double result = Double.parseDouble(res);

        return result;
    }

}
