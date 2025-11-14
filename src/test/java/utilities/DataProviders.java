package utilities;

import org.testng.annotations.DataProvider;
import utilities.ExcelUtility; 

public class DataProviders {

    
    @DataProvider(name="SearchExistingProductData")
    public static Object[][] getSearchExistingProductData()
    {
        final String EXCEL_FILE_NAME = "SearchData.xlsx";
        final String SHEET_NAME = "ExistingProducts";

        Object[][] data = ExcelUtility.getTestData(EXCEL_FILE_NAME, SHEET_NAME);
        return data;
    }
}
