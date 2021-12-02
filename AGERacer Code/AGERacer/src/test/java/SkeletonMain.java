public class SkeletonMain {

    public static void main(String[] args) {
        // Change file with the desired values.
        fileHandler handler = new fileHandler(100, 30, 5);
        try {
            handler.changeFile();
        } catch(java.io.IOException e){
            
        }

        // Uncomment this section and comment the other one to create a Solo Game
        /* Solo Game
        SoloGameRunner gameRunner = new SoloGameRunner();
        // Agent1 agent = new Agent1(200, 4000, 50);
        // Sets the player
        gameRunner.setAgent(Agent1.class);
        // Sets a test case
        gameRunner.setTestCase("test0.json");
        */
        // Another way to add a player for python
        // gameRunner.addAgent("python3 /home/user/player.py");

        // Start the game server
        //gameRunner.start();
        // Simulate
        //gameRunner.simulate();
    }
}
