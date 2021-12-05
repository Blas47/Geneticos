import com.codingame.gameengine.runner.SoloGameRunner;
import com.codingame.gameengine.runner.dto.GameResult;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Genetic {
    public boolean [] history;
    public int generations;
    public double c, executionTime;
    
    // Constructor con los parámetros más relevantes del algoritmo genético.
    public Genetic(int windowImprovements, double c) {
        this.history = new boolean[windowImprovements];
        this.generations = 0;
        this.executionTime = 0;
        this.c = c;
    }
    
    // Regla de 1/5 para modificar las varianzas del individuo.
    public Individual rule1_5(Individual father) {
        // Comprobar que hay suficientes generaciones. 
        if (this.history.length <= this.generations) {
            int counter = 0;

            // Contar los éxitos de las últimas generaciones.
            for (boolean success:this.history) {
                if (success) counter++;
            }
            
            // Calcular ratio de mejoras.
            counter = counter / this.history.length;
            
            // Aplicar la regla de 1/5 para conseguir las nuevas varianzas.
            if (counter < 0.2) {
                for (int i = 0; i < father.variances.length; i++) {
                    father.variances[i] = father.variances[i] * this.c;
                }
            }
            else if (counter > 0.2) {
                for (int i = 0; i < father.variances.length; i++) {
                    father.variances[i] = father.variances[i] / this.c;
                }
            }
        }

        return father;
    }

    public void logInformation (Individual father) {
        File file = new File("config/log.txt");
        
        try{
            FileWriter myWriter = new FileWriter(file, true);
            myWriter.write("Generacion " + this.generations + "\n");
            myWriter.write(father.toString());
            myWriter.close();
        } catch (Exception e) {}
    }


    public void logResult (Individual best) {
        File file = new File("config/results.csv");
        
        try{
            FileWriter myWriter = new FileWriter(file, true);
            myWriter.write("\n" + this.executionTime + "," + this.generations + "," + this.history.length 
                                + "," + this.c + "," + Arrays.toString(best.values) + ","
                                + Arrays.toString(best.variances) + "," + best.fit);
            myWriter.close();
        } catch (Exception e) {}
    }

    public Individual run() {
        long startTime = System.nanoTime();

        
        // Inicializar al primer individuo.
        Individual father = new Individual(3);

        // Ejecutar el algoritmo para n generaciones.
        while(this.generations < 10) {
            Individual child = new Individual (father.values, father.variances);
            
            if (child.fit < father.fit) {
                this.history[(this.generations%this.history.length)] = true;
                father = child;
            } else {
                this.history[(this.generations%this.history.length)] = false;
            }
            
            father = rule1_5(father);
            this.generations++;
            logInformation(father);
            
        }
        long endTime   = System.nanoTime();
        this.executionTime = endTime - startTime;
        logResult(father);
        return father;
    }
}
