import com.codingame.gameengine.runner.SoloGameRunner;

public class VisualTest {
    


    public static void main(String[] args) {
        // Uncomment this section and comment the other one to create a Solo Game
        /* Solo Game */
        SoloGameRunner gameRunner = new SoloGameRunner();
        // Sets the player
        gameRunner.setAgent(Agent2.class);
        // Sets a test case
        gameRunner.setTestCase("test9.json");

        // Another way to add a player for python
        // gameRunner.addAgent("python3 /home/user/player.py");

        // Start the game server
        gameRunner.start();
        // Simulate
        //gameRunner.simulate();
    }

}
