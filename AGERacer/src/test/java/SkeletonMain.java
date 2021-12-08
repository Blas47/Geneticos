
import com.codingame.gameengine.runner.SoloGameRunner;
import com.codingame.gameengine.runner.dto.GameResult;

import java.io.File;
import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class SkeletonMain {
    public static double[][] getExperiments() throws java.io.FileNotFoundException {
        // Path a los experimentos.
        Path path = Paths.get("config/experiments.txt");
        
        // Número de experimentos creados.
        int nExperiment = 0;
        try {
            nExperiment = (int) Files.lines(path).count() - 1;
        } catch (Exception e) {}
        
        // Extraer experimentos del archivo.
        File file = new File("config/experiments.txt");
        Scanner myReader = new Scanner(file);
        
        // Leer la cabecera.
        int nParams = 0; 
        if (myReader.hasNextLine()) {
            String [] data = myReader.nextLine().split(",");
            nParams = data.length;
        }

        // Variable para guardar todos los experimentos con sus parámetros.
        double[][] experiments = new double[nExperiment][nParams];
        
        // Leer experimentos.
        for (int i = 0; i < nExperiment; i++) {
            String [] data = myReader.nextLine().split(",");
            for(int j = 0; j < nParams; j++){
                experiments[i][j] = Double.parseDouble(data[j]);
            }
        }      

        myReader.close();

        return experiments;
    }

    private static Individual runExperiment(int nExperiment, int nEjecucion, int windowImprovements, double c, double umbral, int minVarianza, int maxVarianza, int intervalos) {
        // Vaciar fichero relacionado con la información del algoritmo.
        try {
            File file = new File("config/log" + nExperiment + "-" + nEjecucion + ".txt");
            FileWriter myWriter = new FileWriter(file, false);
            myWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        // Agente a ejecutar.
        int agent = 2;

        // Ejecutar algoritmo.
        Genetic example = new Genetic(windowImprovements, c, umbral, minVarianza, maxVarianza);
        Individual best = example.run(nExperiment, nEjecucion, agent, intervalos);

        System.out.println("MEJOR FIT OBTENIDO: " + best.fit);
        
        return best;
    }
    
    public static void main(String[] args)  {

        // Obtener todos los experimentos.
        double [][] experiments = new double[0][0] ;
        try{
            experiments= getExperiments();
        }catch(Exception e) {}

        // Veces que queremos correr los experimentos para evitar mínimos locales.
        int nVeces = 1;

        // Peor valor de fit que se puede obtener.
        double bestFit = 1000;
        for (int j = 0; j < experiments.length; j++) {
            System.out.println("EJECUTANDO El SET" + j + "DE EXPERIMENTOS");
            // Ejecutar experimentos.
            double bestFitExperiment = 1000;
            double meanFit = 0;
            for (int i = 0; i < nVeces; i++) {
                System.out.println("EJECUTANDO El SET" + j + "DE EXPERIMENTOS POR " + i + " VEZ");
                Individual best = runExperiment(j, i, (int) experiments[j][0], experiments[j][1], experiments[j][2], (int) experiments[j][3], (int) experiments[j][4], (int) experiments[j][5]);
                double fit = best.fit;
                bestFit = fit<bestFit?fit:bestFit;
                bestFitExperiment = fit<bestFitExperiment?fit:bestFitExperiment;
                meanFit += fit;
            }

            System.out.println("MEJOR FIT OBTENIDO DEl SET" + j + "DE EXPERIMENTOS: " + bestFitExperiment);
            meanFit = (meanFit/nVeces);
            System.out.println("MEDIA FIT OBTENIDO DE ESTE SET" + j + "DE EXPERIMENTOS: " + meanFit);
        }
        System.out.println("MEJOR FIT OBTENIDO DE TODOS LOS EXPERIMENTOS: " + bestFit);
    }
}
