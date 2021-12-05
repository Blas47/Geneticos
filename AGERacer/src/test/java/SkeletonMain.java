import com.codingame.gameengine.runner.SoloGameRunner;
import com.codingame.gameengine.runner.dto.GameResult;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class SkeletonMain {

    public static void main(String[] args) {
        // Vaciar fichero relacionado con la información del algoritmo.
        try {
            File file = new File("config/log.txt");
            FileWriter myWriter = new FileWriter(file, false);
            myWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        

        int WINDOW_IMPROVEMENTS = 1;
        double C = 0.817;
        Genetic example = new Genetic(WINDOW_IMPROVEMENTS, C);
        example.run();
    }
}
