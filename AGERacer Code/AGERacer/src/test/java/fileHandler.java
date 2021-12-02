import java.io.File;// Import the File class
import java.util.*;
import java.io.FileWriter;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class fileHandler {
    int [] values = new int[3];

    public fileHandler(int thrust_i, int distance, int thrust_f){
        this.values[0] = thrust_i;
        this.values[1] = distance;
        this.values[2] = thrust_f;
    }
    public void changeFile() throws java.io.IOException {
        try {
            // Path currentRelativePath = Paths.get("");
            //String s = currentRelativePath.toAbsolutePath().toString();
            File file = new File("/Users/nachoblascoalis/Documents/Geneticos/AGERacer Code/AGERacer/src/test/java/Agent1.java");
            Scanner myReader = new Scanner(file);
            List <String> data = new ArrayList<String>();
            boolean next = false;
            int counter = 0;

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                if (line.contains("// Change value.")) {
                    next = true;
                }
                else {
                    if (next == true) {
                        String value = line.replaceAll("[^0-9]", "");
                        line = line.replaceAll(value, String.valueOf(this.values[counter]));
                        counter += 1;
                    }
                    next = false;
                }
                data.add(line);
            }

            myReader.close();

            FileWriter myWriter = new FileWriter(file, false);
            for (String line:data) {
                myWriter.write(line + "\n");
            }
            myWriter.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
    }
}
}

