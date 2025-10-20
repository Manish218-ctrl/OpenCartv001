package utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class ExcelUtility {

    /**
     * Reads data from the specified sheet of an Excel file and returns it as a 2D Object array.
     * CRITICAL FIX: Uses ClassLoader to find the file from the classpath (e.g., src/test/resources).
     *
     * @param fileName The name of the Excel file (e.g., "SearchData.xlsx").
     * @param sheetName The name of the sheet to read (e.g., "ExistingProducts").
     * @return A 2D Object array containing the test data.
     */
    public static Object[][] getTestData(String fileName, String sheetName) {

        // Initialize streams and workbook to null for cleanup in finally block
        InputStream is = null;
        XSSFWorkbook workbook = null;
        Object[][] data = new Object[][] {}; // Default empty array

        try {
            // FIX: Use ClassLoader to read the file from the classpath (src/test/resources)
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

            if (is == null) {
                System.err.println("Error: Excel file '" + fileName + "' not found in the classpath (e.g., src/test/resources).");
                throw new RuntimeException("Excel file not found: " + fileName);
            }

            workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet(sheetName);

            // Handle non-existent sheet
            if (sheet == null) {
                System.err.println("Error: Sheet '" + sheetName + "' not found in the Excel file.");
                throw new IllegalArgumentException("Sheet not found: " + sheetName);
            }

            int rowCount = sheet.getLastRowNum(); // Total number of rows - 1 (since it's a 0-based index)

            // Check if there is at least a header row and one data row
            if (rowCount < 1) {
                // If last row index is 0, it means only the header exists or the file is empty.
                return data;
            }

            // Get the number of columns from the header row (index 0)
            int colCount = Objects.requireNonNull(sheet.getRow(0), "Excel sheet has no header row at index 0.").getLastCellNum();

            // Data array size is: [number of data rows (rows after header)] x [number of columns]
            data = new Object[rowCount][colCount]; // rowCount here is the number of data rows

            DataFormatter formatter = new DataFormatter();

            // Iterate through the data rows (starting from Excel row index 1)
            for (int i = 0; i < rowCount; i++) {
                XSSFRow row = sheet.getRow(i + 1); // Get the actual data row (e.g., row index 1, 2, 3...)

                // Handle null rows (e.g., blank rows in Excel)
                if (row == null) {
                    // Fill the current row in the data array with empty strings
                    for (int j = 0; j < colCount; j++) {
                        data[i][j] = "";
                    }
                    continue;
                }

                // Iterate through the columns/cells
                for (int j = 0; j < colCount; j++) {
                    XSSFCell cell = row.getCell(j);
                    String cellData = "";

                    if (cell != null) {
                        // Use DataFormatter to read any cell type (string, numeric, date) as a String
                        cellData = formatter.formatCellValue(cell);
                    }
                    data[i][j] = cellData;
                }
            }

        } catch (IOException e) {
            System.err.println("Failed to read Excel file: " + e.getMessage());
            e.printStackTrace();
            // Wrap the checked exception in a runtime exception for TestNG to handle
            throw new RuntimeException("Error reading Excel data from file: " + fileName, e);
        } finally {
            // CRITICAL: Close resources
            try {
                if (workbook != null) workbook.close();
                if (is != null) is.close();
            } catch (IOException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        return data;
    }
}
