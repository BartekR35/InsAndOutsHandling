import java.io.*;

public class FileToCopy {
    public static void main(String[] args) {
        // Input file
        String inputFile = "src/FileExc3";
        // Output file
        String outputFile = "FileExc3_copy";

        // Managing file resources by try
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            // Reading input file
            while ((line = reader.readLine()) != null) {
                // Replace " " with "-" and write to the output file
                writer.write(line.replace(" ", "-"));
                // Add a new line in the output file
                writer.newLine();
            }

            // Handling exceptions related to file operations
        } catch (IOException e) {
            System.err.println("An error occurred during file processing: " + e.getMessage());
        }
    }
}
