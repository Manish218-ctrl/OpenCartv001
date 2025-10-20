package testCases.TS_010_Currencies;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CurrencyPage;
import testBase.BaseClass;

public class TC_CR_001_ValidateCurrencySelectionEuroFunctionalityTest extends BaseClass {

    // Initialize the logger
    private static final Logger logger = LogManager.getLogger(TC_CR_001_ValidateCurrencySelectionEuroFunctionalityTest.class);

    @Test
    public void validateCurrencySelection() {
        logger.info("Test Case TC_CR_001 - Currency Selection for Euro Started");

        try {
            // Open the application URL
            logger.info("Opening the application URL: " + rb.getString("appURL"));
            driver.get(rb.getString("appURL"));

            // Initialize the CurrencyPage object
            CurrencyPage currencyPage = new CurrencyPage(driver);

            // Step 1: Click the currency dropdown
            logger.info("Clicking on the 'Currency' dropdown");
            currencyPage.clickCurrencyDropdown();

            // Step 2: Select Euro currency
            logger.info("Selecting 'Euro' from the currency options");
            currencyPage.selectEuroCurrency();

            // Step 3: Validate that Euro is selected
            logger.info("Validating that 'Euro' currency is selected");
            String selectedCurrency = driver.findElement(By.xpath("/html/body/nav/div/div[1]/form/div/button/strong")).getText();
            Assert.assertEquals(selectedCurrency, "€", "Currency selection did not change to Euro");

            logger.info("Test Case TC_CR_001 - Currency Selection for Euro Completed Successfully");

        } catch (Exception e) {
            // If an exception is thrown during the test, log the error
            logger.error("Test Case TC_CR_001 - Error occurred during Currency Selection Test: " + e.getMessage());
            Assert.fail("Test failed due to an error: " + e.getMessage());
        }
    }
}
