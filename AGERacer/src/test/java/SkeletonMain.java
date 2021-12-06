
import com.codingame.gameengine.runner.SoloGameRunner;
import com.codingame.gameengine.runner.dto.GameResult;



import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class SkeletonMain {

    public static void main(String[] args) {
        // Parámetros del algoritmo.
        int WINDOW_IMPROVEMENTS = 5;
        double C = 0.817;
        int agent = 2;

        // Vaciar fichero relacionado con la información del algoritmo.
        try {
            File file = new File("config/log.txt");
            FileWriter myWriter = new FileWriter(file, false);
            myWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        // Ejecutar algoritmo.
        Genetic example = new Genetic(WINDOW_IMPROVEMENTS, C);
        Individual best = example.run(agent);
        System.out.println("MEJOR FIT OBTENIDO: " + best.fit);
    }
}
