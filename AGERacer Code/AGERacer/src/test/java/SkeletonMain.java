import com.codingame.gameengine.runner.SoloGameRunner;
import com.codingame.gameengine.runner.dto.GameResult;

public class SkeletonMain {

    // Function to change the file of the agent.
    public static void changeFile(int thrust_i, int distance, int thrust_f) {
        // Change file with the desired values.
        fileHandler handler = new fileHandler(thrust_i, distance, thrust_f);
        try {
            handler.changeFile();
        } catch(java.io.IOException e){}
    }

    // Function to compile the game with the agent.
    public static double compile() {
        // Compile the game.
        SoloGameRunner gameRunner = new SoloGameRunner();

        // Set the player.
        gameRunner.setAgent(Agent1.class);

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

    public static void main(String[] args) {
        // Change the file of the agent with the desired values.
        changeFile(200, 50, 100);

        // Obtain the fitness value.
        double fitness = compile();
        System.out.print(fitness);







    }
}
