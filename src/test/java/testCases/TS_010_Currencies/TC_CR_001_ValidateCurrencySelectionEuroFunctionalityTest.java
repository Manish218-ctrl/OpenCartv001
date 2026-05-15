package testCases.TS_010_Currencies;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CurrencyPage;
import testBase.BaseClass;

public class TC_CR_001_ValidateCurrencySelectionEuroFunctionalityTest extends BaseClass {

    @Test
    public void validateCurrencySelection() {

        logger.info(
                "Test Case TC_CR_001 - Currency Selection for Euro Started"
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
                    "Selecting Euro from the currency options"
            );

            currencyPage.selectEuroCurrency();

            logger.info(
                    "Validating that Euro currency is selected"
            );

            String selectedCurrency =
                    currencyPage.getCurrentSelectedCurrencySymbol();

            Assert.assertEquals(
                    selectedCurrency,
                    "€",
                    "Currency selection did not change to Euro"
            );

            logger.info(
                    "Test Case TC_CR_001 - Currency Selection for Euro Completed Successfully"
            );

        } catch (Exception e) {

            logger.error(
                    "Test Case TC_CR_001 - Error occurred during Currency Selection Test: "
                            + e.getMessage()
            );

            Assert.fail(
                    "Test failed due to an error: "
                            + e.getMessage()
            );
        }
    }
}