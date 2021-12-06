import com.codingame.gameengine.runner.SoloGameRunner;
import com.codingame.gameengine.runner.dto.GameResult;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Genetic {
    public boolean [] history;
    public int generations, ventanaExitos, minVarianza, maxVarianza;
    public double c, umbral, executionTime;
    
    // Constructor con los parámetros más relevantes del algoritmo genético.
    public Genetic( int ventanaExitos, double c, double umbral, int minVarianza, int maxVarianza) {
        this.history = new boolean[ventanaExitos];
        this.ventanaExitos = ventanaExitos;
        this.c = c;
        this.umbral = umbral;
        this.minVarianza = minVarianza;
        this.maxVarianza = maxVarianza;

        this.generations = 0;
        this.executionTime = 0;
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
            myWriter.write("\n" + this.history.length + "," + this.c + "," + this.umbral + "," + this.minVarianza
                            + "," + this.maxVarianza + "," + this.executionTime + "," + this.generations + ","
                            + Arrays.toString(best.values) + "," + Arrays.toString(best.variances) + "," + best.fit);
            myWriter.close();
        } catch (Exception e) {}
    }

    public Individual run(int agent, int intervalos) {
        // Obtener longitud del vector de valores del algoritmo.
        int size = intervalos * 3;

        long startTime = System.nanoTime();

        // Inicializar al primer individuo.
        Individual father = new Individual(size, agent, this.minVarianza, this.maxVarianza);

        // Ejecutar el algoritmo para n generaciones.
        while(this.generations < 20) {
            Individual child = new Individual (father.values, father.variances, agent);

            if (child.fit < father.fit) {
                this.history[(this.generations%this.history.length)] = true;
                father = new Individual(child);
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
