import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvSplitte {
    public static void main(String[] args) {
        // Path to the parent CSV file
        String parentFilePath = "C:\\Users\\Quynh Huong\\Downloads\\Data.csv";
        // Directory to save the child CSV files
        String outputDir = "C:\\Users\\Quynh Huong\\Downloads\\Path\\";

        splitCsvFile(parentFilePath, outputDir);
    }

    public static void splitCsvFile(String parentFilePath, String outputDir) {
        try (CSVReader reader = new CSVReader(new FileReader(parentFilePath))) {
            // Read all lines at once
            List<String[]> allLines = reader.readAll();

            // Check if the file has at least one line (header)
            if (allLines.isEmpty()) {
                System.out.println("The parent CSV file is empty.");
                return;
            }

            // Extract the header
            String[] header = allLines.get(0);

            // Iterate over each row starting from the second row
            for (int i = 1; i < allLines.size(); i++) {
                String[] currentRow = allLines.get(i);

                // Create a new CSV file for the current row
                File childFile = new File(outputDir + "child_" + i + ".csv");
                try (CSVWriter writer = new CSVWriter(new FileWriter(childFile))) {
                    // Write the header to the new file
                    writer.writeNext(header);
                    // Write the current row to the new file
                    writer.writeNext(currentRow);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }
}

