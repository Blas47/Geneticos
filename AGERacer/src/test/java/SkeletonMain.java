
import com.codingame.gameengine.runner.SoloGameRunner;
import com.codingame.gameengine.runner.dto.GameResult;



import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class SkeletonMain {

    public static void main(String[] args) {
        // Número de experimentos que se pretenden realizar.
        int nExperiment = 10;

        // Veces que queremos correr los experimentos para evitar minimos locales

        int nVeces = 1;
        // Parámetros del algoritmo.
        int windowImprovements = 5;
        int minVarianza = 0;
        int maxVarianza = 200;
        double c = 0.817;
        double umbral = 0.001;
        double bestFit = 1000;
        
        double fit = runExperiment(0, windowImprovements, c, umbral, minVarianza, maxVarianza);
        System.out.println("MEJOR FIT OBTENIDO DE TODOS LOS EXPERIMENTOS: " + bestFit);
        
        // for(int j = 0; j= nVeces; j++) {
        //     // Ejecutar experimentos.
        //     for (int i = 0; i < nExperiment; i++) {
                
        //         double fit = runExperiment(i, windowImprovements, c, umbral, minVarianza, maxVarianza);
        //         bestFit = fit<bestFit?fit:bestFit;
                
        //     }

        //     System.out.println("MEJOR FIT OBTENIDO DE TODOS LOS EXPERIMENTOS: " + bestFit);
        // }
        
    }

    private static double runExperiment(int nExperiment, int windowImprovements, double c, double umbral, int minVarianza, int maxVarianza) {
        System.out.println("Corriendo experimento: " + nExperiment);
        // Vaciar fichero relacionado con la información del algoritmo.
        try {
            File file = new File("config/log.txt");
            FileWriter myWriter = new FileWriter(file, false);
            myWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }

      
        // Ejecutar algoritmo.
        Genetic example = new Genetic(windowImprovements, c, umbral, minVarianza, maxVarianza);
        
        //SI QUEREMOS AGENT 1 PONER INTERVALOS = 1
        // Agente a ejecutar.
        int agent = 2;
        int intervalos = 4;
        Individual best = example.run(agent, intervalos);
        System.out.println("MEJOR FIT OBTENIDO: " + best.fit);
        
        return best.fit;
    }
}
