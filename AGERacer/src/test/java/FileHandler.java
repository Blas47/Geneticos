import java.io.File;// Import the File class
import java.io.FileWriter;
import java.io.FileNotFoundException;  // Import this class to handle errors

public class FileHandler {
    double [] values = new double[3];

    public FileHandler (double [] values){
        this.values = values;
    }

    public void changeFile() throws java.io.IOException {
        try {
            // Path currentRelativePath = Paths.get("");
            //String s = currentRelativePath.toAbsolutePath().toString();
            File file = new File("AGERacer/config/conduccion.txt");
            FileWriter myWriter = new FileWriter(file, false);

            // Change the values.
            for (double value:values) {
                myWriter.write(value + "\n");
            }
            myWriter.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}


