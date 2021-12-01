import java.io.File;// Import the File class
import java.util.*;
import java.io.FileWriter;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class readFile {
    public static void main(String[] args) throws java.io.IOException {
        try {
            // Path currentRelativePath = Paths.get("");
            //String s = currentRelativePath.toAbsolutePath().toString();
            File file = new File("/Users/nachoblascoalis/Documents/Geneticos/AGERacer Code/AGERacer/src/test/java/Agent1.java");
            Scanner myReader = new Scanner(file);
            List <String> data = new ArrayList<String>();

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                data.add(line);
            }
            myReader.close();
            // System.out.println(data.get(10));

            /*
            dataFileWriter myWriter = new FileWriter(file, false);
            myWriter.write(data);
            myWriter.close();
            */

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
