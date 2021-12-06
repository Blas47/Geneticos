import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

public class Agent2 {
    // Función para obtener los valores del fichero.
    public static double[][] getParams() throws java.io.FileNotFoundException {
        // Leer el fichereo donde estan guardados los  valores esenciales para el estilo de conducción del agente.
        File file = new File("config/conduccion.txt");
        Scanner myReader = new Scanner(file);
        double[][] values = new double[4][3];
        
        // Guardar en una lista los valores.
        while (myReader.hasNextLine()) {
            String [] data = myReader.nextLine().split(" ");
            
            for (int i = 0; i < values.length; i++) {
                for (int j = 0; j < values[i].length; j++) {
                    values[i][j] = Double.parseDouble(data[j]);
                }
            }
        }

        myReader.close();
        Arrays.sort(values, Comparator.comparingDouble(o -> o[0]));

        return values;
    }

    // Función para obtener la distancia entre dos puntos.
    public static double distance (Point p1, Point p2) {
            return Math.sqrt(Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2));
    }
        
    /*
        Función para obtener el ángulo entre la posición actual y los dos siguientes puntos.
        Punto 1 (P1): vértice del triángulo (punto objetivo).
        Punto 2 (P2): posición actual de la nave.
        Punto 3 (P3): punto siguiente al objetivo actual.
    */
    public static double obtainAngle (Point current, Point targ, Point next_targ) {
        double distance_1_2 = distance(targ, current);
        double distance_1_3 = distance(targ, next_targ);
        double distance_2_3 = distance(current, next_targ);

        double angle = ((Math.pow(distance_1_2, 2) + Math.pow(distance_1_3, 2) - Math.pow(distance_2_3, 2)) 
                    / (2 * distance_1_2 * distance_1_3));
        
        angle = Math.toDegrees(Math.acos(angle));

        return angle;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int checkpoints = Integer.parseInt(scanner.nextLine());

        // Puntos por los que tiene que pasar el agente.
        ArrayList<Point> targets = new ArrayList<>();
        for (int i = 0; i < checkpoints; i++) {
            String[] line = scanner.nextLine().split(" ");
            System.err.println(line[0] + " " + line[1]);
            targets.add(new Point(Integer.parseInt(line[0]), Integer.parseInt(line[1])));
        }

        // Obtener valores que definen el agente.
        double [][] values = new double[4][3];
        try {
            values = getParams();
        } catch (java.io.FileNotFoundException e) {}

        // Bucle del recorrido.
        while (true) {
            // Obtener parámetros del turno.
            String s = scanner.nextLine();
            System.err.println(s);
            String[] input = s.split(" ");
            
            int target = Integer.parseInt(input[0]);
            int x = Integer.parseInt(input[1]);
            int y = Integer.parseInt(input[2]);
            int vx = Integer.parseInt(input[3]);
            int vy = Integer.parseInt(input[4]);
            int angle = Integer.parseInt(input[5]);

            // Obtener objetivo y el punto siguiente a éste.
            Point targ = targets.get(target);
            Point next_targ;
            
            if (!targ.equals(targets.get(targets.size() - 1))) {
                next_targ = targets.get(target + 1);
            } else {
                next_targ = targets.get(0);
            }

            // Obtener posicion actual.
            Point current = new Point(x, y);
            
            // Obtener ángulo del circuito.
            double angleCircuit = obtainAngle(current, targ, next_targ);
            
            // Si los valores son correctos, ejecutar instrucciones.
            if (values[0][0] != 0.0) {
                // Asignar la velocidad maxima por defecto
                int thrust = 200;
                
                // Decidir la distancia de frenado y velocidad segun angulo.
                for (int i = 0; i < values.length; i++) {
                    // Comprobar el angulo.
                    if ((angleCircuit%181)< values[i][0]) {
                        // Comprobar la distancia de frenado.
                        if (targ.distance(current) < values[i][1]) {
                            // Obtener potencia de frenado.
                            thrust = (int) values[i][2];
                        }
                        break;
                    }
                }

                // Forzar que la potencia sea menor que 200 con el módulo.
                thrust = thrust % 201;
            
                // Mandar las instrucciones con mensajes de los angulos.
                System.out.println(targ.x + " " + targ.y + " " + thrust + " anguloTotal " + angle + " anguloCircuito " + angleCircuit); // X Y THRUST MESSAGE
            }
        }
    }

    private static class Point{
        public int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        double distance(Point p) {
            return Math.sqrt((this.x - p.x) * (this.x - p.x) + (this.y - p.y) * (this.y - p.y));
        }
    }
}
