import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelSplitter {
    public static void main(String[] args) {
        String parentFilePath = "C:\\Users\\Quynh Huong\\Downloads\\Data-automation-BasePrice.csv";
        String outputDir = "C:\\Users\\Quynh Huong\\Downloads\\Path\\";

        try (FileInputStream fis = new FileInputStream(new File(parentFilePath));
             Workbook parentWorkbook = new XSSFWorkbook(fis)) {

            Sheet parentSheet = parentWorkbook.getSheetAt(0);
            Row headerRow = parentSheet.getRow(0);

            // Iterate over each row, starting from the second row (index 1)
            for (int i = 1; i <= parentSheet.getLastRowNum(); i++) {
                Row currentRow = parentSheet.getRow(i);

                // Create a new workbook and sheet for the current row
                Workbook childWorkbook = new XSSFWorkbook();
                Sheet childSheet = childWorkbook.createSheet("Sheet1");

                // Copy the header row to the new sheet
                Row childHeaderRow = childSheet.createRow(0);
                copyRow(headerRow, childHeaderRow);

                // Copy the current row to the new sheet
                Row childRow = childSheet.createRow(1);
                copyRow(currentRow, childRow);

                // Write the new workbook to a file
                try (FileOutputStream fos = new FileOutputStream(new File(outputDir + "child_" + i + ".xlsx"))) {
                    childWorkbook.write(fos);
                }

                childWorkbook.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyRow(Row sourceRow, Row destinationRow) {
        for (int j = 0; j < sourceRow.getLastCellNum(); j++) {
            Cell sourceCell = sourceRow.getCell(j);
            Cell destinationCell = destinationRow.createCell(j);

            if (sourceCell != null) {
                switch (sourceCell.getCellType()) {
                    case STRING:
                        destinationCell.setCellValue(sourceCell.getStringCellValue());
                        break;
                    case NUMERIC:
                        destinationCell.setCellValue(sourceCell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        destinationCell.setCellValue(sourceCell.getBooleanCellValue());
                        break;
                    case FORMULA:
                        destinationCell.setCellFormula(sourceCell.getCellFormula());
                        break;
                    default:
                        destinationCell.setCellValue(sourceCell.toString());
                        break;
                }
            }
        }
    }
}

