import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Csvfile {
    public static void main(String[] args) throws IOException, CsvException {
        String parentFile = "C:\\Users\\Quynh Huong\\Downloads\\Data.csv";
        String childFile = "C:\\Users\\Quynh Huong\\Downloads\\Path\\";
        CsvExport(parentFile,childFile);
    }
    public static void CsvExport(String cha, String con) throws IOException, CsvException {
        CSVReader red = new CSVReader(new FileReader(cha));

            List<String[]> allLine = red.readAll();
            String[] header = allLine.get(0);
            for (int i = 1; i < allLine.size(); i++) {
                String[] line = allLine.get(i);
                File conn = new File(con + "con" + i + ".csv");
                CSVWriter write = new CSVWriter(new FileWriter(conn));
                write.writeNext(header);
                write.writeNext(line);
            }
        }

        }


