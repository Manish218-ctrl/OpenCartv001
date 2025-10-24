package utilities;
/*package utilities;

import org.testng.annotations.DataProvider;
import java.io.IOException;

import utilities.ExcelUtility;

public class DataProviders {


    // DataProvider 1: Login Data (Reads from Excel)
    @DataProvider(name="LoginData")
    public String [][] getData() throws IOException
    {
        // NOTE: Ensure the ExcelUtility class is correctly implemented to read test data.
        String path=".\\testData\\TC_RF_011_ValidateAccountRegistrationDataDrivenTest(TestData).xlsx";

        ExcelUtility xlutil = new ExcelUtility(path);

        int totalrows = xlutil.getRowCount("Sheet1");
        int totalcols = xlutil.getCellCount("Sheet1", 1);

        String logindata[][] = new String[totalrows][totalcols];

        for(int i = 1; i <= totalrows; i++)
        {
            for(int j = 0; j < totalcols; j++)
            {
                logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j);
            }
        }
        return logindata;
    }

    // DataProvider 2: Currency Test Data
    @DataProvider(name="CurrencyData")
    public Object[][] getCurrencyData() {
        return new Object[][] {
                {"Euro"},
                {"Pound Sterling"},
                {"US Dollar"}
        };
    }

    // ---------------------------------------------------------------------------------

    // DataProvider 3: Search Test Data (Based on TC_SF_001, TC_SF_002, etc. from Search.csv)
    /**
     * Provides test data for search functionality, including valid, invalid, and partial searches.
     * Columns: {Search Term}, {Expected Product Title or "No product"}
     */
  /*  @DataProvider(name="SearchData")
    public Object[][] getSearchData() {
        return new Object[][] {
                // Valid searches (Expected Result is the main product title)
                {"iMac", "iMac"},
                {"Samsung", "Samsung SyncMaster 941BW"},

                // Invalid/Non-existing product searches (Expected Result is a flag for "no result" check)
                {"Fitbit", "No product"},
                {"XYZ123", "No product"},
                {"", "No product"} // Empty search, same expected result as non-existing product
        };
    }*/

    // ---------------------------------------------------------------------------------

    // DataProvider 4: Contact Us Form Data (Based on TC_CU_004 from Contact Us.csv)
    /**
     * Provides test data for successful submission of the Contact Us form.
     * Columns: {Your Name}, {E-Mail Address}, {Enquiry}, {Expected Success Message Text}
     */
   /* @DataProvider(name="ContactUsData")
    public Object[][] getContactUsData() {
        // Note: For simplicity, this provides only valid data. Negative cases (missing fields)
        // would require additional data sets and a test method to verify error messages.
        return new Object[][] {
                {"John Doe", "john.doe.contact@testmail.com", "I have a question about my recent order #1001.", "Your Enquiry has been successfully sent!"},
                {"Jane Smith", "jane.smith.inquiry@other.net", "Need technical support on the product model X.", "Your Enquiry has been successfully sent!"}
        };
    }
}*/





import org.testng.annotations.DataProvider;
import utilities.ExcelUtility; // Assuming ExcelUtility is in a 'utilities' package

public class DataProviders {

    /**
     * Data Provider method to fetch existing product search queries and expected titles
     * from the "ExistingProducts" sheet of the "SearchData.xlsx" file.
     * The method must be static to be referenced by the @Test method.
     * @return 2D Object array containing {searchQuery, expectedProductTitle}
     */
    @DataProvider(name="SearchExistingProductData")
    public static Object[][] getSearchExistingProductData()
    {
        // Define your Excel file name and sheet name
        final String EXCEL_FILE_NAME = "SearchData.xlsx";
        final String SHEET_NAME = "ExistingProducts";

        // The data returned should be: {searchQuery, expectedProductTitle}
        Object[][] data = ExcelUtility.getTestData(EXCEL_FILE_NAME, SHEET_NAME);
        return data;
    }
}