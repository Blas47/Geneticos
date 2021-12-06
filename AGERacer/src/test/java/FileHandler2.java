import java.io.File;// Import the File class
import java.io.FileWriter;
import java.io.FileNotFoundException;  // Import this class to handle errors

public class FileHandler2 {
    double [] values = new double[3];

    public FileHandler2 (double [] values){
        this.values = values;
    }

    public void changeFile() throws java.io.IOException {
        try {
            // Path currentRelativePath = Paths.get("");
            //String s = currentRelativePath.toAbsolutePath().toString();
            File file = new File("config/conduccionAgente1.txt");
            FileWriter myWriter = new FileWriter(file, false);
            int counter = 1;

            // Change the values.
            for (double value:values) {
                myWriter.write(value + " ");
                if (counter == 3) {
                    myWriter.write("\n");
                    counter = 0;
                }
                counter++;
            }
            myWriter.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}


