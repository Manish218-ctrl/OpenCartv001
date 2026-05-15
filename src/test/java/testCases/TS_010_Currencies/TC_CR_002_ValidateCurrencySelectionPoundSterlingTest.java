package testCases.TS_010_Currencies;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CurrencyPage;
import testBase.BaseClass;

public class TC_CR_002_ValidateCurrencySelectionPoundSterlingTest extends BaseClass {

    @Test
    public void validateCurrencySelectionPoundSterling() {

        logger.info(
                "Test Case TC_CR_002 - Currency Selection for Pound Sterling Started"
        );

        try {

            logger.info(
                    "Opening the application URL: "
                            + p.getProperty("appURL")
            );

            getDriver().get(
                    p.getProperty("appURL")
            );

            CurrencyPage currencyPage =
                    new CurrencyPage(getDriver());

            logger.info(
                    "Clicking on the Currency dropdown"
            );

            currencyPage.clickCurrencyDropdown();

            logger.info(
                    "Selecting Pound Sterling from the currency options"
            );

            currencyPage.selectPoundSterlingCurrency();

            logger.info(
                    "Validating that Pound Sterling currency is selected"
            );

            String selectedCurrency =
                    currencyPage.getCurrentSelectedCurrencySymbol();

            Assert.assertEquals(
                    selectedCurrency,
                    "£",
                    "Currency selection did not change to Pound Sterling"
            );

            logger.info(
                    "Test Case TC_CR_002 - Currency Selection for Pound Sterling Completed Successfully"
            );

        } catch (Exception e) {

            logger.error(
                    "Test Case TC_CR_002 - Error occurred during Currency Selection Test: "
                            + e.getMessage()
            );

            Assert.fail(
                    "Test failed due to an error: "
                            + e.getMessage()
            );
        }
    }
}