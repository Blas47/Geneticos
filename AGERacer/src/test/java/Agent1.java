import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Agent1 {
    public static ArrayList<Double> getParams() throws java.io.FileNotFoundException{
        ArrayList <Double> values = new ArrayList<>();
        //leer el fichereo donde estan guardados los  valores esenciales para el estilo de conducción del agente
        File file = new File("config/conduccion.txt");
        Scanner myReader = new Scanner(file);
        //guardar en una lista los valores 
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            values.add(Double.parseDouble(data));
        }

        myReader.close();

        return values;
    }

    // El agente tiene dos velocidades entre las que elige según la distancia a la que esté del siguiente punto
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int checkpoints = Integer.parseInt(scanner.nextLine());
        ArrayList<Point> targets = new ArrayList<>();
        for(int i = 0; i < checkpoints; i++){
            String[] line = scanner.nextLine().split(" ");
            System.err.println(line[0] + " " + line[1]);
            targets.add(new Point(Integer.parseInt(line[0]), Integer.parseInt(line[1])));
        }
        double dist = 100000.0;
        int z = 0;
        while (true) {
            String s = scanner.nextLine();
            System.err.println(s);
            String[] input = s.split(" ");
            // id x y vx vy angle
            int target = Integer.parseInt(input[0]);
            int x = Integer.parseInt(input[1]);
            int y = Integer.parseInt(input[2]);
            int vx = Integer.parseInt(input[3]);
            int vy = Integer.parseInt(input[4]);

            // Obtener próximo punto
            Point targ = targets.get(target);
            // Array para los valores
            ArrayList<Double> values = new ArrayList<>();
            // Obtener posicion actual
            Point current = new Point(x, y);

            // Leer los valores escritos en el fichero
            try {
                values = getParams();
            } catch (java.io.FileNotFoundException e) {

            }

            // Si se leen correctamente los valores enviar las instrucciones
            if (!values.isEmpty()) {
                // Obtener la la potencia alta
                double value = values.get(0);
                int thrust = (int) value;

                // Comprobar la distancia de frenado
                if (targ.distance(current) < values.get(1)) {
                    // Obtener potencia de frenado
                    value = values.get(2);
                    thrust = (int) value;
                }
                // Forzar que la potencia sea menor que 200 con el módulo
                thrust = thrust % 200;
                // Mandar las instrucciones
                System.out.println(targ.x + " " + targ.y + " " + thrust + " Agent 1"); // X Y THRUST MESSAGE
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