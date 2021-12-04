import com.codingame.gameengine.runner.SoloGameRunner;
import com.codingame.gameengine.runner.dto.GameResult;

public class Genetic {

    public int rule1_5() {
        return 0;
    }

    public void run() {
        Individual father = new Individual(3);
        // WHILE
        Individual child = new Individual (father.values, father.variances);
    }
}
